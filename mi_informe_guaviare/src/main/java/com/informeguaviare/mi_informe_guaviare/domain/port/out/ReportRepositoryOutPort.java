package com.informeguaviare.mi_informe_guaviare.domain.port.out;

import com.informeguaviare.mi_informe_guaviare.domain.model.PagedResult;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;

import java.util.Optional;
import java.util.UUID;

public interface ReportRepositoryOutPort {

    Report saveReport(Report report);

    Optional<Report> findById(UUID reportId);

    void deleteById(UUID reportId);

    PagedResult<Report> findByFilter(ReportFilter filter);
}
