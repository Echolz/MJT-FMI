package bg.sofia.uni.fmi.mjt.stylechecker.checkers;

public interface LineChecker {
    boolean checkLine(String line);

    String getMessage();
}
