package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;

public interface LoginUseCase {
    User login(String email, String password);
}
