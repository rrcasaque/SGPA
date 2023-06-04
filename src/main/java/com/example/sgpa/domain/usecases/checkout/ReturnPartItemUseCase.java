package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.entities.historical.EventType;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReturnPartItemUseCase {
    CheckedOutItemDAO checkedOutItemDAO;
    PartItemDAO itemPartDAO;
    EventDAO eventDAO;
    UserDAO userDAO;
    public ReturnPartItemUseCase(CheckedOutItemDAO checkedOutItemDAO,
                                 PartItemDAO itemPartDAO,
                                 EventDAO eventDAO,
                                 UserDAO userDAO) {
        this.checkedOutItemDAO = checkedOutItemDAO;
        this.itemPartDAO = itemPartDAO;
        this.eventDAO = eventDAO;
        this.userDAO = userDAO;
    }
    public void returnPartItem(int patrimonialId){
        CheckedOutItem checkedOutItem = checkedOutItemDAO.findOpenByPartItemId(patrimonialId)
                .orElseThrow(()->new EntityNotFoundException("There is no checked out item with the informed patrimonial identification"));
        checkedOutItem.setReturnDate(LocalDateTime.now());
        checkedOutItem.getPartItem().setStatus(StatusPart.AVAILABLE);
        checkedOutItemDAO.update(checkedOutItem);
        itemPartDAO.update(checkedOutItem.getPartItem());
        eventDAO.create(new Event(EventType.RETURN, checkedOutItem.getRelatedCheckout().getUser(),
                Session.getLoggedTechnician(), checkedOutItem.getPartItem()));
    }
}
