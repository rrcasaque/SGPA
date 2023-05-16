package com.example.sgpa.domain.usecases.utils.validation;

import java.util.List;
import java.util.Set;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;

public class CheckRegisteredMovementsByPartUseCase {
	private ReservationDAO reservationDAO;

	public CheckRegisteredMovementsByPartUseCase(ReservationDAO reservationDAO) {		
		this.reservationDAO = reservationDAO;
	}
	
	public void check(Part part) {
		List<Reservation> reservationList = reservationDAO.findAll();
		boolean state = false;
		for(Reservation reservation : reservationList){
			Set<PartItem> itemPartSet = reservation.getItems();
			for(PartItem itemPart : itemPartSet)
				if(itemPart.getPart().getId() == part.getId())
					state = true;
		}
		if(!state)
			throw new RuntimeException("moves not found!");
	}
}

