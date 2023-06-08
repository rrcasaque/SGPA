package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.reservation.ReservationStatus;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.entities.historical.EventType;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.CheckForPartItemAvailabilityUseCase;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingIssuesUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Set;

public class CreateReservationUseCase {
    private final PartItemDAO itemPartDAO;
    private final UserDAO userDAO;
    private final ReservationDAO reservationDAO;
    private final ReservedItemDAO reservedItemDAO;
    private final EventDAO eventDAO;

    private final CheckForUserPendingIssuesUseCase checkForUserPendingIssuesUseCase;
    private final CheckForPartItemAvailabilityUseCase checkForPartItemAvailabilityUseCase;
    public CreateReservationUseCase(UserDAO userDAO,
                          PartItemDAO itemPartDAO,
                          ReservationDAO reservationDAO,
                          EventDAO eventDAO,
                          CheckForUserPendingIssuesUseCase checkForUserPendingIssuesUseCase,
                          CheckForPartItemAvailabilityUseCase checkForPartItemAvailabilityUseCase,
                                    ReservedItemDAO reservedItemDAO){
        this.userDAO = userDAO;
        this.itemPartDAO =itemPartDAO;
        this.reservationDAO = reservationDAO;
        this.eventDAO = eventDAO;
        this.checkForUserPendingIssuesUseCase = checkForUserPendingIssuesUseCase;
        this.checkForPartItemAvailabilityUseCase = checkForPartItemAvailabilityUseCase;
        this.reservedItemDAO = reservedItemDAO;
    }
    public void createReservation(int userId, Set<PartItem> partItems, LocalDate dateScheduledForCheckout) throws Exception {
        if (userId == 0 || partItems.isEmpty() || dateScheduledForCheckout == null)
            throw new IllegalArgumentException("User, parts and check out date must be informed.");
        if (dateScheduledForCheckout.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Invalid check out date.");
        User user = userDAO.findOne(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));
        checkForUserPendingIssuesUseCase.checkForUserPendingIssues(userId);
        checkForPartItemAvailabilityUseCase.checkForAvailabilityOfTheParts(partItems);
        User loggedTechnician = Session.getLoggedTechnician();
        Reservation reservation = new Reservation(dateScheduledForCheckout, user, loggedTechnician, ReservationStatus.WAITING_CHECKOUT);
        int id = reservationDAO.create(reservation);
        reservation.setReservationId(id);
        partItems.forEach(partItem -> partItem.setStatus(StatusPart.RESERVED));
        partItems.forEach(itemPartDAO::update);
        partItems.forEach(partItem -> reservedItemDAO.create(new ReservedItem(partItem, reservation)));
        partItems.forEach(partItem -> eventDAO.create(new Event(EventType.RESERVATION, user, loggedTechnician, partItem)));
    }
}
