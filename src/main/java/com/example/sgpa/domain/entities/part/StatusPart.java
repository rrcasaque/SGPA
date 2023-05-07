package com.example.sgpa.domain.entities.part;

public enum StatusPart {
    AVAILABLE("Disponível"),
    CHECKED_OUT("Emprestada"),
    RESERVED("Reservada");

    private final String label;

    StatusPart(String lable){
        this.label = lable;
    }

    @Override
    public String toString() {
        return label;
    }

}
