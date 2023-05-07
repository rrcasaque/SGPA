package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.Professor;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Checkout {
    private int checkOutId;
    private Technician technician;
    private User user;
    private Set<CheckedOutItem> checkedOutItems;
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
        reservation.getItems().forEach(itemPart -> checkedOutItems.add(new CheckedOutItem(itemPart)));
    }

    public Checkout(List<ItemPart> parts, User user, Technician technician){
        this.user = user;
        this.technician = technician;
        parts.forEach(itemPart -> checkedOutItems.add(new CheckedOutItem(itemPart)));
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
        checkedOutItems.add(new CheckedOutItem(item));
    }

    public void addCheckedOutItems(List<ItemPart> items){
        items.forEach(itemPart-> checkedOutItems.add(new CheckedOutItem(itemPart)));
    }
    private class CheckedOutItem {
        private ItemPart itemPart;
        private LocalDate dueDate;
        private LocalDateTime returnDate;

        private CheckedOutItem(ItemPart item){
            this.itemPart = item;
            this.dueDate = getDueDate();
        }
        private LocalDate getDueDate(){
            if (user instanceof Professor)
                return LocalDate.now().plusDays(itemPart.getPart().getMaxDaysCheckedOutForProfessor());
            return LocalDate.now().plusDays(itemPart.getPart().getMaxDaysCheckedOutForStudent());
        }

        public void setReturnDate(LocalDateTime returnDate) {
            this.returnDate = returnDate;
        }
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
