package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.port.in.RequestPasswordResetUseCase;
import com.informeguaviare.mi_informe_guaviare.application.command.RequestPasswordResetCommand;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.PasswordResetTokenRepositoryPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.EmailNotificationPort;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.model.PasswordResetToken;
import org.springframework.stereotype.Service;

@Service
public class RequestPasswordResetService implements RequestPasswordResetUseCase {

    private final UserRepositoryOutPort userRepositoryPort;
    private final PasswordResetTokenRepositoryPort passwordResetTokenRepositoryPort;
    private final EmailNotificationPort emailNotificationPort;

    public RequestPasswordResetService(UserRepositoryOutPort userRepositoryPort,
            PasswordResetTokenRepositoryPort passwordResetTokenRepositoryPort,
            EmailNotificationPort emailNotificationPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordResetTokenRepositoryPort = passwordResetTokenRepositoryPort;
        this.emailNotificationPort = emailNotificationPort;
    }

    @Override
    public void requestPasswordReset(RequestPasswordResetCommand command) {
        User user = userRepositoryPort.findByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));
        PasswordResetToken token = PasswordResetToken.create(user);
        passwordResetTokenRepositoryPort.save(token);
        emailNotificationPort.sendPasswordResetEmail(user, token.getToken());
    }

}
