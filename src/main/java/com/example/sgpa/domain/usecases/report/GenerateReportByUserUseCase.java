package com.example.sgpa.domain.usecases.report;

import java.util.Date;
import java.util.List;

import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.User;

public class GenerateReportByUserUseCase {
	
	private ReservationDAO reservationDAO;
	
	public GenerateReportByUserUseCase(ReservationDAO reservationDAO) {		
		this.reservationDAO = reservationDAO;
	}

	public Reservation generate(User user, Date start, Date end) {		
		List<Reservation> reservationList = reservationDAO.getReport(user, start, end); 
		if(reservationList.isEmpty())
			throw new RuntimeException("data not found for the informed parameters");
	}
}
