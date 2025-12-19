package com.informeguaviare.mi_informe_guaviare.application.command;

import java.util.UUID;

public class CreateReportCommand {

    private String title;
    private String description;
    private String activities;
    private String objective;
    private String evidencieLink;
    private UUID employeeId;

    public CreateReportCommand() {
    }

    public CreateReportCommand(String title, String description, String activities, String objective,
            String evidencieLink, UUID employeeId) {
        this.title = title;
        this.description = description;
        this.activities = activities;
        this.objective = objective;
        this.evidencieLink = evidencieLink;
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

    public String getEvidencieLink() {
        return evidencieLink;
    }

    public void setEvidencieLink(String evidencieLink) {
        this.evidencieLink = evidencieLink;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
