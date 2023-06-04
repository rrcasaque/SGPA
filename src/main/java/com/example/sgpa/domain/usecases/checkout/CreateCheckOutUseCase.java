package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.entities.historical.EventType;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.CheckForPartItemAvailabilityUseCase;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingIssuesUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.util.Optional;
import java.util.Set;

public class CreateCheckOutUseCase {
    private final PartItemDAO itemPartDAO;
    private final UserDAO userDAO;
    private final CheckOutDAO checkOutDAO;
    private final CheckedOutItemDAO checkedOutItemDAO;
    private final EventDAO eventDAO;
    private final CheckForUserPendingIssuesUseCase checkForUserPendingIssuesUseCase;
    private final CheckForPartItemAvailabilityUseCase checkForPartItemAvailabilityUseCase;
    public CreateCheckOutUseCase(UserDAO userDAO,
                          PartItemDAO itemPartDAO,
                          CheckOutDAO checkOutDAO,
                          CheckedOutItemDAO checkedOutItemDAO,
                          EventDAO eventDAO,
                          CheckForUserPendingIssuesUseCase checkForUserPendingIssuesUseCase,
                          CheckForPartItemAvailabilityUseCase checkForPartItemAvailabilityUseCase){
        this.userDAO = userDAO;
        this.itemPartDAO =itemPartDAO;
        this.checkOutDAO = checkOutDAO;
        this.checkedOutItemDAO = checkedOutItemDAO;
        this.eventDAO = eventDAO;
        this.checkForUserPendingIssuesUseCase = checkForUserPendingIssuesUseCase;
        this.checkForPartItemAvailabilityUseCase = checkForPartItemAvailabilityUseCase;
    }
    public Checkout createCheckout(int userId, Set<PartItem> partItems){
        if (userId == 0 || partItems.isEmpty())
            throw new IllegalArgumentException("User and check out items must be not null.");
        User user = userDAO.findOne(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));
        checkForUserPendingIssuesUseCase.checkForUserPendingIssues(userId);
        checkForPartItemAvailabilityUseCase.checkForAvailabilityOfTheParts(partItems);
        User loggedTechnician = Session.getLoggedTechnician();
        Checkout checkout = new Checkout(partItems, user, loggedTechnician);
        int id = checkOutDAO.create(checkout);
        checkout.setCheckOutId(id);
        checkout.getCheckedOutItems().forEach(checkedOutItemDAO::create);
        partItems.forEach(item -> item.setStatus(StatusPart.CHECKED_OUT));
        partItems.forEach(itemPartDAO::update);
        partItems.forEach(item -> eventDAO.create(new Event(EventType.CHECKOUT, user, loggedTechnician, item)));
        return checkout;
    }
}
