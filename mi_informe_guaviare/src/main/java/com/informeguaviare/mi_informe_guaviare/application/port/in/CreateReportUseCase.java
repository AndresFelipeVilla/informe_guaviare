package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateReportCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;

public interface CreateReportUseCase {

    Report createReport(CreateReportCommand createReportCommand);

}
