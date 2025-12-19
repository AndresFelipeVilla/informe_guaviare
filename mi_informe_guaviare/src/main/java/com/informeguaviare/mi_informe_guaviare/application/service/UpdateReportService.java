package com.informeguaviare.mi_informe_guaviare.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.informeguaviare.mi_informe_guaviare.application.command.UpdateReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.ReportNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.UnauthorizedReportAccessException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.UpdateReportUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.ReportRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserAuthenticationPort;

@Service
public class UpdateReportService implements UpdateReportUseCase {

    private final ReportRepositoryOutPort reportRepositoryOutPort;
    private final UserAuthenticationPort userAuthenticationPort;

    public UpdateReportService(ReportRepositoryOutPort reportRepositoryOutPort,
            UserAuthenticationPort userAuthenticationPort) {
        this.reportRepositoryOutPort = reportRepositoryOutPort;
        this.userAuthenticationPort = userAuthenticationPort;
    }

    @Override
    public Report updateReport(UUID reportId, UpdateReportCommand command) {
        UUID userId = userAuthenticationPort.getCurrentAuthenticatedUserId();
        Report report = reportRepositoryOutPort
                .findById(reportId)
                .orElseThrow(() -> new ReportNotFoundException("Reporte no encontrado"));

        if (!report.getEmployee().getUserId().getValue().equals(userId)) {
            throw new UnauthorizedReportAccessException(
                    String.format("El usuario no tiene permiso para modificar el reporte con ID %s. " +
                            "Solo el propietario del reporte puede modificarlo.",
                            reportId));
        }

        report.update(command.getTitle(), command.getDescription(), command.getActivities(),
                command.getObjective(), command.getEvidencieLink());

        Report updatedReport = reportRepositoryOutPort.saveReport(report);
        return updatedReport;
    }

}
