package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateReportCommand;
import com.informeguaviare.mi_informe_guaviare.application.exceptions.UserNotFoundException;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateReportUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.ReportRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import org.springframework.stereotype.Service;

@Service
public class CreateReportService implements CreateReportUseCase {

    private final ReportRepositoryOutPort reportRepositoryOutPort;
    private final UserRepositoryOutPort userRepositoryOutPort;

    public CreateReportService(ReportRepositoryOutPort reportRepositoryOutPort,
            UserRepositoryOutPort userRepositoryOutPort) {
        this.reportRepositoryOutPort = reportRepositoryOutPort;
        this.userRepositoryOutPort = userRepositoryOutPort;
    }

    @Override
    public Report createReport(CreateReportCommand createReportCommand) {
        User user = userRepositoryOutPort.findById(createReportCommand.getEmployeeId())
                .orElseThrow(() -> new UserNotFoundException("El empleado no existe"));
        Report report = Report.create(createReportCommand.getTitle(), createReportCommand.getDescription(),
                createReportCommand.getActivities(), createReportCommand.getObjective(),
                createReportCommand.getEvidenceLink(),
                user);
        return reportRepositoryOutPort.saveReport(report);
    }
}
