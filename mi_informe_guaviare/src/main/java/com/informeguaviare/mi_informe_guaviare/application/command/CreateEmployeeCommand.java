package com.informeguaviare.mi_informe_guaviare.application.command;

public class CreateEmployeeCommand {
    private String name;
    private String email;
    private String passwordHash;
    private String position;
    private String department;
    private String managerBossCode;

    public CreateEmployeeCommand() {
    }

    public CreateEmployeeCommand(String name, String email, String passwordHash, String position, String department, String managerBossCode) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.position = position;
        this.department = department;
        this.managerBossCode = managerBossCode;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManagerBossCode() {
        return managerBossCode;
    }

    public void setManagerBossCode(String managerBossCode) {
        this.managerBossCode = managerBossCode;
    }
}