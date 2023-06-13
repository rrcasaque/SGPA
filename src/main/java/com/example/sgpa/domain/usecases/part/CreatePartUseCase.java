package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.user.Technician;

import java.util.Optional;

public class CreatePartUseCase {
    private PartDAO partDAO;

    public CreatePartUseCase(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    public Part createPart(String description, int maxDaysCheckedOutForStudent, int maxDaysCheckedOutForProfessor) {
        if (description == null || maxDaysCheckedOutForStudent == 0 || maxDaysCheckedOutForProfessor == 0 )
            throw new IllegalArgumentException("There are missing input fields.");
        if (partDAO.findByDescription(description).isPresent())
            throw new IllegalArgumentException("Informed part type already exists in data base.");
        Part newPart = new Part(description, maxDaysCheckedOutForStudent, maxDaysCheckedOutForProfessor);
        int id = partDAO.create(newPart);
        newPart.setId(id);
        return newPart;
    }
}
