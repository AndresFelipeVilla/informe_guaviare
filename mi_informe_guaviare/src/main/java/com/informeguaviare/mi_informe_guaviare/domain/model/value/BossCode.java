package com.informeguaviare.mi_informe_guaviare.domain.model.value;

import com.informeguaviare.mi_informe_guaviare.domain.exceptions.DomainException;

public class BossCode {

    private final String value;

    private BossCode(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException("BossCode cannot be null or empty");
        }
        this.value = value;
    }

    public static BossCode of(String value) {
        return new BossCode(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BossCode bossCode = (BossCode) o;
        return value.equals(bossCode.value);
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