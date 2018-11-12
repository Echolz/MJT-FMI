package bg.sofia.uni.fmi.mjt.git;

import bg.sofia.uni.fmi.mjt.git.utils.DateFormatter;
import bg.sofia.uni.fmi.mjt.git.utils.HashGenerator;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class Commit {
    private String message;
    private String hash;
    private LocalDateTime createdDate;
    private String dateAsString;
    private Set<String> files;

    public Commit(String message, Set<String> files) {
        this.files = files;
        this.message = message;
        createdDate = LocalDateTime.now();
        dateAsString = DateFormatter.formatDate(createdDate);
        hash = HashGenerator.hexDigest(String.format("%s%s", dateAsString, message));
    }

    public String getHash() {
        return hash;
    }

    public String getMessage() {
        return this.message;
    }

    public Set<String> getFiles() {
        return new LinkedHashSet<>(files);
    }

    public void setFiles(Set<String> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return String.format("commit %s\nDate: %s\n\n\t%s\n", hash, dateAsString, message);
    }
}
