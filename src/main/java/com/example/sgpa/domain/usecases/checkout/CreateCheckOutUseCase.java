package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.PartDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;

import java.util.List;
import java.util.Set;

public class CreateCheckOutUseCase {
    CheckOutDAO checkOutDAO;
    CheckedOutItemDAO checkedOutItemDAO;
    CreateCheckOutUseCase(CheckOutDAO checkOutDAO, CheckedOutItemDAO checkedOutItemDAO){
        this.checkOutDAO = checkOutDAO;
        this.checkedOutItemDAO = checkedOutItemDAO;
    }
    Checkout checkoutItems(int technicianId, int userId, Set<ItemPart> items){
        Checkout checkout = new Checkout(items, user, technician);
        int id = checkOutDAO.create(checkout);
        checkout.getCheckedOutItems().forEach(checkedOutItem -> checkedOutItemDAO.create(checkedOutItem));
        //Checkout checkout = checkOutDAO.findOne(id);
    }





}
