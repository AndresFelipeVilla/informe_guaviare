package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateBossRequest extends BaseUserRequest {

    @NotBlank(message = "El código de jefe no puede estar vacío.")
    private String bossCode;
}