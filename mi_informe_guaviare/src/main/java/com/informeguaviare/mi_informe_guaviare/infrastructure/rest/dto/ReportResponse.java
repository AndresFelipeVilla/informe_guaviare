package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import com.informeguaviare.mi_informe_guaviare.domain.enums.ReportStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReportResponse {

    private UUID reportId;
    private String title;
    private String description;
    private String responsible;
    private String activities;
    private String objective;
    private String evidencieLink;
    private ReportStatus status;
    private LocalDateTime createdAt;

}