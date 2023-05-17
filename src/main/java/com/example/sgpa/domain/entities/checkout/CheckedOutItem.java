package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.Professor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CheckedOutItem {
    private Checkout relatedCheckout;
    private PartItem itemPart;
    private LocalDate dueDate;
    private LocalDateTime returnDate;
    public CheckedOutItem(PartItem item, Checkout relatedCheckout){
        this.itemPart = item;
        this.relatedCheckout = relatedCheckout;
        setDueDate();
    }
    private void setDueDate(){
        if (relatedCheckout.getUser() instanceof Professor) {
            this.dueDate = LocalDate.now().plusDays(itemPart.getPart().getMaxDaysCheckedOutForProfessor());
        }else {
            this.dueDate = LocalDate.now().plusDays(itemPart.getPart().getMaxDaysCheckedOutForStudent());
        }
    }

    public PartItem getItemPart() {
        return itemPart;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public Checkout getRelatedCheckout() {
        return relatedCheckout;
    }
    public LocalDateTime getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckedOutItem that = (CheckedOutItem) o;
        return Objects.equals(relatedCheckout, that.relatedCheckout) && Objects.equals(itemPart, that.itemPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relatedCheckout, itemPart);
    }
}
