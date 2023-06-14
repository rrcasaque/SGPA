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

    public static UserType strToEnum(String userType){
      return Arrays.stream(UserType.values()).filter(ut -> ut.toString().equals(userType)).findAny().orElseThrow();
    }

    @Override
    public String toString() {
        return label;
    }



}
