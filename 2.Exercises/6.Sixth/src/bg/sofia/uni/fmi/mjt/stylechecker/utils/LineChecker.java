package bg.sofia.uni.fmi.mjt.stylechecker.utils;

public interface LineChecker {
    boolean checkLine(String line);

    String getMessage();
}
