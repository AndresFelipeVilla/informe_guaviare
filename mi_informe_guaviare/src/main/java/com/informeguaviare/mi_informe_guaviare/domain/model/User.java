package com.informeguaviare.mi_informe_guaviare.domain.model;

import com.informeguaviare.mi_informe_guaviare.domain.enums.Role;
import com.informeguaviare.mi_informe_guaviare.domain.exceptions.DomainException;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.BossCode;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.Email;
import com.informeguaviare.mi_informe_guaviare.domain.model.value.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private final UserId userId;
    private String name;
    private Email email;
    private String passwordHash;
    private String position;
    private String department;
    private Role role;
    private BossCode code;
    private User manager;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public User(UserId userId, String name, Email email, String passwordHash, String position, String department,
            Role role, BossCode code, User manager, Boolean active, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.position = position;
        this.department = department;
        this.role = role;
        this.code = code;
        this.manager = manager;
        this.active = active;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public static User createEmployee(String name, String email, String passwordHash, String position,
            String department, User manager) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("El nombre no puede ser nulo o vacío");
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new DomainException("La contraseña no puede ser nula o vacía");
        }
        if (manager == null) {
            throw new DomainException("El empleado debe estar asociado a un jefe");
        }
        LocalDateTime now = LocalDateTime.now();
        return new User(null, name, Email.of(email), passwordHash, position, department, Role.EMPLEADO, null, manager,
                true, now, now);
    }

    public static User createBoss(String name, String email, String passwordHash, String position, String department,
            String bossCode) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("El nombre no puede ser nulo o vacío");
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new DomainException("La constraseña no puede ser nula o vacía");
        }
        if (bossCode == null || bossCode.trim().isEmpty()) {
            throw new DomainException("El código de jefe no puede ser nulo o vacío");
        }
        LocalDateTime now = LocalDateTime.now();
        return new User(null, name, Email.of(email), passwordHash, position, department, Role.JEFE,
                BossCode.of(bossCode), null, true, now, now);
    }

    public void updatePassword(String newPasswordHash) {
        if (newPasswordHash == null || newPasswordHash.trim().isEmpty()) {
            throw new DomainException("La contraseña encriptada no puede ser nula o vacía");
        }
        this.passwordHash = newPasswordHash;
        this.updateAt = LocalDateTime.now();
    }

    public String getBossCode() {
        return this.code != null ? this.code.getValue() : null;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Email getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public Role getRole() {
        return role;
    }

    public BossCode getCode() {
        return code;
    }

    public User getManager() {
        return manager;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}