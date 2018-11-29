package bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl;

import bg.sofia.uni.fmi.mjt.stylechecker.checkers.LineChecker;

public class StatementChecker implements LineChecker {
    private static final String message = "// FIXME Only one statement per line is allowed";
    private static final String lineRegEx = "([^;]*[;]+)+([^;]*[;]*)+";

    @Override
    public boolean checkLine(String line) {
        return line.matches(lineRegEx);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
