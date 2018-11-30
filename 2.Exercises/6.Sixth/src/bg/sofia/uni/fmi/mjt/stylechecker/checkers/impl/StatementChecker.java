package bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl;

import bg.sofia.uni.fmi.mjt.stylechecker.checkers.LineChecker;

public class StatementChecker implements LineChecker {
    private static final String message = "// FIXME Only one statement per line is allowed";
    private static final String lineRegEx = "([^;]*[;]+)+([^;]*[;]*)+";

    @Override
    public boolean checkLine(String line) {
        int lastIndex = -1;
        for (int i = line.length() - 1; i >= 0; i--) {
            if (line.charAt(i) != ';') {
                continue;
            }

            if (lastIndex != -1 && lastIndex != i + 1) {
                return true;
            }

            lastIndex = i;
        }

        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
