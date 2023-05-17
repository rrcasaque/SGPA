package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.util.Objects;
import java.util.Optional;

public class DeletePartItemUserCase {

    private PartItemDAO partItemDAO;

    public DeletePartItemUserCase(PartItemDAO itemPartDAO) {
        this.partItemDAO = itemPartDAO;
    }

    public boolean deletePartItem(String patrimonialId) {
        Optional<PartItem> optPartItem = partItemDAO.findOne(patrimonialId);
        if (optPartItem.isEmpty())
            throw new EntityNotFoundException("Part Item not found");
        Session.getLoggedTechnician();
        return partItemDAO.delete(optPartItem.get());
    }
}
