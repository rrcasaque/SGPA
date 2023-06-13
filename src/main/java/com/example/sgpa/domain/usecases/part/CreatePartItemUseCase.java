package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.util.Optional;

public class CreatePartItemUseCase {
    private PartItemDAO partItemDAO;
    private PartDAO partDAO;

    public CreatePartItemUseCase(PartItemDAO itemPartDAO, PartDAO partDAO) {
        this.partItemDAO = itemPartDAO;
        this.partDAO = partDAO;
    }


    public PartItem createPartItem(Part part, String observation) throws Exception {
        if (part == null) throw new IllegalArgumentException("Inform the part to be save.");
        Part foundPart = partDAO.findOne(part.getId()).orElseThrow(()-> new EntityNotFoundException("Part not found"));
        PartItem newPartItem = new PartItem(StatusPart.AVAILABLE, observation, part);
        int patrimonialId = partItemDAO.create(newPartItem);
        newPartItem.setPatrimonialId(patrimonialId);
        return newPartItem;
    }
}
