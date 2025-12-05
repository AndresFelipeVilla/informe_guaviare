package com.informeguaviare.mi_informe_guaviare.domain.model.value;

import com.informeguaviare.mi_informe_guaviare.domain.exceptions.DomainException;

import java.util.Objects;
import java.util.regex.Pattern;

public class PasswordStrength {

    private static final int MIN_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern
            .compile(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

    private final String value;

    private PasswordStrength(String value) {
        this.value = value;
    }

    public static PasswordStrength of(String password) {
        validate(password);
        return new PasswordStrength(password);
    }

    private static void validate(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new DomainException("La contraseña no puede estar vacía");
        }

        if (password.length() < MIN_LENGTH) {
            throw new DomainException(
                    String.format("La contraseña debe tener al menos %d caracteres", MIN_LENGTH));
        }

        if (!UPPERCASE_PATTERN.matcher(password).matches()) {
            throw new DomainException("La contraseña debe contener al menos una letra mayúscula");
        }

        if (!LOWERCASE_PATTERN.matcher(password).matches()) {
            throw new DomainException("La contraseña debe contener al menos una letra minúscula");
        }

        if (!DIGIT_PATTERN.matcher(password).matches()) {
            throw new DomainException("La contraseña debe contener al menos un número");
        }

        if (!SPECIAL_CHAR_PATTERN.matcher(password).matches()) {
            throw new DomainException(
                    "La contraseña debe contener al menos un carácter especial (!@#$%^&*()_+-=[]{};\':\"\\|,.<>/?)");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PasswordStrength that = (PasswordStrength) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
