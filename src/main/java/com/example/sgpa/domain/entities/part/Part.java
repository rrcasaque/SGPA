package com.example.sgpa.domain.entities.part;

import java.util.Objects;

public class Part {
    private int id;
    private String type;
    private int maxDaysCheckedOutForStudent;
    private int maxDaysCheckedOutForProfessor;

    public Part() {
    }

    public Part(String type, int maxDaysCheckedOutForStudent, int maxDaysCheckedOutForProfessor) {
        this.type = type;
        this.maxDaysCheckedOutForStudent = maxDaysCheckedOutForStudent;
        this.maxDaysCheckedOutForProfessor = maxDaysCheckedOutForProfessor;
    }

    public Part(int id, String type, int maxDaysCheckedOutForStudent, int maxDaysCheckedOutForProfessor) {
        this(type, maxDaysCheckedOutForStudent, maxDaysCheckedOutForProfessor);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxDaysCheckedOutForStudent() {
        return maxDaysCheckedOutForStudent;
    }

    public void setMaxDaysCheckedOutForStudent(int maxDaysCheckedOutForStudent) {
        this.maxDaysCheckedOutForStudent = maxDaysCheckedOutForStudent;
    }

    public int getMaxDaysCheckedOutForProfessor() {
        return maxDaysCheckedOutForProfessor;
    }

    public void setMaxDaysCheckedOutForProfessor(int maxDaysCheckedOutForProfessor) {
        this.maxDaysCheckedOutForProfessor = maxDaysCheckedOutForProfessor;
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id && type.equals(part.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
