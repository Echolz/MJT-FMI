package bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl;

import bg.sofia.uni.fmi.mjt.stylechecker.checkers.LineChecker;

public class ImportsChecker implements LineChecker {
    private static String message = "// FIXME Wildcards are not allowed in import statements";

    @Override
    public boolean checkLine(String line) {
        return line.startsWith("{");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
