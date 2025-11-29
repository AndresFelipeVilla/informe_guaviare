package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDetailsResponse {

    private String title;
    private String description;
    private String responsible;
    private String status;
    private LocalDateTime sentIn;
    private LocalDateTime updatedAt;

}
