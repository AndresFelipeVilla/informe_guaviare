package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.repository;

import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.informeguaviare.mi_informe_guaviare.domain.model.PasswordResetToken;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.PasswordResetTokenRepositoryPort;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.PasswordResetTokenEntity;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper.PasswordResetTokenMapper;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata.JpaPasswordResetTokenRepository;

@Repository
public class PasswordResetTokenRepositoryAdapter implements PasswordResetTokenRepositoryPort {

    private final PasswordResetTokenMapper mapper;
    private final JpaPasswordResetTokenRepository jpaPasswordResetTokenRepository;

    public PasswordResetTokenRepositoryAdapter(PasswordResetTokenMapper mapper,
            JpaPasswordResetTokenRepository jpaPasswordResetTokenRepository) {
        this.mapper = mapper;
        this.jpaPasswordResetTokenRepository = jpaPasswordResetTokenRepository;
    }

    @Override
    public PasswordResetToken save(PasswordResetToken token) {
        PasswordResetTokenEntity entity = mapper.toEntity(token);
        PasswordResetTokenEntity saved = jpaPasswordResetTokenRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<PasswordResetToken> findByToken(String token) {
        return jpaPasswordResetTokenRepository.findByToken(token)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<PasswordResetToken> findActiveByUser(User user) {
        return jpaPasswordResetTokenRepository.findActiveByUserId(
                user.getUserId().getValue(),
                LocalDateTime.now()).map(mapper::toDomain);
    }

    @Override
    public void deleteExpiredTokens(LocalDateTime beforeDate) {
        jpaPasswordResetTokenRepository.deleteByExpiresAtBefore(beforeDate);
    }

}
