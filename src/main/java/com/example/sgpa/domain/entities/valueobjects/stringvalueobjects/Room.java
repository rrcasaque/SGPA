package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public class Room extends StringValueObject{

    public Room(String value) {
        super("Código de Sala", "X99", value);
    }

    @Override
    protected boolean isValid(String value) {
        return true;
    }
}
