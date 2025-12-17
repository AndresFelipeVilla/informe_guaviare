package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.command.SendReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.ReportNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.SendReportUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.ReportRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserAuthenticationPort;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SendReportService implements SendReportUseCase {

    private final ReportRepositoryOutPort reportRepositoryOutPort;
    private final UserAuthenticationPort userAuthenticationPort;

    public SendReportService(ReportRepositoryOutPort reportRepositoryOutPort,
            UserAuthenticationPort userAuthenticationPort) {
        this.reportRepositoryOutPort = reportRepositoryOutPort;
        this.userAuthenticationPort = userAuthenticationPort;
    }

    @Override
    @Transactional
    public Report sendReport(SendReportCommand reportCommand) {

        UUID authenticatedUserId = userAuthenticationPort.getCurrentAuthenticatedUserId();

        Report report = reportRepositoryOutPort.findById(reportCommand.getReportId())
                .orElseThrow(() -> new ReportNotFoundException("El reporte no existe"));

        UUID reportOwnerId = report.getEmployee().getUserId().getValue();

        if (!reportOwnerId.equals(authenticatedUserId)) {
            throw new AccessDeniedException("No tienes permiso para enviar este reporte. Pertenece a otro usuario.");
        }

        report.send();
        return reportRepositoryOutPort.saveReport(report);
    }

}
