package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateReservationUseCase {

    void createReservation(User requester, Technician technician, Set<ItemPart> items,
                      LocalDateTime dateTimeScheduledForCheckout){

    }


}
