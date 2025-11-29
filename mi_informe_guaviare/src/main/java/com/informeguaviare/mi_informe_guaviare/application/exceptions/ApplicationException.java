package com.informeguaviare.mi_informe_guaviare.application.exceptions;

public abstract class ApplicationException extends RuntimeException{

    public ApplicationException (String message){
        super(message);
    }

    public ApplicationException (String message, Throwable cause){
        super(message, cause);
    }
}
