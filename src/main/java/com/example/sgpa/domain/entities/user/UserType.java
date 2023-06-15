package com.example.sgpa.domain.entities.user;

import java.util.ArrayList;
import java.util.Arrays;

public enum UserType {
    PROFESSOR("Professor"),
    TECHNICIAN("Técnico"),
    Student("Estudante");
    private final String label;
    UserType(String lable){
        this.label = lable;
    }

    @Override
    public String toString() {
        return label;
    }



}
