package com.informeguaviare.mi_informe_guaviare.application.port.in;

import com.informeguaviare.mi_informe_guaviare.domain.model.PagedResult;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;

public interface GetReportsByFilterUseCase {

    PagedResult<Report> getReportsByFilter(ReportFilter filter);

}
