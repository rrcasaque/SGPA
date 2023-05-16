package com.example.sgpa.domain.entities.reservation;

import com.example.sgpa.domain.entities.part.PartItem;

import java.util.Objects;

public class ReservedItem {
    PartItem itemPart;
    Reservation relatedReservation;

    public ReservedItem(PartItem itemPart, Reservation relatedReservation) {
        this.itemPart = itemPart;
        this.relatedReservation = relatedReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedItem that = (ReservedItem) o;
        return Objects.equals(itemPart, that.itemPart) && Objects.equals(relatedReservation, that.relatedReservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemPart, relatedReservation);
    }
}
