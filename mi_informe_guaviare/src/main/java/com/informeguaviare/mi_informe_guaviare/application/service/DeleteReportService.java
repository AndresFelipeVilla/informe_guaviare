package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.exceptions.ReportNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.DeleteReportUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.enums.ReportStatus;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.ReportRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserAuthenticationPort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteReportService implements DeleteReportUseCase {

    private final ReportRepositoryOutPort reportRepositoryOutPort;
    private final UserAuthenticationPort userAuthenticationPort;

    public DeleteReportService(ReportRepositoryOutPort reportRepositoryOutPort, UserAuthenticationPort userAuthenticationPort){
        this.reportRepositoryOutPort = reportRepositoryOutPort;
        this.userAuthenticationPort = userAuthenticationPort;
    }

    @Override
    public void deleteReport(UUID reportId) {
        UUID userId = userAuthenticationPort.getCurrentAuthenticatedUserId();
        Report report = reportRepositoryOutPort.findById(reportId)
                .orElseThrow(()-> new ReportNotFoundException("Report no encontrado"));
        if (!report.getReportId().equals(userId)){
            throw new AccessDeniedException("No tienes permiso para eliminar este reporte");
        }
        if (report.getStatus() != ReportStatus.BORRADOR){
            throw new IllegalStateException("Solo se puede eliminar un reporte que está en estado de borrador (BORRADOR).");
        }
        reportRepositoryOutPort.deleteById(reportId);
    }
}
