package com.example.sgpa.domain.usecases.user;

import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.Optional;

public interface UserDAO extends DAO<User, String>{
    Optional<User> findOneByIdAndType(UserType type, int institutionalId);
}
