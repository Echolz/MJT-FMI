package bg.sofia.uni.fmi.jira;

import bg.sofia.uni.fmi.jira.enums.IssuePriority;
import bg.sofia.uni.fmi.jira.enums.IssueResolution;
import bg.sofia.uni.fmi.jira.enums.IssueStatus;
import bg.sofia.uni.fmi.jira.enums.IssueType;
import bg.sofia.uni.fmi.jira.interfaces.IssueTracker;
import bg.sofia.uni.fmi.jira.issues.Issue;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Jira implements IssueTracker {
    private Issue[] issues;

    public Jira(Issue[] issues) {
        this.issues = issues;
    }

    @Override
    public Issue[] findAll(Component component, IssueStatus status) {
        Issue[] toReturn = Arrays.copyOf(issues, issues.length);

        for (int i = 0; i < issues.length; i++) {
            if (toReturn[i].getComponent().equals(component) && toReturn[i].getStatus().equals(status)) {
                continue;
            }

            toReturn[i] = null;
        }

        return toReturn;
    }

    @Override
    public Issue[] findAll(Component component, IssuePriority priority) {
        Issue[] toReturn = Arrays.copyOf(issues, issues.length);

        for (int i = 0; i < toReturn.length; i++) {
            if (toReturn[i].getComponent().equals(component) && toReturn[i].getPriority().equals(priority)) {
                continue;
            }

            toReturn[i] = null;
        }

        return toReturn;
    }

    @Override
    public Issue[] findAll(Component component, IssueType type) {
        Issue[] toReturn = Arrays.copyOf(issues, issues.length);

        for (int i = 0; i < toReturn.length; i++) {
            if (toReturn[i].getComponent().equals(component) && toReturn[i].getType().equals(type)) {
                continue;
            }

            toReturn[i] = null;
        }

        return toReturn;
    }

    @Override
    public Issue[] findAll(Component component, IssueResolution resolution) {
        Issue[] toReturn = Arrays.copyOf(issues, issues.length);

        for (int i = 0; i < toReturn.length; i++) {
            if (toReturn[i].getComponent().equals(component) && toReturn[i].getResolution().equals(resolution)) {
                continue;
            }

            toReturn[i] = null;
        }

        return toReturn;
    }

    @Override
    public Issue[] findAllIssuesCreatedBetween(LocalDateTime startTime, LocalDateTime endTime) {
        Issue[] toReturn = Arrays.copyOf(issues, issues.length);

        for (int i = 0; i < toReturn.length; i++) {
            if (toReturn[i].getCreatedAt().compareTo(startTime) >= 0 && toReturn[i].getCreatedAt().compareTo(endTime) <= 0) {
                continue;
            }

            toReturn[i] = null;
        }

        return toReturn;
    }

    @Override
    public Issue[] findAllBefore(LocalDateTime dueTime) {
        Issue[] toReturn = Arrays.copyOf(issues, issues.length);

        for (int i = 0; i < toReturn.length; i++) {
            if (toReturn[i].getDueTime() != null && toReturn[i].getDueTime().compareTo(dueTime) <= 0) {
                continue;
            }

            toReturn[i] = null;
        }

        return toReturn;
    }
}
