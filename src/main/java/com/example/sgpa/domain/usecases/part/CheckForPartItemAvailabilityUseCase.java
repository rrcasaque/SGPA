package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.part.PartItem;

import java.util.Set;
import java.util.stream.Collectors;

public class CheckForPartItemAvailabilityUseCase {

    PartItemDAO itemPartDAO;

    public CheckForPartItemAvailabilityUseCase(PartItemDAO itemPartDAO) {
        this.itemPartDAO = itemPartDAO;
    }

    public void checkForAvailabilityOfTheParts(Set<PartItem> itemParts) {
        Set<PartItem> blockedItemParts = itemParts.stream().filter(itemPart ->
                itemPartDAO.isPartItemAvailable(itemPart.getPatrimonialId())).collect(Collectors.toSet());

        if (!blockedItemParts.isEmpty()){
            StringBuilder message = new StringBuilder("The following parts are not Available for check out:\n");
            blockedItemParts.forEach(itemPart -> message
                    .append("PatrimonialId: ")
                    .append(itemPart.getPatrimonialId())
                    .append(". Part description: ")
                    .append(itemPart.getPart().getType())
                    .append(". Status: ")
                    .append(itemPart.getStatus().toString())
                    .append("\n"));
            throw new RuntimeException(message.toString());
        }
    }
}
