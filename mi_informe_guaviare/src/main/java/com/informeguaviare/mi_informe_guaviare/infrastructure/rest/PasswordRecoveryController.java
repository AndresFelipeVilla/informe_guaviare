package com.informeguaviare.mi_informe_guaviare.infrastructure.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import com.informeguaviare.mi_informe_guaviare.application.command.RequestPasswordResetCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.ResetPasswordCommand;
import com.informeguaviare.mi_informe_guaviare.application.port.in.RequestPasswordResetUseCase;
import com.informeguaviare.mi_informe_guaviare.application.port.in.ResetPasswordUseCase;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ForgotPasswordRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.MessageResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ResetPasswordRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper.UserMapperDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/password-recovery")
public class PasswordRecoveryController {

    private final RequestPasswordResetUseCase requestPasswordResetUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;
    private final UserMapperDTO userMapperDTO;

    public PasswordRecoveryController(RequestPasswordResetUseCase requestPasswordResetUseCase,
            ResetPasswordUseCase resetPasswordUseCase, UserMapperDTO userMapperDTO) {
        this.requestPasswordResetUseCase = requestPasswordResetUseCase;
        this.resetPasswordUseCase = resetPasswordUseCase;
        this.userMapperDTO = userMapperDTO;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {

        RequestPasswordResetCommand command = userMapperDTO.toRequestPasswordResetCommand(forgotPasswordRequest);
        requestPasswordResetUseCase.requestPasswordReset(command);
        return ResponseEntity.ok(new MessageResponse("Password reset email sent"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {

        ResetPasswordCommand command = userMapperDTO.toResetPasswordCommand(resetPasswordRequest);
        resetPasswordUseCase.resetPassword(command);
        return ResponseEntity.ok(new MessageResponse("Password reset successfully"));
    }

}
