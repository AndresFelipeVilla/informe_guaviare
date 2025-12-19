package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper;

import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.ReportId;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.ReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = { ValueObjectMapper.class, UserPersistenceMapper.class })
public abstract class ReportPersistenceMapper {

    @Autowired
    protected UserPersistenceMapper userMapper;

    public Report toDomain(ReportEntity reportEntity) {
        if (reportEntity == null) {
            return null;
        }

        return Report.load(
                (reportEntity.getReportId() == null) ? null : new ReportId(reportEntity.getReportId()),
                reportEntity.getTitle(),
                reportEntity.getDescription(),
                reportEntity.getActivities(),
                reportEntity.getObjective(),
                reportEntity.getEvidencieLink(),
                reportEntity.getStatus(),
                (reportEntity.getEmployee() == null) ? null : userMapper.toDomain(reportEntity.getEmployee()),
                reportEntity.getSentIn(),
                reportEntity.getCreatedAt(),
                reportEntity.getUpdatedAt());
    }

    @Mapping(target = "reportId", source = "reportId", qualifiedByName = "reportIdToUUID")
    public abstract ReportEntity toEntity(Report report);
}
