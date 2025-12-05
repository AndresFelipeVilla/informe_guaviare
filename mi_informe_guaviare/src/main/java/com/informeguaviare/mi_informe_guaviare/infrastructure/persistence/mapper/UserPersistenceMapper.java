package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ValueObjectMapper.class })
public interface UserPersistenceMapper {

    @Mapping(target = "userId", source = "userId", qualifiedByName = "uuidToUserId")
    @Mapping(target = "email", source = "email", qualifiedByName = "stringToEmail")
    @Mapping(target = "code", source = "bossCode", qualifiedByName = "stringToBossCode")
    User toDomain(UserEntity entity);

    @Mapping(target = "userId", source = "userId", qualifiedByName = "userIdToUUID")
    @Mapping(target = "email", source = "email", qualifiedByName = "emailToString")
    @Mapping(target = "bossCode", source = "code", qualifiedByName = "bossCodeToString")
    @Mapping(target = "subordinates", ignore = true)
    UserEntity toEntity(User domain);

}
