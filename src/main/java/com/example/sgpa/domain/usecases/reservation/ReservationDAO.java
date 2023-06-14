package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.List;

public interface ReservationDAO extends DAO<Reservation, Integer> {
    public List<Reservation> findExpired();
}
