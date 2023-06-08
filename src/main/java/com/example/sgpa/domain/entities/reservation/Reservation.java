package com.example.sgpa.domain.entities.reservation;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Reservation {
    private int reservationId;
    private LocalDate dateScheduledForCheckout;
    private User requester;
    private User technician;
    private ReservationStatus status;
    private final Set<PartItem> items = new HashSet<>();
    public Reservation(LocalDate dateScheduledForCheckout,
                       User requester, User technician, ReservationStatus status) {
        this.dateScheduledForCheckout = dateScheduledForCheckout;
        this.requester = requester;
        this.technician = technician;
        this.status=status;
    }
    public Reservation(int id, LocalDate dateScheduledForCheckout,
                       User requester, User technician, ReservationStatus status) {
        this(dateScheduledForCheckout, requester,  technician, status);
        this.reservationId = id;
    }
    public void setDateScheduledForCheckout(LocalDate dateScheduledForCheckout) {
        this.dateScheduledForCheckout = dateScheduledForCheckout;
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
    public LocalDate getDateScheduledForCheckout() {
        return dateScheduledForCheckout;
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
    public ReservationStatus getStatus() {return status;}
    public void setStatus(ReservationStatus status) {this.status = status;}
    public void addItem(PartItem item){
        items.add(item);
    }
    public void addItems(List<PartItem> items){
        this.items.addAll(items);
    }
    public Integer getUserId(){
        return requester.getInstitutionalId();
    }
    public String getScheduledCheckOutDate(){
        return dateScheduledForCheckout.toString();
    }
    public boolean isExpired(){
        return dateScheduledForCheckout.isBefore(LocalDate.now());
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

    public boolean isClosed() {
        return status != ReservationStatus.WAITING_CHECKOUT;
    }
}
