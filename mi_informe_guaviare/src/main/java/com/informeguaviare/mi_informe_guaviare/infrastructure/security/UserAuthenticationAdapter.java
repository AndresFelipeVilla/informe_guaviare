package com.informeguaviare.mi_informe_guaviare.infrastructure.security;

import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserAuthenticationPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAuthenticationAdapter implements UserAuthenticationPort {

    @Override
    public UUID getCurrentAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new IllegalStateException("No se encontró un usuario autenticado o el tipo de 'principal' es incorrecto.");
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return UUID.fromString(userDetails.getUserId());
    }
}
