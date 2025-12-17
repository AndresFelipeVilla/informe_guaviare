package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.exceptions;

import com.informeguaviare.mi_informe_guaviare.application.exceptions.BossCodeExistsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.EmailAlreadyExistsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.InvalidCredentialsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.InvalidPasswordException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.InvalidTokenException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.ManagerNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.ReportNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.TokenExpiredException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.TokenNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.UnauthorizedReportAccessException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.UserNotFoundException;
import com.informeguaviare.mi_informe_guaviare.domain.exceptions.DomainException;
import com.informeguaviare.mi_informe_guaviare.domain.exceptions.InvalidReportStatusException;
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

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTokenNotFoundException(TokenNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), "/api/auth/login");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), "/api/auth/password-recovery");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), "/api/auth/password-recovery");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), "/api/users");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                "/api/auth/password-recovery/reset-password");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                "/api/auth/password-recovery/reset-password");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidReportStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidReportStatusException(InvalidReportStatusException e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                "/api/reports");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedReportAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedReportAccessException(UnauthorizedReportAccessException e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                e.getMessage(),
                "/api/reports");
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}
