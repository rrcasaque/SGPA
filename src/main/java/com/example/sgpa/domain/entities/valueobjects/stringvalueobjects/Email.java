package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public class Email extends StringValueObject{

    public Email(String value) {
        super("E-mail", "example@mailserver.com", value);
    }
    @Override
    protected boolean isValid(String value) {
        //ver como validar com regex
        return true;
    }


}
