package com.example.sgpa.domain.usecases.utils;

public class FixLengthStringBuilder {
    public String format(String input, Integer finalLength){
        if(input.length()>finalLength) return input.substring(0,finalLength);
        if(input.length()<finalLength) return addBlankSpaces(input, finalLength - input.length());
        else return input;
    }
    private String addBlankSpaces(String input, Integer numberOfSpaces){
        StringBuilder newStr = new StringBuilder(input);
        for (int i = 0; i < numberOfSpaces; i++) {
            newStr.append(" ");
        }
        return newStr.toString();
    }
}
