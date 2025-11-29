package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;

import java.util.Optional;

public interface UserSecurityUseCase {
    Optional<User> loadUserByEmail(String email);
}
