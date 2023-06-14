package com.example.sgpa.domain.entities.reservation;

import java.util.Arrays;

public enum ReservationStatus {
    WAITING_CHECKOUT("Aguardando retirada"),
    CANCELED("Cancelada"),
    EXPIRED("Expirada"),
    FINISHED("Empréstimo já realizado");

    private final String label;
    ReservationStatus(String label){
        this.label = label;
    }
    @Override
    public String toString() {
        return label;
    }
    public static ReservationStatus strToEnum(String statusString){
        return Arrays.stream(ReservationStatus.values())
                .filter(reservationStatus -> reservationStatus.toString().equals(statusString))
                .findFirst().orElseThrow(()->new RuntimeException("Undefined reservation status."));
    }


}
