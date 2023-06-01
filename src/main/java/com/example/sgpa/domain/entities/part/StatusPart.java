package com.example.sgpa.domain.entities.part;

import com.example.sgpa.domain.entities.user.UserType;

import java.util.Arrays;

public enum StatusPart {
    AVAILABLE("Disponível"),
    CHECKED_OUT("Emprestada"),
    RESERVED("Reservada");

    private final String label;

    StatusPart(String lable){
        this.label = lable;
    }

    public static StatusPart strToEnum(String status){
        return Arrays.stream(StatusPart.values()).filter(ps -> ps.toString().equals(status)).findAny().orElseThrow();
    }
    @Override
    public String toString() {
        return label;
    }

}
