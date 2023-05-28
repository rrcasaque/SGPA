package com.example.sgpa.domain.usecases.auth;

import java.util.Optional;
import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.user.UserDAO;

public class Auth {
	private final UserDAO userDAO;

	public Auth(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public boolean authenticate(String login, String password) throws Exception {
		Optional<User> user = userDAO.findOneByLoginAndPassword(login, password);
		if (user.isEmpty())
			throw new Exception("Login and/or password incorrect.");
		Session.makeInstance(user.get());
		System.out.println(Session.getLoggedTechnician().getName()+"has logged right now!");
		return true;		
	}
}
