package bg.sofia.uni.fmi.mjt.git;

import java.util.*;

public class Repository {
    private Set<String> setOfFilesToAdd;
    private Set<String> setOfFilesToRemove;
    private Map<String, List<Commit>> branchToCommits;
    private String currentBranch;

    public Repository() {
        setUp();
    }

    public Result add(String... files) {
        String existingFile = "";
        boolean hasExisting = false;

        for (String file : files) {
            if (!setOfFilesToAdd.contains(file)) {
                continue;
            }

            hasExisting = true;
            existingFile = file;
            break;
        }

        if (hasExisting) {
            return new Result(false, String.format("'%s' already exists", existingFile));
        }

        StringBuilder message = new StringBuilder();

        message.append("added ");

        for (String file : files) {
            setOfFilesToAdd.add(file);
            setOfFilesToRemove.remove(file);
            message.append(String.format("%s, ", file));
        }

        message.setLength(message.length() - 2);
        message.append(" to stage");

        return new Result(true, message.toString());
    }

    public Result remove(String... files) {
        String nonExistingFile = "";
        boolean hasNonExisting = false;

        for (String file : files) {
            if (setOfFilesToAdd.contains(file)) {
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
            setOfFilesToAdd.remove(file);
            setOfFilesToRemove.add(file);
            message.append(String.format("%s, ", file));
        }

        message.setLength(message.length() - 2);
        message.append(" for removal");

        return new Result(true, message.toString());
    }

    // TODO: 9.11.2018 г.  
    public Result commit(String message) {
        if (setOfFilesToRemove.size() == 0 && setOfFilesToAdd.size() == 0) {
            return new Result(false, "nothing to commit, working tree clean");
        }

        // creating a result when commit is valid
        int numberOfChangedFiles = setOfFilesToAdd.size() + setOfFilesToRemove.size();
        Result toReturn = new Result(true, String.format("%d files changed", numberOfChangedFiles));

        // creating and adding commit
        Commit commit = new Commit(message);
        branchToCommits.get(currentBranch).add(commit);

        // clearing sets for the new commit
        return toReturn;
    }

    public Commit getHead() {
        List<Commit> commits = branchToCommits.get(currentBranch);

        if (commits.size() == 0) {
            return null;
        }

        return commits.get(commits.size() - 1);
    }

    // TODO: 9.11.2018 г.
    public Result log() {
        List<Commit> commits = branchToCommits.get(currentBranch);

        if (commits.size() == 0) {
            return new Result(false, String.format("%s does not have any commits yet", currentBranch));
        }

        StringBuilder resultMessage = new StringBuilder();

        for (int i = 0; i < commits.size(); i++) {
            resultMessage.append(commits.get(i).toString());
            if (i == commits.size() - 1) {
                break;
            }
            resultMessage.append("\n");
        }

        return new Result(true, resultMessage.toString());
    }

    public String getBranch() {
        return currentBranch;
    }

    public Result createBranch(String name) {
        if (branchToCommits.containsKey(name)) {
            return new Result(false, String.format("branch %s already exists", name));
        }

        List<Commit> newBranchCommits = new ArrayList<>(branchToCommits.get(currentBranch));

        branchToCommits.put(name, newBranchCommits);

        return new Result(true, String.format("created branch %s", name));
    }

    public Result checkoutBranch(String name) {
        if (!branchToCommits.containsKey(name)) {
            return new Result(false, String.format("branch %s does not exist", name));
        }

        currentBranch = name;
        return new Result(true, String.format("switched to branch %s", name));
    }

    // TODO: 9.11.2018 г.
    Result checkoutCommit(String hash) {

        return null;
    }

    private void setUp() {
        setOfFilesToAdd = new LinkedHashSet<>();
        setOfFilesToRemove = new LinkedHashSet<>();
        branchToCommits = new HashMap<>();
        branchToCommits.put("master", new ArrayList<>());
        currentBranch = "master";
    }
}
