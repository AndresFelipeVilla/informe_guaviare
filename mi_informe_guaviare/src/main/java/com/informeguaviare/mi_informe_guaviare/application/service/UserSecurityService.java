package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.port.in.UserSecurityUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;

import java.util.Optional;

public class UserSecurityService implements UserSecurityUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;

    public UserSecurityService(UserRepositoryOutPort userRepositoryOutPort) {
        this.userRepositoryOutPort = userRepositoryOutPort;
    }

    @Override
    public Optional<User> loadUserByEmail(String email) {
        return userRepositoryOutPort.findByEmail(email);
    }
}
