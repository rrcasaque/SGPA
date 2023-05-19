package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends DAO<Reservation, Integer> {
    void createReservedItem(ReservedItem reservedItem);
    List<Reservation> getReportByDate(Date start, Date end);
    List<Reservation> getReportByUser(User user, Date start, Date end);
    List<Reservation> getReportByPart(Part part, Date start, Date end);
}
