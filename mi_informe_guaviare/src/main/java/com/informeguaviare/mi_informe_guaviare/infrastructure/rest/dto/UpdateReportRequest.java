package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateReportRequest {

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 200, message = "El título no puede exceder 200 caracteres")
    private String title;
    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String description;
    @Size(max = 2000, message = "Las actividades no pueden exceder 2000 caracteres")
    private String activities;
    @Size(max = 500, message = "El objetivo no puede exceder 500 caracteres")
    private String objective;
    @Size(max = 500, message = "El link de evidencia no puede exceder 500 caracteres")
    private String evidenceLink;

}
