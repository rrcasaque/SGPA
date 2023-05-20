package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.user.UserDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryUserDAO implements UserDAO {
    @Override
    public Optional<User> findOneByIdAndType(UserType type, String institutionalId) {
        return Optional.empty();
    }

    @Override
    public String create(User obj) {
        return null;
    }

    @Override
    public Optional<User> findOne(String type) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public boolean delete(User obj) {
        return false;
    }
}
