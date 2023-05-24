package com.example.sgpa.domain.usecases.report;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.validation.CheckExistenceUserUseCase;
import com.example.sgpa.domain.usecases.utils.validation.DateType;
import com.example.sgpa.domain.usecases.utils.validation.VerifyDateUseCase;

public class GenerateReportByUserUseCase {
		
	private EventDAO eventDAO;
	private UserDAO userDAO;
	
	public GenerateReportByUserUseCase(EventDAO eventDAO, UserDAO userDAO) {				
		this.userDAO = userDAO;
		this.eventDAO = eventDAO;
	}

	public List<Event> generate(User user, UserType userType, LocalDateTime start, LocalDateTime end) {			
		try {						
			CheckExistenceUserUseCase checkExistenceUserUseCase = new CheckExistenceUserUseCase(this.userDAO);
			checkExistenceUserUseCase.check(user, userType);
			VerifyDateUseCase.verify(DateType.START, start);
			VerifyDateUseCase.verify(DateType.END, end);
			
			List<Event> eventList = this.eventDAO.getReportByUser(user, start, end);
						 
			if(eventList.isEmpty())
				throw new RuntimeException("data not found for the informed parameters");																		
			return eventList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}			
	}
}
