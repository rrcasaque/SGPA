package com.example.sgpa.domain.usecases.report;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;

public class GenerateReportByPartUseCase {
	private ReservationDAO reservationDAO;

	public GenerateReportByPartUseCase(ReservationDAO reservationDAO) {		
		this.reservationDAO = reservationDAO;
	}
	
	public List<Reservation> generate(Part part, LocalDateTime start, LocalDateTime end) {		
		List<Reservation> reservationList = reservationDAO.getReportByPart(part, start, end); 
		if(reservationList.isEmpty())
			throw new RuntimeException("data not found for the informed parameters");
		return reservationList;
	}
}
