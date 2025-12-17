package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import com.informeguaviare.mi_informe_guaviare.domain.enums.ReportStatus;

@Data
public class ReportListResponse {

    private UUID reportId;
    private String title;
    private LocalDateTime sentAt;
    private String description;
    private ReportStatus status;
}
