package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportListResponse {

    private String title;
    private LocalDateTime sentIn;
    private String description;
    private String status;
}
