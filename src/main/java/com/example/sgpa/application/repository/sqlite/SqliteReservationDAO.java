package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;

import java.util.List;
import java.util.Optional;

public class SqliteReservationDAO implements ReservationDAO {
    @Override
    public void createReservedItem(ReservedItem reservedItem) {

    }

    @Override
    public Integer create(Reservation obj) {
        return null;
    }

    @Override
    public Optional<Reservation> findOne(Integer type) {
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public void update(Reservation obj) {

    }

    @Override
    public boolean delete(Reservation obj) {
        return false;
    }
}
