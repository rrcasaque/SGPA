package com.example.sgpa.domain.entities.user;

public class Professor extends User {
    private int room;

    public Professor(){
    }

    public Professor(int institutionalId, int room) {
        super(institutionalId);
        this.room = room;
    }

    public Professor(int institutionalId, String name, String email, String phone, int room) {
        super(institutionalId, name, email, phone, UserType.PROFESSOR.toString(), room);
        this.room = room;
    }

    public int getRoom() {
        return this.room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
