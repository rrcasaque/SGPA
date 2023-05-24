package com.example.sgpa.domain.entities.user;

public class User {
    private String institutionalId;
    private String name;
    private String email;
    private String phone;
    public User() {
    }

    public User(String institutionalId) {
        this.institutionalId = institutionalId;
    }

    public User(String institutionalId, String name, String email, String phone) {
        this.institutionalId = institutionalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getInstitutionalId() {
        return institutionalId;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
