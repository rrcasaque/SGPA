package com.example.sgpa.domain.usecases.auth;

import java.util.Optional;

import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.usecases.technician.TechnicianDAO;

public class Auth {
	private TechnicianDAO technicianDAO;

	public Auth(TechnicianDAO technicianDAO) {		
		this.technicianDAO = technicianDAO;
	}
	
	public boolean authenticate(String institutionalId, String password){
		Optional<Technician> technician = technicianDAO.findOne(institutionalId);		
		if(technician.isEmpty())
			throw new RuntimeException("Technician user not found");	
		if(!technician.get().getPassword().equals(password))
			throw new RuntimeException("Incorrect password");	
		return true;		
	}
	
}
