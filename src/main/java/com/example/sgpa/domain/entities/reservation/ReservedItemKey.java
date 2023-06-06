package com.example.sgpa.domain.entities.reservation;

import java.util.Objects;

public class ReservedItemKey {
    private Integer reservationId;
    private Integer patrimonialId;

    public ReservedItemKey(Integer reservationId, Integer patrimonialId) {
        this.reservationId = reservationId;
        this.patrimonialId = patrimonialId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public Integer getPatrimonialId() {
        return patrimonialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedItemKey that = (ReservedItemKey) o;
        return reservationId.equals(that.reservationId) && patrimonialId.equals(that.patrimonialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, patrimonialId);
    }
}
