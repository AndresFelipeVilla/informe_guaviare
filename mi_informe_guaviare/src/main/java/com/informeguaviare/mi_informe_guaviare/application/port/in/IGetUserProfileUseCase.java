package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;

public interface IGetUserProfileUseCase {
    User getProfile(String userId);
}