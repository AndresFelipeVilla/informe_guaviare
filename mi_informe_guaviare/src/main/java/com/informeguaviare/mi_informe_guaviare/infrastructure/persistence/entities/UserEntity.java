package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities;

import com.informeguaviare.mi_informe_guaviare.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String department;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(unique = true)
    private String bossCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private UserEntity manager;
    @OneToMany(mappedBy = "manager")
    private Set<UserEntity> subordinates;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
