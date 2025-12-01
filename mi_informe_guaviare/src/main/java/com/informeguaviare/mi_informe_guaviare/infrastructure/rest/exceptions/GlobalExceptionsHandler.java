package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.exceptions;

import com.informeguaviare.mi_informe_guaviare.application.exceptions.BossCodeExistsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.EmailAlreadyExistsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.InvalidCredentialsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.ManagerNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.ReportNotFoundException;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(BossCodeExistsException.class)
    public ResponseEntity<ErrorResponse> handleBossCodeExistsException(BossCodeExistsException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(), e.getMessage(), "/api/users");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(), e.getMessage(), "/api/users");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage(), "/api/auth/login");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleManagerNotFoundException(ManagerNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), "/api/users");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReportNotFoundException(ReportNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), "/api/reports");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
