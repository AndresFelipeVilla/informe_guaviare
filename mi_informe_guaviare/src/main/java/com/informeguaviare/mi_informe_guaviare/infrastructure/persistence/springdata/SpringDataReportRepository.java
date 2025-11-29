package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata;

import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SpringDataReportRepository extends JpaRepository<ReportEntity, UUID> , JpaSpecificationExecutor<ReportEntity> {
}
