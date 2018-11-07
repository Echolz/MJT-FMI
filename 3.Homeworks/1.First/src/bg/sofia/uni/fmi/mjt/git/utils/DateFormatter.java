package bg.sofia.uni.fmi.mjt.git.utils;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateFormatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd HH:mm yyyy");

    static public String formatDate(TemporalAccessor time) {
        return formatter.format(time);
    }
}
