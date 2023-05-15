package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.user.Professor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CheckedOutItem {
    private Checkout relatedCheckout;
    private ItemPart itemPart;
    private LocalDate dueDate;
    private LocalDateTime returnDate;
    public CheckedOutItem(ItemPart item, Checkout relatedCheckout){
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

    public ItemPart getItemPart() {
        return itemPart;
    }
    public LocalDate getDueDate() {
        return dueDate;
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
