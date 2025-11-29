package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateEmployeeCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;

public interface CreateEmployeeUseCase {
    User createEmployee(CreateEmployeeCommand command);
}