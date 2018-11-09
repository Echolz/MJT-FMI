package bg.sofia.uni.fmi.mjt.git;

import bg.sofia.uni.fmi.mjt.git.utils.DateFormatter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Commit {
    private String message;
    private String hash;
    private LocalDateTime createdDate;
    private String dateAsString;

    // TODO: 9.11.2018 г.  
    public Commit(String message) {
        this.message = message;
        createdDate = LocalDateTime.now();
        dateAsString = DateFormatter.formatDate(createdDate);
        System.out.println(dateAsString);
    }

    // TODO: 9.11.2018 г.
    public String getHash() {
        return null;
    }

    public String getMessage() {
        return this.message;
    }
}
