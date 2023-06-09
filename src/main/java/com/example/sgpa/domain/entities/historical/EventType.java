package com.example.sgpa.domain.entities.historical;

import java.util.Arrays;

public enum EventType {
    RESERVATION("Reserva"),
    CHECKOUT("Retirada"),
    RETURN("Devolução");
    private String label;
    EventType(String label) {
        this.label = label;
    }
    @Override
    public String toString() {
        return label;
    }
    public static EventType strToEnum(String stringEventType) {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType.toString().equals(stringEventType))
                .findFirst().orElseThrow(()->new RuntimeException("Undefined event type."));
    }
}
