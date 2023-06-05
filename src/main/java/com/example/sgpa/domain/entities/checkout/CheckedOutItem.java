package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CheckedOutItem {
    private Checkout relatedCheckout;
    private final PartItem partItem;
    private LocalDate dueDate;
    private LocalDateTime returnDate;
    public CheckedOutItem(PartItem item, LocalDate dueDate){
        this.partItem = item;
        this.dueDate = dueDate;
    }
    public CheckedOutItem(PartItem item, LocalDate dueDate, LocalDateTime returnDate){
        this.partItem = item;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
    public CheckedOutItem(PartItem item, Checkout relatedCheckout, LocalDate dueDate){
        this.partItem = item;
        this.dueDate = dueDate;
        this.relatedCheckout = relatedCheckout;
    }
    public CheckedOutItem(PartItem item, Checkout relatedCheckout){
        this.partItem = item;
        this.relatedCheckout = relatedCheckout;
        setDueDate();
    }
    private void setDueDate(){
        if (relatedCheckout.getUser().getUserType() == UserType.PROFESSOR) {
            this.dueDate = LocalDate.now().plusDays(partItem.getPart().getMaxDaysCheckedOutForProfessor());
        }else {
            this.dueDate = LocalDate.now().plusDays(partItem.getPart().getMaxDaysCheckedOutForStudent());
        }
    }
    public PartItem getPartItem() {
        return partItem;
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
    public String getType(){return partItem.getType();}
    public Integer getPatrimonialId(){return partItem.getPatrimonialId();}
    public String getStatus(){
        return partItem.getStatus().toString();
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
        return Objects.equals(relatedCheckout, that.relatedCheckout) && Objects.equals(partItem, that.partItem);
    }
    @Override
    public int hashCode() {
        return Objects.hash(relatedCheckout, partItem);
    }
}
