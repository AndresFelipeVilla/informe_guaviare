package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.SendReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.UpdateReportCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.PagedResult;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper.ValueObjectMapper;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = { ValueObjectMapper.class })
public interface ReportMapperDTO {

    @Mapping(target = "employeeId", ignore = true)
    CreateReportCommand toCreateReportCommand(CreateReportRequest request);

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "reportIdToUUID")
    @Mapping(source = "employee.name", target = "responsible")
    @Mapping(source = "status", target = "status")
    ReportResponse toReportResponse(Report report);

    SendReportCommand toSendReportCommand(UUID reportId);

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "reportIdToUUID")
    @Mapping(source = "employee.name", target = "responsible")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "sentIn", target = "sentAt")
    ReportDetailsResponse toReportDetailsResponse(Report report);

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "reportIdToUUID")
    @Mapping(target = "employee", source = "employee.name")
    @Mapping(source = "sentIn", target = "sentAt")
    ReportSummaryResponse toReportSummaryResponse(Report report);

    List<ReportSummaryResponse> toReportListSummary(List<Report> reports);

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "reportIdToUUID")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "sentIn", target = "sentAt")
    ReportListResponse toReportListResponse(Report report);

    List<ReportListResponse> toReportListResponse(List<Report> reports);

    @Mapping(source = "request.title", target = "title")
    @Mapping(source = "request.description", target = "description")
    @Mapping(source = "request.activities", target = "activities")
    @Mapping(source = "request.objective", target = "objective")
    @Mapping(source = "request.evidenceLink", target = "evidenceLink")
    UpdateReportCommand toUpdateReportCommand(UpdateReportRequest request);

    @Mapping(source = "pagedResult.pageNumber", target = "page")
    @Mapping(source = "pagedResult.pageSize", target = "size")
    @Mapping(source = "pagedResult.totalElements", target = "totalElements")
    @Mapping(source = "pagedResult.items", target = "content")
    PagedResponse<ReportListResponse> toPagedReportListResponse(PagedResult<Report> pagedResult);

    @Mapping(source = "pagedResult.pageNumber", target = "page")
    @Mapping(source = "pagedResult.pageSize", target = "size")
    @Mapping(source = "pagedResult.totalElements", target = "totalElements")
    @Mapping(source = "pagedResult.items", target = "content")
    PagedResponse<ReportSummaryResponse> toPagedReportSummaryResponse(PagedResult<Report> pagedResult);

}
