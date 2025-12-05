package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.PasswordResetTokenEntity;

public interface JpaPasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, UUID> {

    Optional<PasswordResetTokenEntity> findByToken(String token);

    @Query("SELECT t FROM PasswordResetTokenEntity t WHERE t.userId = :userId AND t.used = false AND t.expiresAt > :now")
    Optional<PasswordResetTokenEntity> findActiveByUserId(@Param("userId") UUID userId,
            @Param("now") LocalDateTime now);

    @Modifying
    @Query("DELETE FROM PasswordResetTokenEntity t WHERE t.expiresAt < :beforeDate")
    void deleteByExpiresAtBefore(@Param("beforeDate") LocalDateTime beforeDate);
}
