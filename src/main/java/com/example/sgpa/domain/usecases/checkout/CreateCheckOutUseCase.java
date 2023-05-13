package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.PartDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CreateCheckOutUseCase {
    private UserDAO userDAO;
    private CheckOutDAO checkOutDAO;
    private CheckedOutItemDAO checkedOutItemDAO;
    CreateCheckOutUseCase(UserDAO userDAO,
                          CheckOutDAO checkOutDAO,
                          CheckedOutItemDAO checkedOutItemDAO){
        this.userDAO = userDAO;
        this.checkOutDAO = checkOutDAO;
        this.checkedOutItemDAO = checkedOutItemDAO;
    }
    Checkout createCheckou(int technicianId, String userId, Set<ItemPart> itemParts){
        //fazer validação e criação do usuário
        Optional<User> user = userDAO.findOne(userId);
        if (user.isEmpty())
            throw new EntityNotFoundException("User not found");

        List<CheckedOutItem> lateItems = checkedOutItemDAO.findLateByUser(userId);
        if (!lateItems.isEmpty()){
            StringBuilder message = new StringBuilder("User has the followinf overdue parts:\n");
            lateItems.forEach(checkedOutItem -> message
                    .append("PatrimonialId: ")
                    .append(checkedOutItem.getItemPart().getPatrimonialId())
                    .append(". Part description: ")
                    .append(checkedOutItem.getItemPart().getPart().getDescription())
                    .append(". Due Date: ")
                    .append(checkedOutItem.getDueDate().toString())
                    .append("\n"));
            throw new RuntimeException(message.toString());
        }

        //fazer validação e criação da lista de peças
        Checkout checkout = new Checkout(itemParts, user, );
        int id = checkOutDAO.create(checkout);
        checkout.getCheckedOutItems().forEach(checkedOutItem -> checkedOutItemDAO.create(checkedOutItem));
        //Checkout checkout = checkOutDAO.findOne(id);
    }





}
