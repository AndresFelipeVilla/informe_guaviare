package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportSummaryResponse {

    private String title;
    private String employee;
    private LocalDateTime sentIn;
    private String status;

}
