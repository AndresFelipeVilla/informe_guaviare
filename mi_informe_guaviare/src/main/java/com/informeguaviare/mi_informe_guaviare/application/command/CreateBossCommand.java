package com.informeguaviare.mi_informe_guaviare.application.command;

public class CreateBossCommand {
    private String name;
    private String email;
    private String password;
    private String position;
    private String department;
    private String bossCode;

    public CreateBossCommand() {
    }

    public CreateBossCommand(String name, String email, String password, String position, String department,
            String bossCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.position = position;
        this.department = department;
        this.bossCode = bossCode;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBossCode() {
        return bossCode;
    }

    public void setBossCode(String bossCode) {
        this.bossCode = bossCode;
    }
}