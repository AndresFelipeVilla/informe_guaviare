package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities;

import com.informeguaviare.mi_informe_guaviare.domain.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reports")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reportId;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String activities;
    @Column(columnDefinition = "TEXT")
    private String objetivo;
    private String linkDeEvidencia;
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime sentIn;
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private UserEntity employee;

}
