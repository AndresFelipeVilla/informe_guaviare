package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReportRequest {

    @NotBlank(message = "El título no puede estar vacío.")
    private String title;
    private String description;
    private String activities;
    @NotBlank(message = "El objetivo no puede estar vacío.")
    private String objective;
    private String evidenceLink;

}