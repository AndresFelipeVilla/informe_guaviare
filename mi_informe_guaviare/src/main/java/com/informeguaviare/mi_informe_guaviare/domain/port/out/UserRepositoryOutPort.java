package com.informeguaviare.mi_informe_guaviare.domain.port.out;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryOutPort {
    User saveUser(User user);
    Optional<User> findById(UUID userId);
    Optional<User> findByBossCode(String bossCode);
    Optional<User> findByEmail(String email);

}
