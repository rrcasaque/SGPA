package com.example.sgpa.domain.usecases.report;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.part.PartDAO;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;
import com.example.sgpa.domain.usecases.utils.validation.CheckExistencePartUseCase;
import com.example.sgpa.domain.usecases.utils.validation.CheckExistenceUserUseCase;
import com.example.sgpa.domain.usecases.utils.validation.DateType;
import com.example.sgpa.domain.usecases.utils.validation.VerifyDateUseCase;

public class GenerateReportByPartUseCase {
	private EventDAO eventDAO;
	private PartDAO partDAO;

	public GenerateReportByPartUseCase(EventDAO eventDAO, PartDAO partDAO) {		
		this.eventDAO = eventDAO;
		this.partDAO = partDAO;
	}
	
	public List<Event> generate(Part part, LocalDateTime start, LocalDateTime end) {
		try {
			CheckExistencePartUseCase checkExistenceUserUseCase = new CheckExistencePartUseCase(this.partDAO);
			checkExistenceUserUseCase.check(part);
			VerifyDateUseCase.verify(DateType.START, start);
			VerifyDateUseCase.verify(DateType.END, end);
			
			List<Event> eventList = eventDAO.getReportByPart(part, start, end); 
			
			if(eventList.isEmpty())
				throw new RuntimeException("data not found for the informed parameters");
			return eventList;
		} catch (Exception e) {
			throw new RuntimeException("data not found for the informed parameters");
		}		
	}
}
