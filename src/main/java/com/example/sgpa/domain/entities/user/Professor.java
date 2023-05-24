package com.example.sgpa.domain.entities.user;

public class Professor extends User {
    private String room;

    public Professor(){
    }

    public Professor(String institutionalId, String room) {
        super(institutionalId);
        this.room = room;
    }

    public Professor(String institutionalId, String name, String email, String phone, String room) {
        super(institutionalId, name, email, phone);
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
