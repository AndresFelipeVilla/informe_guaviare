package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReportSummaryResponse {

    private UUID reportId;
    private String title;
    private String employee;
    private LocalDateTime sentAt;
    private String status;

}
