package com.example.sgpa.domain.usecases.utils.validation;

import java.util.List;
import java.util.Optional;

import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;

public class CheckRegisteredMovementsByUserUseCase {
	private ReservationDAO reservationDAO;

	public CheckRegisteredMovementsByUserUseCase(ReservationDAO reservationDAO) {		
		this.reservationDAO = reservationDAO;
	}
	
	public void check(User user){		
		List<Reservation> reservationList = reservationDAO.findAll();
		boolean state = false;
		for(Reservation reservation : reservationList){
			if(reservation.getRequester().equals(user))
				state = true;
		}
		if(!state)
			throw new RuntimeException("moves not found!");
	}
}
