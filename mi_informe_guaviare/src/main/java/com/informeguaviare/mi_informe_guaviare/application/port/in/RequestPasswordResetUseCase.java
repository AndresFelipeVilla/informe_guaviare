package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.application.command.RequestPasswordResetCommand;

public interface RequestPasswordResetUseCase {

    void requestPasswordReset(RequestPasswordResetCommand command);

}
