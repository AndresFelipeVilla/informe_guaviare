package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.SendReportCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper.ValueObjectMapper;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring", uses = {ValueObjectMapper.class})
public interface ReportMapperDTO {

    CreateReportCommand toCreateReportCommand(CreateReportRequest request);

    @Mapping(source = "employee.name", target = "responsible")
    @Mapping(source = "status", target = "status")
    ReportResponse toReportResponse(Report report);

    SendReportCommand toSendReportCommand(UUID reportId);

    @Mapping(source = "employee.name", target = "responsible")
    @Mapping(source = "status", target = "status")
    ReportDetailsResponse toReportDetailsResponse(Report report);

    @Mapping(target = "employee", source = "employee.name")
    ReportSummaryResponse toReportSummaryResponse(Report report);
    List<ReportSummaryResponse> toReportListSummary(List<Report> reports);

    List<ReportListResponse> toReportListResponse(List<Report> reports);
}
