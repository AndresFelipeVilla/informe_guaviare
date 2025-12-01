package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.exceptions.InvalidCredentialsException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.LoginUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepositoryOutPort userRepositoryOutPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryOutPort = userRepositoryOutPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) {
        User user = userRepositoryOutPort.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("El correo electrónico no existe"));
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException("La contraseña es incorrecta");
        }
        return user;
    }
}
