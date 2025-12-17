package com.informeguaviare.mi_informe_guaviare.domain.port.out;

public interface PasswordEncryptionOutPort {

    boolean checkPassword(String password, String encodedPassword);

    String encodePassword(String password);

}
