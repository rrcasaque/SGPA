package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Checkout {
    private int checkOutId;
    private User technician;
    private User user;
    private LocalDateTime checkOutDateTime;
    private final Set<CheckedOutItem> checkedOutItems = new HashSet<>();
    private int associatedReservationId;
    public Checkout(){}
    public Checkout(User user, Technician technician){
        this.user = user;
        this.technician = technician;
    }
    public Checkout(int checkOutId, User user, User technician){
        this.user = user;
        this.technician = technician;
        this.checkOutId  = checkOutId;
    }
    public Checkout(int checkOutId, User user, User technician, int associatedReservationId, LocalDateTime checkOutDateTime){
        this.user = user;
        this.technician = technician;
        this.checkOutId  = checkOutId;
        this.associatedReservationId = associatedReservationId;
        this.checkOutDateTime = checkOutDateTime;
    }
    public Checkout(Reservation reservation){
        this.user = reservation.getRequester();
        this.technician = reservation.getTechnician();
        this.associatedReservationId = reservation.getReservationId();
        this.checkOutDateTime = LocalDateTime.now();
        reservation.getItems().forEach(itemPart -> checkedOutItems.add(new CheckedOutItem(itemPart, this)));
    }
    public Checkout(Set<PartItem> parts, User user, User technician){
        this.user = user;
        this.technician = technician;
        this.checkOutDateTime = LocalDateTime.now();
        parts.forEach(itemPart -> checkedOutItems.add(new CheckedOutItem(itemPart, this)));
    }
    public Set<CheckedOutItem> getCheckedOutItems() {
        return checkedOutItems;
    }
    public Integer getAssociatedReservationId() {
        return associatedReservationId;
    }
    public int getCheckOutId() {
        return checkOutId;
    }
    public void setCheckOutId(int checkOutId) {
        this.checkOutId = checkOutId;
    }
    public User getTechnician() {
        return technician;
    }
    public void setTechnician(Technician technician) {
        this.technician = technician;
    }
    public User getUser() {
        return user;
    }
    public Integer getUserId(){return user.getInstitutionalId();}
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }
    public String getCheckOutDate() {
        return checkOutDateTime.toString().split("T")[0];
    }
    public void addCheckedOutItem(CheckedOutItem checkedOutItem){
        checkedOutItems.add(checkedOutItem);
    }
    public void addCheckedOutItems(List<CheckedOutItem> checkedOutItems) {
        this.checkedOutItems.addAll(checkedOutItems);
    }
    public void addCheckedOutItem(PartItem item){
        checkedOutItems.add(new CheckedOutItem(item, this));
    }
    public void addCheckedOutItems(Set<PartItem> items){
        items.forEach(itemPart-> checkedOutItems.add(new CheckedOutItem(itemPart, this)));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkout checkout = (Checkout) o;
        return checkOutId == checkout.checkOutId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(checkOutId);
    }
}
