package com.informeguaviare.mi_informe_guaviare.domain.model.value;

import com.informeguaviare.mi_informe_guaviare.domain.exceptions.DomainException;

import java.util.Objects;
import java.util.UUID;

public class ReportId {

    private final UUID value;

    public ReportId(UUID value){
        if (value == null){
            throw new DomainException("reportId no puede ser nulo");
        }

        this.value = value;
    }

    public static ReportId of(UUID value) {
        return new ReportId(value);
    }

    public static ReportId generate() {
        return new ReportId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReportId reportId = (ReportId) o;
        return Objects.equals(value, reportId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
