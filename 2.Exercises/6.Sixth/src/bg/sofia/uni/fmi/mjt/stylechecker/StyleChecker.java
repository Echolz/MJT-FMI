package bg.sofia.uni.fmi.mjt.stylechecker;

import bg.sofia.uni.fmi.mjt.stylechecker.checkers.LineChecker;
import bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl.BracketsChecker;
import bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl.ImportsChecker;
import bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl.LineLengthChecker;
import bg.sofia.uni.fmi.mjt.stylechecker.checkers.impl.StatementChecker;
import bg.sofia.uni.fmi.mjt.stylechecker.utils.StreamParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StyleChecker {
    private static final String WILDCARD_IMPORT_CHECK = "wildcard.import.check.active";
    private static final String STATEMENTS_PER_LINE_CHECK = "statements.per.line.check.active";
    private static final String LENGTH_OF_LINE_CHECK = "length.of.line.check.active";
    private static final String OPENING_BRACKET_CHECK = "opening.bracket.check.active";
    private static final String LINE_LENGTH_LIMIT = "line.length.limit";

    private static final String DEFAULT_LINE_LENGTH_LIMIT = "100";
    private static final String DEFAULT_CHECK_MODE = "true";

    private Properties properties;
    private List<LineChecker> lineCheckers;

    public StyleChecker() {
        this(null);
    }

    public StyleChecker(InputStream inputStream) {
        properties = new Properties();
        initProperties();
        if (inputStream != null) {
            initProperties(inputStream);
        }
        initCheckers();
    }

    private void initCheckers() {
        lineCheckers = new ArrayList<>();

        if (isPropertySet(OPENING_BRACKET_CHECK)) {
            lineCheckers.add(new BracketsChecker());
        }

        if (isPropertySet(STATEMENTS_PER_LINE_CHECK)) {
            lineCheckers.add(new StatementChecker());
        }

        if (isPropertySet(LENGTH_OF_LINE_CHECK)) {
            int lineLimit = Integer.parseInt(properties.getProperty(LINE_LENGTH_LIMIT));
            lineCheckers.add(new LineLengthChecker(lineLimit));
        }

        if (isPropertySet(WILDCARD_IMPORT_CHECK)) {
            lineCheckers.add(new ImportsChecker());
        }
    }

    private void initProperties() {
        properties = new Properties();
        String[] booleanProps = {WILDCARD_IMPORT_CHECK, STATEMENTS_PER_LINE_CHECK,
                LENGTH_OF_LINE_CHECK, OPENING_BRACKET_CHECK};

        for (String property : booleanProps) {
            properties.setProperty(property, DEFAULT_CHECK_MODE);
        }

        properties.setProperty(LINE_LENGTH_LIMIT, DEFAULT_LINE_LENGTH_LIMIT);
    }

    private void initProperties(InputStream inputStream) {
        try {
            properties.load(inputStream);
            System.out.println(properties);
        } catch (IOException e) {
            initProperties();
        }
    }

    private boolean isPropertySet(String propertyName) {
        String property = properties.getProperty(propertyName);
        return Boolean.parseBoolean(property);
    }

    public void checkStyle(InputStream source, OutputStream output) {
        List<String> lines = StreamParser.parse(source);
        List<String> newLines = createNewLines(lines);
        writeLines(newLines, output);
    }

    private void writeLines(List<String> lines, OutputStream output) {
        PrintWriter writer = new PrintWriter(output);
        lines.forEach(writer::println);
        writer.close();
    }

    private List<String> createNewLines(List<String> lines) {
        List<String> newLines = new ArrayList<>();

        for (String line : lines) {
            if (line.equals("")) {
                newLines.add(line);
                continue;
            }

            for (LineChecker lineChecker : lineCheckers) {
                if (lineChecker.checkLine(line)) {
                    newLines.add(lineChecker.getMessage());
                }
            }
            newLines.add(line);
        }

        return newLines;
    }
}
