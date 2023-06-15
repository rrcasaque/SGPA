package com.example.sgpa.domain.usecases.user;

import java.util.Optional;

import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.Professor;
import com.example.sgpa.domain.entities.user.Student;
import com.example.sgpa.domain.entities.user.Technician;

public class UpdateUserUseCase {
    private UserDAO userDAO;
    public Exception invalidID = new IllegalArgumentException("This institutional ID not exists.");

    public UpdateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void updateStudent(int institutionalId, String name, String email, String phone) throws Exception {
        Optional<User> optUser = userDAO.findOne(institutionalId);

        if (optUser.isEmpty())
            throw invalidID;
        if (institutionalId == 0 || name.isEmpty())
            throw new IllegalArgumentException("Institutional ID and name must be informed.");

        Student newStudent = new Student(institutionalId, name, email, phone);
        userDAO.update(newStudent);

    }

    public void updateProfessor(int institutionalId, String name, String email, String phone, int room) throws Exception {
        Optional<User> optUser = userDAO.findOne(institutionalId);

        if (optUser.isEmpty())
            throw invalidID;
        if (institutionalId == 0 || name.isEmpty() || room == 0)
            throw new IllegalArgumentException("Institutional ID, name and room must be informed.");

        Professor newProfessor = new Professor(institutionalId, name, email, phone, room);
        userDAO.update(newProfessor);
    }

    public void updateTechnician (int institutionalId, String name, String email, String phone, String login, String password) throws Exception {
        Optional<User> optUser = userDAO.findOne(institutionalId);;

        if (optUser.isEmpty())
            throw invalidID;
        if (institutionalId == 0 || name.isEmpty() || login.isEmpty() || password.isEmpty())
            throw new IllegalArgumentException("Institutional ID, name, login and password must be informed.");

        Technician newTechnician = new Technician(institutionalId, name, email, phone, login, password);
        userDAO.update(newTechnician);
    }


}
