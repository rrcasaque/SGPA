package com.example.sgpa.domain.entities.part;

import java.util.Objects;

public class Part {
    private int id;
    private String description;
    private int maxDaysCheckedOutForStudent;
    private int maxDaysCheckedOutForProfessor;

    public Part() {
    }

    public Part(String description, int maxDaysCheckedOutForStudent, int maxDaysCheckedOutForProfessor) {
        this.id = id;
        this.description = description;
        this.maxDaysCheckedOutForStudent = maxDaysCheckedOutForStudent;
        this.maxDaysCheckedOutForProfessor = maxDaysCheckedOutForProfessor;
    }

    public Part(int id, String description, int maxDaysCheckedOutForStudent, int maxDaysCheckedOutForProfessor) {
        this(description, maxDaysCheckedOutForStudent, maxDaysCheckedOutForProfessor);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
