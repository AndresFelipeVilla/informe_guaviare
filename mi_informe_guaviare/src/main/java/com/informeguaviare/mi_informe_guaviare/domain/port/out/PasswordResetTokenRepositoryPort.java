package com.informeguaviare.mi_informe_guaviare.domain.port.out;

import java.time.LocalDateTime;
import java.util.Optional;

import com.informeguaviare.mi_informe_guaviare.domain.model.PasswordResetToken;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;

public interface PasswordResetTokenRepositoryPort {

    PasswordResetToken save(PasswordResetToken token);

    Optional<PasswordResetToken> findByToken(String token);

    Optional<PasswordResetToken> findActiveByUser(User user);

    void deleteExpiredTokens(LocalDateTime beforeDate);

}
