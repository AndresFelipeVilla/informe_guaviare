package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReportDetailsResponse {

    private UUID reportId;
    private String title;
    private String description;
    private String responsible;
    private String status;
    private LocalDateTime sentAt;
    private LocalDateTime updatedAt;

}
