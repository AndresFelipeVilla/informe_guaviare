package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.command.ResetPasswordCommand;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.InvalidTokenException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.TokenExpiredException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.TokenNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.UserNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.ResetPasswordUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.PasswordResetToken;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.PasswordStrength;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.PasswordResetTokenRepositoryPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResetPasswordService implements ResetPasswordUseCase {

    private final PasswordResetTokenRepositoryPort tokenRepository;
    private final UserRepositoryOutPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(
            PasswordResetTokenRepositoryPort tokenRepository,
            UserRepositoryOutPort userRepository,
            PasswordEncoder passwordEncoder) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordCommand command) {
        PasswordResetToken token = tokenRepository.findByToken(command.token())
                .orElseThrow(() -> new TokenNotFoundException("Token no encontrado o inválido"));

        if (token.isExpired()) {
            throw new TokenExpiredException("El token ha expirado");
        }

        if (!token.isValid()) {
            throw new InvalidTokenException("El token ya ha sido utilizado o es inválido");
        }

        PasswordStrength passwordStrength = PasswordStrength.of(command.newPassword());

        User user = userRepository.findById(token.getUserId().getValue())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        String hashedPassword = passwordEncoder.encode(passwordStrength.getValue());

        user.updatePassword(hashedPassword);

        userRepository.saveUser(user);

        token.markAsUsed();
        tokenRepository.save(token);
    }
}
