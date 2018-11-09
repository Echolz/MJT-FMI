package bg.sofia.uni.fmi.mjt.git;

import bg.sofia.uni.fmi.mjt.git.utils.DateFormatter;
import bg.sofia.uni.fmi.mjt.git.utils.HashGenerator;

import java.time.LocalDateTime;

public class Commit {
    private String message;
    private String hash;
    private LocalDateTime createdDate;
    private String dateAsString;

    public Commit(String message) {
        this.message = message;
        createdDate = LocalDateTime.now();
        dateAsString = DateFormatter.formatDate(createdDate);
        hash = HashGenerator.hexDigest(String .format("%s%s", dateAsString, message));
    }

    public String getHash() {
        return hash;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("commit %s\nDate: %s\n\n\t%s", hash, dateAsString, message);
    }
}
