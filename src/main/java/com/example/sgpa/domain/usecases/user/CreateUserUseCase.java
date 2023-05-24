package com.example.sgpa.domain.usecases.user;

import java.util.Optional;

import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.Professor;
import com.example.sgpa.domain.entities.user.Student;
import com.example.sgpa.domain.entities.user.Technician;

import com.example.sgpa.domain.entities.user.UserType;

public class CreateUserUseCase {
	private UserDAO userDAO;
	
	public User createUser(String institutionalId, String name, String email, String phone, UserType userType) {
		Optional<User> optUser = userDAO.findOneByIdAndType(userType, institutionalId);
		if (optUser.isEmpty()) {
			Student newStudent = new Student(institutionalId, name, email, phone);
			return newStudent;
		}
		
		return optUser.get();
	}
	
	public User createUser(String institutionalId, String name, String email, String phone, UserType userType, String room) {
		Optional<User> optUser = userDAO.findOneByIdAndType(userType, institutionalId);
		if (optUser.isEmpty()) {
			Professor newProfessor = new Professor(institutionalId, name, email, phone, room);
			return newProfessor;
		}
		
		return optUser.get();
	}
	
	public User createUser(String institutionalId, String name, String email, String phone, UserType userType, String login, String password) {
		Optional<User> optUser = userDAO.findOneByIdAndType(userType, institutionalId);
		if (optUser.isEmpty()) {
			Technician newTechnician = new Technician(institutionalId, name, email, phone, login, password);
			return newTechnician;
		}
		
		return optUser.get();
	}
}
