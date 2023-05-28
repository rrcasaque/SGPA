package com.example.sgpa.domain.entities.reservation;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Reservation {
    private int reservationId;
    private LocalDateTime dateTimeScheduledForCheckout;
    private User requester;
    private User technician;
    private final Set<PartItem> items = new HashSet<>();
    public Reservation(LocalDateTime dateTimeScheduledForCheckout,
                       User requester, User technician) {
        this.dateTimeScheduledForCheckout = dateTimeScheduledForCheckout;
        this.requester = requester;
        this.technician = technician;
    }
    public Reservation(int id, LocalDateTime dateTimeScheduledForCheckout,
                       User requester, Technician technician) {
        this(dateTimeScheduledForCheckout, requester,  technician);
        this.reservationId = id;
    }
    public void setDateTimeScheduledForCheckout(LocalDateTime dateTimeScheduledForCheckout) {
        this.dateTimeScheduledForCheckout = dateTimeScheduledForCheckout;
    }
    public void setRequester(User requester) {
        this.requester = requester;
    }
    public void setTechnician(Technician technician) {
        this.technician = technician;
    }
    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {this.reservationId = reservationId;}
    public LocalDateTime getDateTimeScheduledForCheckout() {
        return dateTimeScheduledForCheckout;
    }
    public User getRequester() {
        return requester;
    }
    public User getTechnician() {
        return technician;
    }
    public Set<PartItem> getItems() {
        return items;
    }
    public void addItem(PartItem item){
        items.add(item);
    }
    public void addItems(List<PartItem> items){
        items.addAll(items);
    }
    public void removeItem(PartItem item){
        items.remove(item);
    }
    public void removeItems(List<PartItem> items){
        items.removeAll(items);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }
}
