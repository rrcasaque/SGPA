package com.example.sgpa.domain.entities.valueobjects.stringvalueobjects;

public enum DDD {
    DDD11(11),
    DDD12(12),
    DDD13(13),
    DDD14(14),
    DDD15(15),
    DDD16(16),
    DDD17(17),
    DDD18(18),
    DDD19(19);
    private int ddd;
    private DDD(int ddd){
        this.ddd= ddd;
    }

    public int getValue() {
        return ddd;
    }

    @Override
    public String toString() {
        return String.valueOf(ddd);
    }
}
