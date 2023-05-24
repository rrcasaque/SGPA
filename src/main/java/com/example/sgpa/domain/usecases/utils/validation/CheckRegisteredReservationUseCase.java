package com.example.sgpa.domain.usecases.utils.validation;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;

public class CheckRegisteredReservationUseCase {
	private ReservationDAO reservationDAO;

	public CheckRegisteredReservationUseCase(ReservationDAO reservationDAO) {		
		this.reservationDAO = reservationDAO;
	}
	
	public void check(LocalDateTime start, LocalDateTime end){
		List<Reservation> reservationList = reservationDAO.getReportByDate(start,end);
		if(reservationList.isEmpty())
			throw new RuntimeException("moves not found!");		
	}
}
