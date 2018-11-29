package bg.sofia.uni.fmi.mjt.stylechecker;

import bg.sofia.uni.fmi.mjt.stylechecker.utils.LineChecker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        if (inputStream == null) {
            initProperties();
        } else {
            initProperties(inputStream);
        }

        initCheckers();
    }

    private void initCheckers() {
        lineCheckers = new ArrayList<>();

        if (isPropertySet(OPENING_BRACKET_CHECK)) {

        }

        if (isPropertySet(STATEMENTS_PER_LINE_CHECK)) {

        }

        if (isPropertySet(LENGTH_OF_LINE_CHECK)) {
            int lineLimit = Integer.parseInt(properties.getProperty(LINE_LENGTH_LIMIT));

        }

        if (isPropertySet(WILDCARD_IMPORT_CHECK)) {

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
        } catch (IOException e) {
            initProperties();
        }


    }

    private boolean isPropertySet(String propertyName) {
        String property = properties.getProperty(propertyName);
        return Boolean.parseBoolean(property);
    }

    public void checkStyle(InputStream source, OutputStream output) {

    }
}
