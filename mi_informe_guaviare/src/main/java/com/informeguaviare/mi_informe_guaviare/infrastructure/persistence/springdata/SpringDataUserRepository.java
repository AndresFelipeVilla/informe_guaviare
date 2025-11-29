package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata;

import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByBossCode(String bossCode);

    Optional<UserEntity> findByEmail(String email);
}
