package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.usecases.utils.DAO;

public interface ReservationDAO extends DAO<Reservation, Integer> {
}
