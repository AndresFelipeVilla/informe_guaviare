package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.repository;

import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.ReportRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata.ReportSpecification;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata.SpringDataReportRepository;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.ReportEntity;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper.ReportPersistenceMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaReportRepositoryAdapter implements ReportRepositoryOutPort {

    private final SpringDataReportRepository springDataReportRepository;
    private final ReportPersistenceMapper reportPersistenceMapper;

    public JpaReportRepositoryAdapter(SpringDataReportRepository springDataReportRepository, ReportPersistenceMapper reportPersistenceMapper) {
        this.springDataReportRepository = springDataReportRepository;
        this.reportPersistenceMapper = reportPersistenceMapper;
    }

    @Override
    public Report saveReport(Report report) {
        ReportEntity reportEntity = reportPersistenceMapper.toEntity(report);
        ReportEntity savedEntity = springDataReportRepository.save(reportEntity);
        return reportPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Report> findById(UUID reportId) {
        return springDataReportRepository.findById(reportId)
                .map(reportPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID reportId) {
        springDataReportRepository.deleteById(reportId);
    }

    @Override
    public List<Report> findByFilter(ReportFilter filter) {
        Specification<ReportEntity> spec = ReportSpecification.byFilter(filter);
        List<ReportEntity> entities = springDataReportRepository.findAll(spec);
        return entities.stream().map(reportPersistenceMapper::toDomain).toList();
    }






}
