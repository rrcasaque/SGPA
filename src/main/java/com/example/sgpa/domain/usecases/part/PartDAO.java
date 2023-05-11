package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface PartDAO extends DAO<Part, Integer> {
    Integer create(Part obj);
    Optional<Part> findOne(Part obj);
    List<Part> findAll();
    void update(Part obj);
    boolean delete(Part obj);

}
