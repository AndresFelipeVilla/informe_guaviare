package com.informeguaviare.mi_informe_guaviare.application.command;

import java.util.UUID;

public class CreateReportCommand {

    private String title;
    private String description;
    private String activities;
    private String objective;
    private String evidenceLink;
    private UUID employeeId;

    public CreateReportCommand() {
    }

    public CreateReportCommand(String title, String description, String activities, String objective,
            String evidenceLink, UUID employeeId) {
        this.title = title;
        this.description = description;
        this.activities = activities;
        this.objective = objective;
        this.evidenceLink = evidenceLink;
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getEvidenceLink() {
        return evidenceLink;
    }

    public void setEvidenceLink(String evidenceLink) {
        this.evidenceLink = evidenceLink;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
