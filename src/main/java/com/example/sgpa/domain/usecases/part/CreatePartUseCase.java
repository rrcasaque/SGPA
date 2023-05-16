package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.util.Optional;

public class CreatePartUseCase {
    private PartItemDAO partItemDAO;
    private PartDAO partDAO;

    public CreatePartUseCase(PartItemDAO itemPartDAO, PartDAO partDAO) {
        this.partItemDAO = itemPartDAO;
        this.partDAO = partDAO;
    }

    public Part createPart(String description, int maxDaysCheckedOutForStudent, int maxDaysCheckedOutForProfessor) {
        Optional<Part> optPart = partDAO.findByDescription(description);
        if (optPart.isEmpty()) {
            Part newPart = new Part(description, maxDaysCheckedOutForStudent, maxDaysCheckedOutForProfessor);
            int id = partDAO.create(newPart);
            newPart.setId(id);

            return newPart;
        }
        return optPart.get();
    }

    public PartItem createPartItem(String patrimonialId, Part part) {
        Optional<Part> optPart = partDAO.findOne(part.getId());
        if (optPart.isEmpty()) {
            throw new EntityNotFoundException("Part not found");
        }

        Optional<PartItem> optPartItem = partItemDAO.findOne(patrimonialId);
        if (optPartItem.isEmpty()) {
            PartItem newPartItem = new PartItem(patrimonialId, StatusPart.AVAILABLE, "nova", part);
            partItemDAO.create(newPartItem);
            return newPartItem;
        }

        return optPartItem.get();
    }
}
