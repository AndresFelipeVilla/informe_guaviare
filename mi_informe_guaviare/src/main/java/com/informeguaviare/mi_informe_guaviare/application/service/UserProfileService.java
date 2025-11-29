package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.port.in.IGetUserProfileUseCase;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.UserNotFoundException;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserProfileService implements IGetUserProfileUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;

    public UserProfileService(UserRepositoryOutPort userRepositoryOutPort) {
        this.userRepositoryOutPort = userRepositoryOutPort;
    }

    @Override
    public User getProfile(String userId) {
        UUID id = UUID.fromString(userId);
        return userRepositoryOutPort.findById(id)
                .orElseThrow(()->new UserNotFoundException("Usuario no encontrado con el id: "+ id));
    }
}