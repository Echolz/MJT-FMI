package bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl;

import bg.sofia.uni.fmi.mjt.stylechecker.checkers.LineChecker;

public class LineLengthChecker implements LineChecker {
    private static String message;
    private int maxLineLength;

    public LineLengthChecker(int maxLineLength) {
        this.maxLineLength = maxLineLength;
        message = "// FIXME Length of line should not exceed " + maxLineLength + " characters";
    }

    @Override
    public boolean checkLine(String line) {
        if (line.startsWith("import")) {
            return false;
        }
        return line.length() > maxLineLength;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
