package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import com.informeguaviare.mi_informe_guaviare.domain.enums.ReportStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportResponse {

    private String title;
    private String description;
    private String responsible;
    private String activities;
    private String objetivo;
    private String linkDeEvidencia;
    private ReportStatus status;
    private LocalDateTime createdAt;

}