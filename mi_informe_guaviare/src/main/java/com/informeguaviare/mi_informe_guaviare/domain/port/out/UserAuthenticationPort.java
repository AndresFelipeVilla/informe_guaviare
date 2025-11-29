package com.informeguaviare.mi_informe_guaviare.domain.port.out;

import java.util.UUID;

public interface UserAuthenticationPort {
    UUID getCurrentAuthenticatedUserId();
}
