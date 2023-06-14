package com.example.sgpa.domain.usecases.part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckForPartItemAvailabilityUseCase {
    PartItemDAO itemPartDAO;
    public CheckForPartItemAvailabilityUseCase(PartItemDAO itemPartDAO) {
        this.itemPartDAO = itemPartDAO;
    }
    public void checkForAvailabilityOfTheParts(Set<PartItem> partItems) throws Exception {
        if (partItems != null && !partItems.isEmpty()) {
            Set<PartItem> blockedItemParts = partItems.stream().filter(PartItem::isNotAvailable).collect(Collectors.toSet());
            if (!blockedItemParts.isEmpty()) {
                StringBuilder message = new StringBuilder("The following parts are not Available for check out or reservation:\n");
                blockedItemParts.forEach(itemPart -> message
                        .append("PatrimonialId: ")
                        .append(itemPart.getPatrimonialId())
                        .append(". Part description: ")
                        .append(itemPart.getPart().getType())
                        .append(". Status: ")
                        .append(itemPart.getStatus().toString())
                        .append("\n"));
                throw new Exception(message.toString());
            }
        }
    }
}
