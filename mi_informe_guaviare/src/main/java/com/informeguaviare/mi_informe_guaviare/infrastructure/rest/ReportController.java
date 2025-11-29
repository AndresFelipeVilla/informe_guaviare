package com.informeguaviare.mi_informe_guaviare.infrastructure.rest;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.SendReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.port.in.*;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.*;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper.ReportMapperDTO;
import com.informeguaviare.mi_informe_guaviare.infrastructure.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final CreateReportUseCase createReportUseCase;
    private final ReportMapperDTO reportMapperDTO;
    private final SendReportUseCase sendReportUseCase;
    private final DeleteReportUseCase deleteReportUseCase;
    private final GetReportsByFilterUseCase getReportsByFilterUseCase;


    public ReportController( CreateReportUseCase createReportUseCase, ReportMapperDTO reportMapperDTO,
                             SendReportUseCase sendReportUseCase,
                             DeleteReportUseCase deleteReportUseCase, GetReportsByFilterUseCase getReportsByFilterUseCase){
        this.createReportUseCase = createReportUseCase;
        this.reportMapperDTO = reportMapperDTO;
        this.sendReportUseCase = sendReportUseCase;
        this.deleteReportUseCase = deleteReportUseCase;
        this.getReportsByFilterUseCase = getReportsByFilterUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<ReportResponse> createReport(@Valid @RequestBody CreateReportRequest reportRequest, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = UUID.fromString(customUserDetails.getUserId());
        CreateReportCommand createReportCommand = reportMapperDTO.toCreateReportCommand(reportRequest);
        createReportCommand.setEmployeeId(userId);
        Report report = createReportUseCase.createReport(createReportCommand);
        return ResponseEntity.ok(reportMapperDTO.toReportResponse(report));
    }

    @PostMapping("/{reportId}/send")
    public ResponseEntity<ReportDetailsResponse> sendReport(@PathVariable UUID reportId){
        SendReportCommand sendReportCommand = reportMapperDTO.toSendReportCommand(reportId);
        Report report = sendReportUseCase.SendReport(sendReportCommand);
        ReportDetailsResponse reportDetailsResponse = reportMapperDTO.toReportDetailsResponse(report);
        return ResponseEntity.ok(reportDetailsResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReportListResponse>> getReport(@RequestParam(required = false) String status,
                                                              @RequestParam(required = false) String query,
                                                              Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = customUserDetails.getUserId();
        String userRole = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .orElse("");
        ReportFilter reportFilter = new ReportFilter(status, query, userRole, userId);
        List<Report> reports = getReportsByFilterUseCase.getReportsByFilter(reportFilter);
        return ResponseEntity.ok(reportMapperDTO.toReportListResponse(reports));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<ReportSummaryResponse>> getReportsSummary(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String query,
            Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = customUserDetails.getUserId();
        String userRole = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .orElse("");
        ReportFilter filter = new ReportFilter(status, query, userRole, userId);
        List<Report> reports = getReportsByFilterUseCase.getReportsByFilter(filter);
        return ResponseEntity.ok(reportMapperDTO.toReportListSummary(reports));
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable UUID reportId){
        deleteReportUseCase.deleteReport(reportId);
        return ResponseEntity.noContent().build();
    }



}
