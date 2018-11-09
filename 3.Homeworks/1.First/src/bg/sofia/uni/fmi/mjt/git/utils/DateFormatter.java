package bg.sofia.uni.fmi.mjt.git.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateFormatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd HH:mm yyyy");

    static public String formatDate(LocalDateTime time) {
        return String.format("%s %s %s", getDayOfWeek(time.getDayOfWeek()), getMonth(time.getMonth()), formatter.format(time));
    }

    static public String getDayOfWeek(DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    static private String getMonth(Month month) {
        return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }
}
