package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.usecases.part.PartDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryPartDAO implements PartDAO {
    @Override
    public Optional<Part> findByDescription(String description) {
        return Optional.empty();
    }

    @Override
    public Integer create(Part obj) {
        return null;
    }

    @Override
    public Optional<Part> findOne(Integer type) {
        return Optional.empty();
    }

    @Override
    public List<Part> findAll() {
        return null;
    }

    @Override
    public void update(Part obj) {

    }

    @Override
    public boolean delete(Part obj) {
        return false;
    }
}
