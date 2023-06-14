package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.application.repository.sqlite.SqlitePartItemDAO;
import com.example.sgpa.application.repository.sqlite.SqliteReservationDAO;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservationStatus;
import com.example.sgpa.domain.usecases.part.PartItemDAO;

import java.util.List;

public class UpdateExpiredReservationsUseCase {
    ReservationDAO reservationDAO = new SqliteReservationDAO();
    PartItemDAO partItemDAO = new SqlitePartItemDAO();

    public void update(){
        List<Reservation> expiredReservations = reservationDAO.findExpired();
        for (Reservation reservation : expiredReservations) {
            reservation.setStatus(ReservationStatus.EXPIRED);
            reservationDAO.update(reservation);
            reservation.getItems().forEach(partItem -> {
                partItem.setStatus(StatusPart.AVAILABLE);
                partItemDAO.update(partItem);
            });
        }
    }

}
