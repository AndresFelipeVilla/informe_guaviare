package com.informeguaviare.mi_informe_guaviare.application.service;

import com.informeguaviare.mi_informe_guaviare.application.port.in.GetReportsByFilterUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.PagedResult;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.ReportRepositoryOutPort;
import org.springframework.stereotype.Service;

@Service
public class ReportFilterService implements GetReportsByFilterUseCase {

    private final ReportRepositoryOutPort reportRepositoryOutPort;

    public ReportFilterService(ReportRepositoryOutPort reportRepositoryOutPort) {
        this.reportRepositoryOutPort = reportRepositoryOutPort;
    }

    @Override
    public PagedResult<Report> getReportsByFilter(ReportFilter filter) {
        return reportRepositoryOutPort.findByFilter(filter);
    }
}
