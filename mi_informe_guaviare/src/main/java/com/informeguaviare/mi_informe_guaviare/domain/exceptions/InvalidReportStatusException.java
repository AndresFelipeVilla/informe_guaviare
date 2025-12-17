package com.informeguaviare.mi_informe_guaviare.domain.exceptions;

public class InvalidReportStatusException extends DomainException {

    public InvalidReportStatusException(String message) {
        super(message);
    }

    public InvalidReportStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
