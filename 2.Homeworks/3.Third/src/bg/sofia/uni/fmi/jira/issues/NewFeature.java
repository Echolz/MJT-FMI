package bg.sofia.uni.fmi.jira.issues;

import bg.sofia.uni.fmi.jira.Component;
import bg.sofia.uni.fmi.jira.User;
import bg.sofia.uni.fmi.jira.enums.IssuePriority;
import bg.sofia.uni.fmi.jira.enums.IssueType;
import bg.sofia.uni.fmi.jira.issues.exceptions.InvalidReporterException;
import bg.sofia.uni.fmi.jira.issues.exceptions.NullDependency;

import java.time.LocalDateTime;

public class NewFeature extends Issue{
    private LocalDateTime dueTime;

    public NewFeature(IssuePriority priority, Component component, User reporter, String description, LocalDateTime dueTime) throws InvalidReporterException {
        super(priority, component, reporter, description);
        if (dueTime == null) {
            throw new NullDependency();
        }
        this.dueTime = dueTime;
    }

    @Override
    public IssueType getType() {
        return IssueType.NEW_FEATURE;
    }

    @Override
    public LocalDateTime getDueTime() {
        return dueTime;
    }
}
