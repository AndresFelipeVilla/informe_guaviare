package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(
        @NotBlank(message = "El email es obligatorio") @Email(message = "El email debe tener un formato válido") String email) {
}