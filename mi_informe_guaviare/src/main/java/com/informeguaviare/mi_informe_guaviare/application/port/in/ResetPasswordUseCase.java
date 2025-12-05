package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.application.command.ResetPasswordCommand;

public interface ResetPasswordUseCase {

    void resetPassword(ResetPasswordCommand command);

}
