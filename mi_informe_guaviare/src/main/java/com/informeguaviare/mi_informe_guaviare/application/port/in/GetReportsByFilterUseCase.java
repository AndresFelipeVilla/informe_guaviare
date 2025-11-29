package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;

import java.util.List;

public interface GetReportsByFilterUseCase {

    List<Report> getReportsByFilter(ReportFilter filter);

}
