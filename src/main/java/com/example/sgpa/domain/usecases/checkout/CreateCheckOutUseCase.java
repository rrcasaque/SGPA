package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.historical.EventDAO;
import com.example.sgpa.domain.entities.historical.EventType;
import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.CheckForITemPartAvailabilityUseCase;
import com.example.sgpa.domain.usecases.part.ItemPartDAO;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingsIssuesUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.util.Optional;
import java.util.Set;

public class CreateCheckOutUseCase {
    private final ItemPartDAO itemPartDAO;
    private final UserDAO userDAO;
    private final CheckOutDAO checkOutDAO;
    private final CheckedOutItemDAO checkedOutItemDAO;
    private final EventDAO eventDAO;
    private final CheckForUserPendingsIssuesUseCase checkForUserPendingsIssuesUseCase;
    private final CheckForITemPartAvailabilityUseCase checkForITemPartAvailabilityUseCase;
    CreateCheckOutUseCase(UserDAO userDAO,
                          ItemPartDAO itemPartDAO,
                          CheckOutDAO checkOutDAO,
                          CheckedOutItemDAO checkedOutItemDAO,
                          EventDAO eventDAO,
                          CheckForUserPendingsIssuesUseCase checkForUserPendingsIssuesUseCase,
                          CheckForITemPartAvailabilityUseCase checkForITemPartAvailabilityUseCase){
        this.userDAO = userDAO;
        this.itemPartDAO =itemPartDAO;
        this.checkOutDAO = checkOutDAO;
        this.checkedOutItemDAO = checkedOutItemDAO;
        this.eventDAO = eventDAO;
        this.checkForUserPendingsIssuesUseCase = checkForUserPendingsIssuesUseCase;
        this.checkForITemPartAvailabilityUseCase = checkForITemPartAvailabilityUseCase;
    }
    Checkout createCheckout(String userId, Set<ItemPart> itemParts){
        Optional<User> user = userDAO.findOne(userId);
        if (user.isEmpty())
            throw new EntityNotFoundException("User not found");
        checkForUserPendingsIssuesUseCase.checkForUserPendingIssues(userId);
        checkForITemPartAvailabilityUseCase.checkForAvailabilityOfTheParts(itemParts);
        Technician loggedTechnician = Session.getLoggedTechnician();
        Checkout checkout = new Checkout(itemParts, user.get(), loggedTechnician);
        int id = checkOutDAO.create(checkout);
        checkout.setCheckOutId(id);
        checkout.getCheckedOutItems().forEach(checkedOutItemDAO::create);
        itemParts.forEach(itemPart -> itemPart.setStatus(StatusPart.CHECKED_OUT));
        itemParts.forEach(itemPartDAO::update);
        eventDAO.create(new Event(EventType.CHECKOUT, user.get(), loggedTechnician));
        return checkout;
    }

}
