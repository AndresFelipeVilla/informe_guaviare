package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateBossCommand;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.BossCodeExistsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.EmailAlreadyExistsException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateBossUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateBossService implements CreateBossUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;
    private final PasswordEncoder passwordEncoder;

    public CreateBossService(UserRepositoryOutPort userRepositoryOutPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryOutPort = userRepositoryOutPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User createBoss(CreateBossCommand command) {
        if (userRepositoryOutPort.findByBossCode(command.getBossCode()).isPresent()) {
            throw new BossCodeExistsException("Ya existe un jefe con el código " + command.getBossCode());
        }
        if (userRepositoryOutPort.findByEmail(command.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Ya existe un jefe con el correo electrónico " + command.getEmail());
        }
        String encodedPassword = passwordEncoder.encode(command.getPasswordHash());
        User boss = User.createBoss(command.getName(), command.getEmail(), encodedPassword, command.getPosition(), command.getDepartment(), command.getBossCode());
        return userRepositoryOutPort.saveUser(boss);
    }
}