package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.util.Objects;
import java.util.Optional;

public class UpdatePartItemUseCase {
    private final PartItemDAO partItemDAO;

    public UpdatePartItemUseCase(PartItemDAO itemPartDAO) {
        this.partItemDAO = itemPartDAO;
    }
    public PartItem updatePartItem(PartItem partItem) {
        if (partItem == null)
            throw new IllegalArgumentException("Part item mus be not null.");
        PartItem optPartItem = partItemDAO.findOne(partItem.getPatrimonialId()).orElseThrow(()->new RuntimeException("Part Item not found"));
        partItemDAO.update(partItem);
        return partItem;
    }

}
