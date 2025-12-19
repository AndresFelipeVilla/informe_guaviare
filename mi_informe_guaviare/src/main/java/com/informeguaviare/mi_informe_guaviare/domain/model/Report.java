package com.informeguaviare.mi_informe_guaviare.domain.model;

import java.time.LocalDateTime;
import com.informeguaviare.mi_informe_guaviare.domain.enums.ReportStatus;
import com.informeguaviare.mi_informe_guaviare.domain.exceptions.InvalidReportStatusException;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.ReportId;

public class Report {

    private final ReportId reportId;
    private String title;
    private String description;
    private String activities;
    private String objective;
    private String evidencieLink;
    private ReportStatus status;
    private final User employee;
    private LocalDateTime sentIn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Report(ReportId reportId, String title, String description, String activities, String objective,
            String evidencieLink, User employee) {
        this.reportId = reportId;
        this.title = title;
        this.description = description;
        this.activities = activities;
        this.objective = objective;
        this.evidencieLink = evidencieLink;
        this.employee = employee;
        this.status = ReportStatus.BORRADOR;
        this.createdAt = LocalDateTime.now();
    }

    private Report(ReportId reportId, String title, String description, String activities, String objective,
            String evidencieLink,
            ReportStatus status, User employee, LocalDateTime sentIn, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.reportId = reportId;
        this.title = title;
        this.description = description;
        this.activities = activities;
        this.objective = objective;
        this.evidencieLink = evidencieLink;
        this.status = status;
        this.employee = employee;
        this.sentIn = sentIn;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Report load(ReportId reportId, String title, String description, String activities, String objective,
            String evidenceLink,
            ReportStatus status, User employee, LocalDateTime sentIn, LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        return new Report(reportId, title, description, activities, objective, evidenceLink,
                status, employee, sentIn, createdAt, updatedAt);
    }

    public static Report create(String title, String description, String activities, String objective,
            String evidenceLink, User employee) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }
        if (employee == null) {
            throw new IllegalArgumentException("El reporte debe estar asociado a un empleado.");
        }
        return new Report(null, title, description, activities, objective, evidenceLink, employee);
    }

    public void update(String newTitle, String newDescription, String newActivities,
            String newObjective, String newLinkOfEvidence) {
        validateCanBeUpdated();

        if (newTitle != null && !newTitle.trim().isEmpty()) {
            this.title = newTitle;
        }
        if (newDescription != null) {
            this.description = newDescription;
        }
        if (newActivities != null) {
            this.activities = newActivities;
        }
        if (newObjective != null) {
            this.objective = newObjective;
        }
        if (newLinkOfEvidence != null) {
            this.evidencieLink = newLinkOfEvidence;
        }

        this.updatedAt = LocalDateTime.now();
    }

    private void validateCanBeUpdated() {
        if (this.status != ReportStatus.BORRADOR) {
            throw new InvalidReportStatusException(
                    String.format("El reporte no puede ser actualizado porque su estado es %s. " +
                            "Solo los reportes en estado BORRADOR pueden ser modificados.",
                            this.status));
        }
    }

    public void send() {
        if (this.status != ReportStatus.BORRADOR) {
            throw new IllegalStateException(
                    "Solo se puede enviar un reporte que está en estado de borrador (BORRADOR).");
        }

        this.status = ReportStatus.ENVIADO;
        this.sentIn = LocalDateTime.now();
        this.updatedAt = this.sentIn;
    }

    public ReportId getReportId() {
        return reportId;
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

    public String getEvidencieLink() {
        return evidencieLink;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public User getEmployee() {
        return employee;
    }

    public LocalDateTime getSentIn() {
        return sentIn;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
