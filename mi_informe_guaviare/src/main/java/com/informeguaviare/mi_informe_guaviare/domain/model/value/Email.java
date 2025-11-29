package com.informeguaviare.mi_informe_guaviare.domain.model.value;

import com.informeguaviare.mi_informe_guaviare.domain.exceptions.DomainException;

public class Email {

    private final String value;

    public Email(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException("El correo electrónico no puede ser nulo o vacío.");
        }
        if (!value.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new DomainException("El correo electrónico no es válido.");
        }
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}