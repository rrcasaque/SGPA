package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.user.Professor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CheckedOutItem {
    private Checkout relatedCheckout;
    private ItemPart itemPart;
    private LocalDate dueDate;
    private LocalDateTime returnDate;
    public CheckedOutItem(ItemPart item, Checkout relatedCheckout){
        this.itemPart = item;
        this.relatedCheckout = relatedCheckout;
        this.dueDate = getDueDate();
    }
    private LocalDate getDueDate(){
        if (relatedCheckout.getUser() instanceof Professor)
            return LocalDate.now().plusDays(itemPart.getPart().getMaxDaysCheckedOutForProfessor());
        return LocalDate.now().plusDays(itemPart.getPart().getMaxDaysCheckedOutForStudent());
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

}
