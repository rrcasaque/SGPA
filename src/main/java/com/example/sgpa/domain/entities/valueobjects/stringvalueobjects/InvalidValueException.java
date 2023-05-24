package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public class InvalidValueException extends RuntimeException{
    public InvalidValueException(String message) {
        super(message);
    }
}
