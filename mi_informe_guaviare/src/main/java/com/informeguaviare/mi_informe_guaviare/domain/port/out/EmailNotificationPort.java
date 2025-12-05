package com.informeguaviare.mi_informe_guaviare.domain.port.out;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;

public interface EmailNotificationPort {

    void sendPasswordResetEmail(User user, String resetToken);

}
