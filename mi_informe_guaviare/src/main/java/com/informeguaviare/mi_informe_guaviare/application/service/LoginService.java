package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.exceptions.InvalidCredentialsException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.LoginUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.PasswordEncryptionOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;
    private final PasswordEncryptionOutPort passwordEncryptionOutPort;

    public LoginService(UserRepositoryOutPort userRepositoryOutPort,
            PasswordEncryptionOutPort passwordEncryptionOutPort) {
        this.userRepositoryOutPort = userRepositoryOutPort;
        this.passwordEncryptionOutPort = passwordEncryptionOutPort;
    }

    @Override
    public User login(String email, String password) {
        User user = userRepositoryOutPort.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("El correo electrónico no existe"));
        if (!passwordEncryptionOutPort.checkPassword(password, user.getPassword())) {
            throw new InvalidCredentialsException("La contraseña es incorrecta");
        }
        return user;
    }
}
