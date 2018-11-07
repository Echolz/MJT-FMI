package bg.sofia.uni.fmi.mjt.git;

import java.time.temporal.TemporalAccessor;

public class Commit {
    private String message;
    private String hash;
    private TemporalAccessor createdDate;

    public Commit(String message) {
        this.message = message;
    }

    public String getHash() {
        return null;
    }

    public String getMessage() {
        return null;
    }
}
