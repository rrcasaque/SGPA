package com.example.sgpa.domain.entities.user;

public class Technician extends User {
    private String login;
    private String password;

    public Technician(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Technician(int institutionalId, String login, String password) {
        super(institutionalId);
        this.login = login;
        this.password = password;
    }

    public Technician(int institutionalId, String name, String email, String phone, String login, String password) {
        super(institutionalId, name, email, phone, UserType.TECHNICIAN.toString(), login, password);
        this.login = login;
        this.password = password;
    }

}
