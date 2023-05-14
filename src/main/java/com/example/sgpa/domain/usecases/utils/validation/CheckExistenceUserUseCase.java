package com.example.sgpa.domain.usecases.utils.validation;

import java.util.Optional;

import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.user.UserDAO;

public class CheckExistenceUserUseCase {
	private UserDAO userDAO;
	
	public CheckExistenceUserUseCase(UserDAO userDAO) {		
		this.userDAO = userDAO;
	}

	public void check(User user, UserType usertype) {
		Optional<User> requester = userDAO.findOneByIdAndType(usertype, user.getInstitutionalId());
		if(requester.isEmpty())
			throw new RuntimeException("user not found!");		
	}
}
