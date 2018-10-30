package bg.sofia.uni.fmi.jira;

import bg.sofia.uni.fmi.jira.issues.exceptions.InvalidReporterException;

public class Component {
    private String name;
    private String shortName;
    private User creator;

    public Component(String name, String shortName, User creator) throws InvalidReporterException {
        if (creator == null || name == null || shortName == null) {
            throw new InvalidReporterException();
        }
        this.name = name;
        this.shortName = shortName;
        this.creator = creator;
    }

    public String getShortName() {
        return shortName;
    }
}
