package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Checkout {
    private int checkOutId;
    private Technician technician;
    private User user;
    private final Set<CheckedOutItem> checkedOutItems = new HashSet<>();
    private Reservation associatedReservation;
    public Checkout(){}

    public Checkout(User user, Technician technician){
        this.user = user;
        this.technician = technician;
    }

    public Checkout(Reservation reservation){
        this.user = reservation.getRequester();
        this.technician = reservation.getTechnician();
        this.associatedReservation = reservation;
        reservation.getItems().forEach(itemPart -> checkedOutItems.add(new CheckedOutItem(itemPart, this)));
    }

    public Checkout(Set<ItemPart> parts, User user, Technician technician){
        this.user = user;
        this.technician = technician;
        parts.forEach(itemPart -> checkedOutItems.add(new CheckedOutItem(itemPart, this)));
    }

    public Set<CheckedOutItem> getCheckedOutItems() {
        return checkedOutItems;
    }

    public Reservation getAssociatedReservation() {
        return associatedReservation;
    }

    public int getCheckOutId() {
        return checkOutId;
    }

    public void setCheckOutId(int checkOutId) {
        this.checkOutId = checkOutId;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addCheckedOutItem(ItemPart item){
        checkedOutItems.add(new CheckedOutItem(item, this));
    }

    public void addCheckedOutItems(Set<ItemPart> items){
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
