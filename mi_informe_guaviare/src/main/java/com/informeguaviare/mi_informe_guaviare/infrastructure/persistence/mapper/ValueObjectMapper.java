package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper;

import com.informeguaviare.mi_informe_guaviare.domain.model.value.BossCode;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.Email;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.ReportId;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.UserId;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ValueObjectMapper {

    @Named("emailToString")
    default String emailToString(Email email) {
        return email != null ? email.getValue() : null;
    }

    @Named("stringToEmail")
    default Email stringToEmail(String email) {
        return email != null ? new Email(email) : null;
    }

    @Named("userIdToUUID")
    default java.util.UUID userIdToUUID(UserId userId) {
        return userId != null ? userId.getValue() : null;
    }

    @Named("uuidToUserId")
    default UserId uuidToUserId(java.util.UUID userId) {
        return userId != null ? new UserId(userId) : null;
    }

    @Named("bossCodeToString")
    default String bossCodeToString(BossCode bossCode) {
        return bossCode != null ? bossCode.getValue() : null;
    }

    @Named("stringToBossCode")
    default BossCode stringToBossCode(String bossCode) {
        return bossCode != null ? BossCode.of(bossCode) : null;
    }

    @Named("reportIdToUUID")
    default java.util.UUID reportIdToUUID(ReportId reportId) {
        return reportId != null ? reportId.getValue() : null;
    }

    @Named("uuidToReportId")
    default ReportId uuidToReportId(java.util.UUID reportId) {
        return reportId != null ? ReportId.of(reportId) : null;
    }

}
