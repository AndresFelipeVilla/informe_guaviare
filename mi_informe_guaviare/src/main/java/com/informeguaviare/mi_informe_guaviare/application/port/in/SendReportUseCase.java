package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.application.command.SendReportCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;

public interface SendReportUseCase {

    Report sendReport(SendReportCommand reportCommand);
}
