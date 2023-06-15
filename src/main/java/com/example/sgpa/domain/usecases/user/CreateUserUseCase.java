package com.example.sgpa.domain.usecases.user;

import java.util.Optional;

import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.Professor;
import com.example.sgpa.domain.entities.user.Student;
import com.example.sgpa.domain.entities.user.Technician;

import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.reservation.CreateReservationUseCase;

public class CreateUserUseCase {
	private UserDAO userDAO;
	public Exception duplicatedID = new IllegalArgumentException("This institutional ID already exists.");

	public CreateUserUseCase(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	public void createStudent(int institutionalId, String name, String email, String phone) throws Exception {
		Optional<User> optUser = userDAO.findOne(institutionalId);

		if (!optUser.isEmpty())
			throw duplicatedID;
		if (institutionalId == 0 || name.isEmpty())
			throw new IllegalArgumentException("Institutional ID and name must be informed.");

		Student newStudent = new Student(institutionalId, name, email, phone);
		userDAO.create(newStudent);

	}

	public void createProfessor(int institutionalId, String name, String email, String phone, int room) throws Exception {
		Optional<User> optUser = userDAO.findOne(institutionalId);

		if (!optUser.isEmpty())
			throw duplicatedID;
		if (institutionalId == 0 || name.isEmpty() || room == 0)
			throw new IllegalArgumentException("Institutional ID, name and room must be informed.");

		Professor newProfessor = new Professor(institutionalId, name, email, phone, room);
		System.out.print(newProfessor.getRoom());
		userDAO.create(newProfessor);
	}

	public void createTechnician (int institutionalId, String name, String email, String phone, String login, String password) throws Exception {
		Optional<User> optUser = userDAO.findOne(institutionalId);;

		if (!optUser.isEmpty())
			throw duplicatedID;
		if (institutionalId == 0 || name.isEmpty() || login.isEmpty() || password.isEmpty())
			throw new IllegalArgumentException("Institutional ID, name, login and password must be informed.");

		Technician newTechnician = new Technician(institutionalId, name, email, phone, login, password);
		userDAO.create(newTechnician);
	}
}
