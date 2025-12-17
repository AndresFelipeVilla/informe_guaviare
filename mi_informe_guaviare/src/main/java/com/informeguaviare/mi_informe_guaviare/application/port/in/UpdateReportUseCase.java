package com.informeguaviare.mi_informe_guaviare.application.port.in;

import java.util.UUID;

import com.informeguaviare.mi_informe_guaviare.application.command.UpdateReportCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;

public interface UpdateReportUseCase {

    Report updateReport(UUID reportId, UpdateReportCommand command);
}