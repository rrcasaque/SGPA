package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public class Fone extends StringValueObject{

    public Fone(String value) {
        super("Telefone", "(99)99999-9999", value);
    }

    @Override
    protected boolean isValid(String value) {
        return true;
    }
}
