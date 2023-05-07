package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public class TechnicianPassword extends StringValueObject{
    public TechnicianPassword(String value) {
        super("Password", "Must have Exactly 6 characters", value);
    }
    @Override
    protected boolean isValid(String value) {
        return true;
    }
}
