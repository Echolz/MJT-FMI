package bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl;

import bg.sofia.uni.fmi.mjt.stylechecker.checkers.LineChecker;

public class LineLengthChecker implements LineChecker {
    private static String message = "// FIXME Length of line should not exceed %d characters";
    private int maxLineLength;

    public LineLengthChecker(int maxLineLength) {
        this.maxLineLength = maxLineLength;
    }

    @Override
    public boolean checkLine(String line) {
        return false;
    }

    @Override
    public String getMessage() {
        return String.format(message, maxLineLength);
    }
}
