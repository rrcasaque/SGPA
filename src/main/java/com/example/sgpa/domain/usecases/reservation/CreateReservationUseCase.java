package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.historical.EventDAO;
import com.example.sgpa.domain.entities.historical.EventType;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.CheckForITemPartAvailabilityUseCase;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingsIssuesUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class CreateReservationUseCase {

    private final PartItemDAO itemPartDAO;
    private final UserDAO userDAO;
    private final ReservationDAO reservationDAO;
    private final EventDAO eventDAO;
    private final CheckForUserPendingsIssuesUseCase checkForUserPendingsIssuesUseCase;
    private final CheckForITemPartAvailabilityUseCase checkForITemPartAvailabilityUseCase;
    public CreateReservationUseCase(UserDAO userDAO,
                          PartItemDAO itemPartDAO,
                          ReservationDAO reservationDAO,
                          EventDAO eventDAO,
                          CheckForUserPendingsIssuesUseCase checkForUserPendingsIssuesUseCase,
                          CheckForITemPartAvailabilityUseCase checkForITemPartAvailabilityUseCase){
        this.userDAO = userDAO;
        this.itemPartDAO =itemPartDAO;
        this.reservationDAO = reservationDAO;
        this.eventDAO = eventDAO;
        this.checkForUserPendingsIssuesUseCase = checkForUserPendingsIssuesUseCase;
        this.checkForITemPartAvailabilityUseCase = checkForITemPartAvailabilityUseCase;
    }
    public Reservation createReservation(String userId, Set<PartItem> itemParts, LocalDateTime dateTimeScheduledForCheckout){
        Optional<User> user = userDAO.findOne(userId);
        if (user.isEmpty())
            throw new EntityNotFoundException("User not found");
        checkForUserPendingsIssuesUseCase.checkForUserPendingIssues(userId);
        checkForITemPartAvailabilityUseCase.checkForAvailabilityOfTheParts(itemParts);
        Technician loggedTechnician = Session.getLoggedTechnician();
        Reservation reservation = new Reservation(dateTimeScheduledForCheckout, user.get(), loggedTechnician);
        int id = reservationDAO.create(reservation);
        reservation.setReservationId(id);
        itemParts.forEach(itemPart -> itemPart.setStatus(StatusPart.RESERVED));
        itemParts.forEach(itemPartDAO::update);
        itemParts.forEach(itemPart -> reservationDAO.createReservedItem(new ReservedItem(itemPart, reservation)));
        itemParts.forEach(itemPart -> eventDAO.create(new Event(EventType.RESERVATION, user.get(), loggedTechnician, itemPart)));
        return reservation;
    }
}
