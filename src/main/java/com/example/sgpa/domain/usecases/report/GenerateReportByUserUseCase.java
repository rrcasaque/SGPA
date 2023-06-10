package com.example.sgpa.domain.usecases.report;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.validation.CheckExistenceUserUseCase;
import com.example.sgpa.domain.usecases.utils.validation.DateType;
import com.example.sgpa.domain.usecases.utils.validation.VerifyDateUseCase;

public class GenerateReportByUserUseCase {
	private final EventDAO eventDAO;
	private final UserDAO userDAO;
	public GenerateReportByUserUseCase(EventDAO eventDAO, UserDAO userDAO) {
		this.userDAO = userDAO;
		this.eventDAO = eventDAO;
	}
	public List<Event> generate(int userId, LocalDateTime start, LocalDateTime end) {
		try {						
			CheckExistenceUserUseCase checkExistenceUserUseCase = new CheckExistenceUserUseCase(this.userDAO);
			checkExistenceUserUseCase.check(userId);
			VerifyDateUseCase.verify(start,end);
			
			List<Event> eventList = this.eventDAO.getReportByUser(userId, start, end);
						 
			if(eventList.isEmpty())
				throw new RuntimeException("data not found for the informed parameters");																		
			return eventList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}			
	}
}
