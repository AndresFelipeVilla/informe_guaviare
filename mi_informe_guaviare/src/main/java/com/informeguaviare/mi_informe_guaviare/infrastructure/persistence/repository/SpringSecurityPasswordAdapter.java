package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.repository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.informeguaviare.mi_informe_guaviare.domain.port.out.PasswordEncryptionOutPort;

@Component
public class SpringSecurityPasswordAdapter implements PasswordEncryptionOutPort {

    private final PasswordEncoder passwordEncoder;

    public SpringSecurityPasswordAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
