package com.example.sgpa.domain.usecases.report;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.utils.validation.DateType;
import com.example.sgpa.domain.usecases.utils.validation.VerifyDateUseCase;

public class GenerateReportUseCase {
	private EventDAO eventDAO;	

	public GenerateReportUseCase( EventDAO eventDAO) {				
		this.eventDAO = eventDAO;
	}
	
	public List<Event> generate(LocalDateTime start, LocalDateTime end) {
		try {
			VerifyDateUseCase.verify(start, end);
			List<Event> eventList = eventDAO.getReportByDate(start, end);
			
			if(eventList.isEmpty())
				throw new RuntimeException("data not found for the informed parameters");
			return eventList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}		
	}
	
	
}
