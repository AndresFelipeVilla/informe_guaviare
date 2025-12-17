package com.informeguaviare.mi_informe_guaviare.application.command;

public class UpdateReportCommand {

    private final String title;
    private final String description;
    private final String activities;
    private final String objective;
    private final String evidenceLink;

    public UpdateReportCommand(String title, String description, String activities, String objective,
            String evidenceLink) {
        this.title = title;
        this.description = description;
        this.activities = activities;
        this.objective = objective;
        this.evidenceLink = evidenceLink;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getActivities() {
        return activities;
    }

    public String getObjective() {
        return objective;
    }

    public String getEvidenceLink() {
        return evidenceLink;
    }

}
