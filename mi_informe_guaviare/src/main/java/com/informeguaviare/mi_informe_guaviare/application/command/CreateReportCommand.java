package com.informeguaviare.mi_informe_guaviare.application.command;

import java.util.UUID;

public class CreateReportCommand {

    private String title;
    private String description;
    private String activities;
    private String objetivo;
    private String linkDeEvidencia;
    private UUID employeeId;


    public CreateReportCommand() {
    }

    public CreateReportCommand(String title, String description, String activities, String objetivo, String linkDeEvidencia, UUID employeeId) {
        this.title = title;
        this.description = description;
        this.activities = activities;
        this.objetivo = objetivo;
        this.linkDeEvidencia = linkDeEvidencia;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getLinkDeEvidencia() {
        return linkDeEvidencia;
    }

    public void setLinkDeEvidencia(String linkDeEvidencia) {
        this.linkDeEvidencia = linkDeEvidencia;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
