package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;


import java.time.Instant;

public record ErrorResponse(Instant timestamp, int status, String error, String message, String path) {

    public ErrorResponse(Instant timestamp, int status, String error, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
