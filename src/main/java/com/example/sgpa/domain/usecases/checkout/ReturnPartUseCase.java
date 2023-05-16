package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.historical.EventDAO;
import com.example.sgpa.domain.entities.historical.EventType;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

public class ReturnPartUseCase {
    CheckedOutItemDAO checkedOutItemDAO;
    PartItemDAO itemPartDAO;
    EventDAO eventDAO;
    UserDAO userDAO;

    public ReturnPartUseCase(CheckedOutItemDAO checkedOutItemDAO,
                             PartItemDAO itemPartDAO,
                             EventDAO eventDAO,
                             UserDAO userDAO) {
        this.checkedOutItemDAO = checkedOutItemDAO;
        this.itemPartDAO = itemPartDAO;
        this.eventDAO = eventDAO;
        this.userDAO = userDAO;
    }

    public void returnItemPart(String patrimonialId, String institutionalId){
        Optional<CheckedOutItem> CheckedOutItemOptional = checkedOutItemDAO.findNotReturned(patrimonialId);
        if(CheckedOutItemOptional.isEmpty())
            throw new EntityNotFoundException("There is no checked out item with the informed patrimonial identification");
        Optional<User> user = userDAO.findOne(institutionalId);
        if(user.isEmpty())
            throw new EntityNotFoundException("There is no checked out item with the informed patrimonial identification");
        CheckedOutItem checkedOutItem = CheckedOutItemOptional.get();
        checkedOutItem.setReturnDate(LocalDateTime.now());
        PartItem itemPart = checkedOutItem.getItemPart();
        itemPart.setStatus(StatusPart.AVAILABLE);
        checkedOutItemDAO.update(checkedOutItem);
        itemPartDAO.update(itemPart);
        Technician loggedTechnician = Session.getLoggedTechnician();
        eventDAO.create(new Event(EventType.RETURN, user.get(), loggedTechnician, itemPart));
    }
}
