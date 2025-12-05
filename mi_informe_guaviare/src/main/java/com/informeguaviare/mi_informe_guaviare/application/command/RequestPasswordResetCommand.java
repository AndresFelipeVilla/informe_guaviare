package com.informeguaviare.mi_informe_guaviare.application.command;

public record RequestPasswordResetCommand(String email) {

    public RequestPasswordResetCommand {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede ser nulo o vacío");
        }
    }

}
