package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public abstract class StringValueObject {
    protected String name;
    protected String value;
    protected String mask; //ver como fazer com regex
    public StringValueObject(String name, String mask, String value){
        this.name = name;
        this.mask = mask;
        setValue(value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (!isValid(value))
            throw new InvalidValueException("Invalid value input for object " + name +
                    ". It must be like: " + mask);
        this.value = value;
    }

    protected abstract boolean isValid(String value);

}
