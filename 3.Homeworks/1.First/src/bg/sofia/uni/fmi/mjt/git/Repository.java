package bg.sofia.uni.fmi.mjt.git;

import java.util.*;

public class Repository {
    private Set<String> setOfStagedFiles;
    private Map<String, List<Commit>> branchToCommits;
    private String currentBranch;
    public Repository() {
        setUp();
    }

    public Result add(String... files) {
        String existingFile = "";
        boolean hasExisting = false;

        for (String file : files) {
            if (!setOfStagedFiles.contains(file)) {
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
            setOfStagedFiles.add(file);
            message.append(String.format("%s, ", file));
        }

        message.setLength(message.length() - 2);
        message.append(" to stage");

        return new Result(true, message.toString());
    }

    public Result remove(String... files) {

        return null;
    }

    public Result commit(String message) {

        return null;
    }

    public Commit getHead() {

        return null;
    }

    public Result log() {

        return null;
    }

    public String getBranch() {

        return null;
    }

    public Result createBranch(String name) {
        return null;
    }

    public Result checkoutBranch(String name) {

        return null;
    }

    Result checkoutCommit(String hash) {

        return null;
    }

    private void setUp() {
        setOfStagedFiles = new LinkedHashSet<>();
        branchToCommits = new HashMap<>();
        branchToCommits.put("master", new ArrayList<>());
        currentBranch = "master";
    }
}
