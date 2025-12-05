package com.informeguaviare.mi_informe_guaviare.domain.model;

import com.informeguaviare.mi_informe_guaviare.domain.exceptions.DomainException;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.UserId;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class PasswordResetToken {
    private UUID tokenId;
    private String token;
    private UserId userId;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean used;

    public PasswordResetToken(UUID tokenId, String token,
            UserId userId, LocalDateTime createdAt,
            LocalDateTime expiresAt, boolean used) {
        this.tokenId = tokenId;
        this.token = token;
        this.userId = userId;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.used = used;
    }

    public static PasswordResetToken create(User user) {
        if (user == null || user.getUserId() == null) {
            throw new DomainException("El usuario no puede ser nulo");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusHours(1); // 1 hora de expiración
        String tokenValue = UUID.randomUUID().toString();

        return new PasswordResetToken(
                null,
                tokenValue,
                user.getUserId(),
                now,
                expiration,
                false);
    }

    public static PasswordResetToken reconstruct(UUID tokenId, String token, UserId userId,
            LocalDateTime createdAt, LocalDateTime expiresAt, boolean used) {
        return new PasswordResetToken(tokenId, token, userId, createdAt, expiresAt, used);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isValid() {
        return !isExpired() && !used;
    }

    public void markAsUsed() {
        if (this.used) {
            throw new DomainException("El token ya ha sido utilizado");
        }
        if (isExpired()) {
            throw new DomainException("No se puede usar un token expirado");
        }
        this.used = true;
    }

    public UUID getTokenId() {
        return tokenId;
    }

    public String getToken() {
        return token;
    }

    public UserId getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PasswordResetToken that = (PasswordResetToken) o;
        return Objects.equals(tokenId, that.tokenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenId);
    }

}
