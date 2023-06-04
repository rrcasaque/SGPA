package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.Professor;
import com.example.sgpa.domain.entities.user.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CheckedOutItem {
    private Checkout relatedCheckout;
    private final PartItem itemPart;
    private LocalDate dueDate;
    private LocalDateTime returnDate;
    public CheckedOutItem(PartItem item, LocalDate dueDate){
        this.itemPart = item;
        this.dueDate = dueDate;
    }
    public CheckedOutItem(PartItem item, LocalDate dueDate, LocalDateTime returnDate){
        this.itemPart = item;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
    public CheckedOutItem(PartItem item, Checkout relatedCheckout){
        this.itemPart = item;
        this.relatedCheckout = relatedCheckout;
        setDueDate();
    }
    private void setDueDate(){
        if (relatedCheckout.getUser().getUserType() == UserType.PROFESSOR) {
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
    public void setRelatedCheckout(Checkout relatedCheckout) {
        this.relatedCheckout = relatedCheckout;
    }
    public boolean isLate(){
        return LocalDate.now().isAfter(dueDate) && returnDate == null;
    }
    public boolean isOpen(){
        return returnDate == null;
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
