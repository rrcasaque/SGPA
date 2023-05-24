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
        Optional<Part> optPart = partDAO.findByDescription(description);
        if (optPart.isEmpty()) {
            //Session.getLoggedTechnician();
            Part newPart = new Part(description, maxDaysCheckedOutForStudent, maxDaysCheckedOutForProfessor);
            int id = partDAO.create(newPart);
            newPart.setId(id);

            return newPart;
        }
        return optPart.get();
    }
}
