package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.part.ItemPart;

import java.util.Set;
import java.util.stream.Collectors;

public class CheckForITemPartAvailabilityUseCase {

    ItemPartDAO itemPartDAO;

    public CheckForITemPartAvailabilityUseCase(ItemPartDAO itemPartDAO) {
        this.itemPartDAO = itemPartDAO;
    }

    public void checkForAvailabilityOfTheParts(Set<ItemPart> itemParts) {
        Set<ItemPart> blockedItemParts = itemParts.stream().filter(itemPart ->
                itemPartDAO.isItemPartAvailable(itemPart.getPatrimonialId())).collect(Collectors.toSet());

        if (!blockedItemParts.isEmpty()){
            StringBuilder message = new StringBuilder("The following parts are not Available for check out:\n");
            blockedItemParts.forEach(itemPart -> message
                    .append("PatrimonialId: ")
                    .append(itemPart.getPatrimonialId())
                    .append(". Part description: ")
                    .append(itemPart.getPart().getDescription())
                    .append(". Status: ")
                    .append(itemPart.getStatus().toString())
                    .append("\n"));
            throw new RuntimeException(message.toString());
        }
    }
}
