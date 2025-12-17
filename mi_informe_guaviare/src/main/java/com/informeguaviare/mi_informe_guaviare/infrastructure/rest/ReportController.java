package com.informeguaviare.mi_informe_guaviare.infrastructure.rest;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.SendReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.UpdateReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.port.in.*;
import com.informeguaviare.mi_informe_guaviare.domain.model.PagedResult;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.*;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper.ReportMapperDTO;
import com.informeguaviare.mi_informe_guaviare.infrastructure.security.CustomUserDetails;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final CreateReportUseCase createReportUseCase;
    private final ReportMapperDTO reportMapperDTO;
    private final SendReportUseCase sendReportUseCase;
    private final DeleteReportUseCase deleteReportUseCase;
    private final GetReportsByFilterUseCase getReportsByFilterUseCase;
    private final UpdateReportUseCase updateReportUseCase;

    public ReportController(CreateReportUseCase createReportUseCase, ReportMapperDTO reportMapperDTO,
            SendReportUseCase sendReportUseCase,
            DeleteReportUseCase deleteReportUseCase, GetReportsByFilterUseCase getReportsByFilterUseCase,
            UpdateReportUseCase updateReportUseCase) {
        this.createReportUseCase = createReportUseCase;
        this.reportMapperDTO = reportMapperDTO;
        this.sendReportUseCase = sendReportUseCase;
        this.deleteReportUseCase = deleteReportUseCase;
        this.getReportsByFilterUseCase = getReportsByFilterUseCase;
        this.updateReportUseCase = updateReportUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReportResponse> createReport(@Valid @RequestBody CreateReportRequest reportRequest,
            Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = UUID.fromString(customUserDetails.getUserId());
        CreateReportCommand createReportCommand = reportMapperDTO.toCreateReportCommand(reportRequest);
        createReportCommand.setEmployeeId(userId);
        Report report = createReportUseCase.createReport(createReportCommand);
        return ResponseEntity.ok(reportMapperDTO.toReportResponse(report));
    }

    @PostMapping("/{reportId}/submissions")
    public ResponseEntity<ReportDetailsResponse> sendReport(@PathVariable UUID reportId) {
        SendReportCommand sendReportCommand = reportMapperDTO.toSendReportCommand(reportId);
        Report report = sendReportUseCase.sendReport(sendReportCommand);
        ReportDetailsResponse reportDetailsResponse = reportMapperDTO.toReportDetailsResponse(report);
        return ResponseEntity.ok(reportDetailsResponse);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ReportListResponse>> getReport(@RequestParam(required = false) String status,
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = customUserDetails.getUserId();
        String userRole = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .orElse("");
        ReportFilter reportFilter = new ReportFilter(status, query, userRole, userId, page, size);
        PagedResult<Report> pagedResult = getReportsByFilterUseCase.getReportsByFilter(reportFilter);
        PagedResponse<ReportListResponse> pagedResponse = reportMapperDTO.toPagedReportListResponse(pagedResult);
        return ResponseEntity.ok(pagedResponse);

    }

    @GetMapping("/summary")
    public ResponseEntity<PagedResponse<ReportSummaryResponse>> getReportsSummary(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = customUserDetails.getUserId();
        String userRole = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .orElse("");
        ReportFilter filter = new ReportFilter(status, query, userRole, userId, page, size);
        PagedResult<Report> pagedResult = getReportsByFilterUseCase.getReportsByFilter(filter);
        PagedResponse<ReportSummaryResponse> pagedResponse = reportMapperDTO.toPagedReportSummaryResponse(pagedResult);
        return ResponseEntity.ok(pagedResponse);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable UUID reportId) {
        deleteReportUseCase.deleteReport(reportId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<ReportResponse> updateReport(
            @PathVariable UUID reportId,
            @Valid @RequestBody UpdateReportRequest request) {

        UpdateReportCommand command = reportMapperDTO.toUpdateReportCommand(request);
        Report updatedReport = updateReportUseCase.updateReport(reportId, command);
        return ResponseEntity.ok(reportMapperDTO.toReportResponse(updatedReport));
    }

}
