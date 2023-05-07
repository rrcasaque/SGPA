package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public class InstitutionalId extends StringValueObject{

    public InstitutionalId(String value) {
        super("Prontuário", "XX9999999", value);
    }
    @Override
    protected boolean isValid(String value) {
        return true;
    }
}
