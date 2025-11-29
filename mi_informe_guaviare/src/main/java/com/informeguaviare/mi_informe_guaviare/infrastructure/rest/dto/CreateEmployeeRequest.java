package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateEmployeeRequest extends BaseUserRequest {

    @NotBlank(message = "El código del jefe no puede estar vacío.")
    private String managerBossCode;
}