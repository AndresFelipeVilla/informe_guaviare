package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateEmployeeCommand;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.EmailAlreadyExistsException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.ManagerNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateEmployeeUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.PasswordEncryptionOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements CreateEmployeeUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;
    private final PasswordEncryptionOutPort passwordEncryptionOutPort;

    public CreateEmployeeService(UserRepositoryOutPort userRepositoryOutPort,
            PasswordEncryptionOutPort passwordEncryptionOutPort) {
        this.userRepositoryOutPort = userRepositoryOutPort;
        this.passwordEncryptionOutPort = passwordEncryptionOutPort;
    }

    @Override
    @Transactional
    public User createEmployee(CreateEmployeeCommand command) {
        User manager = userRepositoryOutPort.findByBossCode(command.getManagerBossCode())
                .orElseThrow(() -> new ManagerNotFoundException(
                        "El jefe con código " + command.getManagerBossCode() + " no existe."));
        if (userRepositoryOutPort.findByEmail(command.getEmail().toLowerCase()).isPresent()) {
            throw new EmailAlreadyExistsException(
                    "Ya existe un empleado con el correo electrónico " + command.getEmail());
        }
        String encodedPassword = passwordEncryptionOutPort.encodePassword(command.getPassword());
        User employee = User.createEmployee(command.getName(), command.getEmail().toLowerCase(), encodedPassword,
                command.getPosition(), command.getDepartment(), manager);
        return userRepositoryOutPort.saveUser(employee);
    }
}