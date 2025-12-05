package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(@NotBlank(message = "El token es obligatorio") String token,

        @NotBlank(message = "La nueva contraseña es obligatoria") String newPassword) {

}
