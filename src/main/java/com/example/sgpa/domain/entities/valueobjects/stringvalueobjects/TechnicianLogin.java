package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public class TechnicianLogin extends StringValueObject {
    public TechnicianLogin(String value) {
        super("Login", "Only letters and numbers. Maximum 8 characters", value);
    }
    @Override
    protected boolean isValid(String value) {
        return true;
    }
}
