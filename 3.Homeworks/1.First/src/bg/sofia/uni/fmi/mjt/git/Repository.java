package bg.sofia.uni.fmi.mjt.git;

import java.util.*;

public class Repository {
    private Set<String> lastCommitFiles;
    private Set<String> stagedFiles;
    private int lastCommitDeletedFiles;
    private Map<String, Deque<Commit>> branchToCommits;
    private Map<String, Commit> hashToCommit;
    private String currentBranch;

    public Repository() {
        setUp();
    }

    public Result add(String... files) {
        String existingFile = "";
        boolean hasExistingFile = false;

        for (String file : files) {
            if (!lastCommitFiles.contains(file) && !stagedFiles.contains(file)) {
                continue;
            }

            hasExistingFile = true;
            existingFile = file;
            break;
        }

        if (hasExistingFile) {
            return new Result(false, String.format("'%s' already exists", existingFile));
        }

        StringBuilder resultMessage = new StringBuilder();

        resultMessage.append("added ");

        for (String file : files) {
            stagedFiles.add(file);
            resultMessage.append(String.format("%s, ", file));
        }

        resultMessage.setLength(resultMessage.length() - 2);
        resultMessage.append(" to stage");

        return new Result(true, resultMessage.toString());
    }

    public Result remove(String... files) {
        String nonExistingFile = "";
        boolean hasNonExisting = false;

        for (String file : files) {
            if (stagedFiles.contains(file) || lastCommitFiles.contains(file)) {
                continue;
            }

            hasNonExisting = true;
            nonExistingFile = file;
            break;
        }

        if (hasNonExisting) {
            return new Result(false, String.format("'%s' did not match any files", nonExistingFile));
        }

        StringBuilder message = new StringBuilder();

        message.append("added ");

        for (String file : files) {
            message.append(String.format("%s, ", file));
            if (stagedFiles.remove(file)) {
                continue;
            }

            lastCommitFiles.remove(file);
            lastCommitDeletedFiles++;
        }

        message.setLength(message.length() - 2);
        message.append(" for removal");

        return new Result(true, message.toString());
    }

    public Result commit(String message) {
        if (lastCommitDeletedFiles == 0 && stagedFiles.size() == 0) {
            return new Result(false, "nothing to commit, working tree clean");
        }

        Result toReturn = new Result(true, String.format("%s files changed", stagedFiles.size() + lastCommitDeletedFiles));

        lastCommitFiles.addAll(stagedFiles);
        Commit commit = new Commit(message, lastCommitFiles);
        branchToCommits.get(currentBranch).addLast(commit);
        hashToCommit.put(commit.getHash(), commit);

        resetStage();

        return toReturn;
    }

    public Commit getHead() {
        return branchToCommits.get(currentBranch).peekLast();
    }

    public Result log() {
        if (branchToCommits.get(currentBranch).size() == 0) {
            return new Result(false, String.format("branch %s does not have any commits yet", currentBranch));
        }

        StringBuilder resultMessage = new StringBuilder();

        for (Commit commit : branchToCommits.get(currentBranch)) {
            resultMessage.append(commit.toString());
            resultMessage.append("\n");
            resultMessage.append("\n");
        }

        resultMessage.setLength(resultMessage.length() - 2);

        return new Result(true, resultMessage.toString());
    }

    public String getBranch() {
        return currentBranch;
    }

    public Result createBranch(String name) {
        if (branchToCommits.containsKey(name)) {
            return new Result(false, String.format("branch %s already exists", name));
        }

        Deque<Commit> lastBranchCommits;

        if (name.equals("master")) {
            lastBranchCommits = new LinkedList<>();
        } else {
            lastBranchCommits = new LinkedList<>(branchToCommits.get(currentBranch));
        }

        branchToCommits.put(name, lastBranchCommits);
        return new Result(true, String.format("created branch %s", name));
    }

    public Result checkoutBranch(String name) {
        if (!branchToCommits.containsKey(name)) {
            return new Result(false, String.format("branch %s does not exist", name));
        }

        currentBranch = name;

        resetStage();

        return new Result(true, String.format("switched to branch %s", name));

    }

    public Result checkoutCommit(String hash) {
        if (!hashToCommit.containsKey(hash)) {
            return new Result(false, String.format("commit %s does not exist", hash));
        }

        Deque<Commit> currentBranchCommits = branchToCommits.get(currentBranch);

        while (!currentBranchCommits.peekLast().getHash().equals(hash)) {
            currentBranchCommits.pollLast();
        }

        resetStage();

        return new Result(true, String.format("HEAD is now at %s", hash));
    }

    private void resetStage() {
        stagedFiles = new LinkedHashSet<>();
        lastCommitFiles = getLastCommitFiles();
        lastCommitDeletedFiles = 0;
    }

    private Set<String> getLastCommitFiles() {
        Commit lastBranchCommit = getHead();
        lastCommitDeletedFiles = 0;
        if (lastBranchCommit == null) {
            return new LinkedHashSet<>();
        } else {
            return lastBranchCommit.getFiles();
        }
    }

    private void setUp() {
        branchToCommits = new HashMap<>();
        createBranch("master");
        checkoutBranch("master");
        hashToCommit = new HashMap<>();
    }
}
