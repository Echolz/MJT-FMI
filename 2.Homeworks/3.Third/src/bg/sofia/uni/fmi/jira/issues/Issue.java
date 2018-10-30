package bg.sofia.uni.fmi.jira.issues;

import bg.sofia.uni.fmi.jira.Component;
import bg.sofia.uni.fmi.jira.User;
import bg.sofia.uni.fmi.jira.enums.IssuePriority;
import bg.sofia.uni.fmi.jira.enums.IssueResolution;
import bg.sofia.uni.fmi.jira.enums.IssueStatus;
import bg.sofia.uni.fmi.jira.enums.IssueType;
import bg.sofia.uni.fmi.jira.interfaces.IIssue;
import bg.sofia.uni.fmi.jira.issues.exceptions.InvalidReporterException;

import java.time.LocalDateTime;

public abstract class Issue implements IIssue {
    private static int numberOfIssues = 0;
    private IssuePriority priority;
    private IssueResolution resolution;
    private IssueStatus status;

    private Component component;
    private User reporter;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private int id;

    public Issue(IssuePriority priority, Component component, User reporter, String description) throws InvalidReporterException {
        this.priority = priority;
        this.component = component;
        this.reporter = reporter;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.id = numberOfIssues;
        this.status = IssueStatus.OPEN;
        this.resolution = IssueResolution.UNRESOLVED;
        numberOfIssues ++;
    }

    public IssuePriority getPriority() {
        return priority;
    }

    public IssueResolution getResolution() {
        return resolution;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public Component getComponent() {
        return component;
    }

    public User getReporter() {
        return reporter;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public abstract IssueType getType();

    @Override
    public void resolve(IssueResolution resolution) {
        this.resolution = resolution;
        lastModified = LocalDateTime.now();
    }

    @Override
    public void setStatus(IssueStatus status) {
        this.status = status;
        lastModified = LocalDateTime.now();
    }

    @Override
    public String getId() {
        return String.format("%s-%d", component.getShortName(), id);
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getLastModifiedAt() {
        return lastModified;
    }
}
