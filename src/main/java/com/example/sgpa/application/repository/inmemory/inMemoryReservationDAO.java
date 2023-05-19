package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;

import java.util.*;

public class inMemoryReservationDAO implements ReservationDAO {
    private static final Map<Integer, Reservation> reservationDB = new HashMap<>();
    private static int nbr_reservations;
    private static final Set<ReservedItem> reservedItemDB = new LinkedHashSet<>();

    @Override
    public Integer create(Reservation reservation) {
        nbr_reservations++;
        reservationDB.put(nbr_reservations, reservation);
        return nbr_reservations;
    }

    @Override
    public Optional<Reservation> findOne(Integer key) {
        return Optional.ofNullable(reservationDB.get(key));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationDB.values().stream().toList();
    }

    @Override
    public void update(Reservation reservation) {
        int key = reservation.getReservationId();
        if (!reservationDB.containsKey(key))
            throw new RuntimeException("Reservation do not exist in data base.");
        reservationDB.put(key, reservation);
    }

    @Override
    public boolean delete(Reservation reservation) {
        return reservationDB.remove(reservation.getReservationId(), reservation);
    }

    @Override
    public void createReservedItem(ReservedItem reservedItem) {

    }

    @Override
    public List<Reservation> getReportByDate(Date start, Date end) {
        return null;
    }

    @Override
    public List<Reservation> getReportByUser(User user, Date start, Date end) {
        return null;
    }

    @Override
    public List<Reservation> getReportByPart(Part part, Date start, Date end) {
        return null;
    }
}
