package com.example.sgpa.domain.usecases.utils.validation;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.usecases.historical.EventDAO;

public class CheckRegisteredReservationUseCase {
	private EventDAO eventDAO;

	public CheckRegisteredReservationUseCase(EventDAO eventDAO) {		
		this.eventDAO = eventDAO;
	}
	
	public void check(LocalDateTime start, LocalDateTime end){
		List<Event> eventList = eventDAO.getReportByDate(start,end);
		if(eventList.isEmpty())
			throw new RuntimeException("moves not found!");		
	}
}
