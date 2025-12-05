package com.informeguaviare.mi_informe_guaviare.application.command;

public record ResetPasswordCommand(String token, String newPassword) {

    public ResetPasswordCommand {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("El token no puede ser nulo o vacío");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
        }
    }

}
