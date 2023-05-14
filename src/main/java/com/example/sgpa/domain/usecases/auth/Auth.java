package com.example.sgpa.domain.usecases.auth;

import java.util.Optional;

import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.technician.TechnicianDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;

public class Auth {
	private UserDAO userDAO;

	public Auth(UserDAO userDAO) {		
		this.userDAO = userDAO;
	}
	
	public boolean authenticate(String institutionalId, String password){
		Optional<User> user = userDAO.findOneByIdAndType(UserType.TECHNICIAN,institutionalId);
		Technician technician = (Technician) user.get(); 
		if(technician == null)
			throw new RuntimeException("Technician user not found");		
		if(!technician.getPassword().equals(password))
			throw new RuntimeException("Incorrect password");	
		return true;		
	}
	
}
