package bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl;

import bg.sofia.uni.fmi.mjt.stylechecker.checkers.LineChecker;

public class BracketsChecker implements LineChecker {
    private static final String message = "// FIXME Opening brackets should be placed on the same line as the declaration";

    @Override
    public boolean checkLine(String line) {
        return line.startsWith("{");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
