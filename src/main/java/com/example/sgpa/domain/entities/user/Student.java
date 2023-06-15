package com.example.sgpa.domain.entities.user;

public class Student extends User {
    public Student() {
    }

    public Student(int institutionalId) {
        super(institutionalId);
    }

    public Student(int institutionalId, String name, String email, String phone) {
        super(institutionalId, name, email, phone, UserType.Student.toString());
    }
}
