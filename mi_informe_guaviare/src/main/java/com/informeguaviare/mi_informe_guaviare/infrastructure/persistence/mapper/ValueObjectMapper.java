package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper;

import com.informeguaviare.mi_informe_guaviare.domain.model.value.BossCode;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.Email;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.ReportId;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.UserId;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class ValueObjectMapper {

    @Named("emailToString")
    public String emailToString(Email email) {
        return email != null ? email.getValue() : null;
    }

    @Named("stringToEmail")
    public Email stringToEmail(String email) {
        return email != null ? new Email(email) : null;
    }

    @Named("userIdToUUID")
    public java.util.UUID userIdToUUID(UserId userId) {
        return userId != null ? userId.getValue() : null;
    }

    @Named("uuidToUserId")
    public UserId uuidToUserId(java.util.UUID userId) {
        return userId != null ? new UserId(userId) : null;
    }

    @Named("bossCodeToString")
    public String bossCodeToString(BossCode bossCode) {
        return bossCode != null ? bossCode.getValue() : null;
    }

    @Named("stringToBossCode")
    public BossCode stringToBossCode(String bossCode) {
        return bossCode != null ? BossCode.of(bossCode) : null;
    }

    @Named("reportIdToUUID")
    public java.util.UUID reportIdToUUID(ReportId reportId) {
        return reportId != null ? reportId.getValue() : null;
    }

    @Named("uuidToReportId")
    public ReportId uuidToReportId(java.util.UUID reportId) {
        return reportId != null ? ReportId.of(reportId) : null;
    }

}
