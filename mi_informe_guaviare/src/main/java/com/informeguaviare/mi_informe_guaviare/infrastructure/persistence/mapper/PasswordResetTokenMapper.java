package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.informeguaviare.mi_informe_guaviare.domain.model.PasswordResetToken;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.PasswordResetTokenEntity;

@Mapper(componentModel = "spring", uses = { ValueObjectMapper.class })
public interface PasswordResetTokenMapper {

    @Mapping(target = "userId", source = "userId", qualifiedByName = "userIdToUUID")
    PasswordResetTokenEntity toEntity(PasswordResetToken passwordResetToken);

    @Mapping(target = "tokenId", source = "tokenId")
    @Mapping(target = "token", source = "token")
    @Mapping(target = "userId", source = "userId", qualifiedByName = "uuidToUserId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "expiresAt", source = "expiresAt")
    @Mapping(target = "used", source = "used")
    PasswordResetToken toDomain(PasswordResetTokenEntity entity);
}
