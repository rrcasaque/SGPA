package com.example.sgpa.domain.usecases.user;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;

import java.util.List;

public class CheckForUserPendingsIssuesUseCase {
    private CheckedOutItemDAO checkedOutItemDAO;

    public CheckForUserPendingsIssuesUseCase(CheckedOutItemDAO checkedOutItemDAO){
        this.checkedOutItemDAO =   checkedOutItemDAO;
    }

    public void checkForUserPendingIssues(String userId) {
        List<CheckedOutItem> lateItemsOfUser = checkedOutItemDAO.findLateByUser(userId);
        if (!lateItemsOfUser.isEmpty()){
            StringBuilder message = new StringBuilder("User has the following overdue parts:\n");
            lateItemsOfUser.forEach(checkedOutItem -> message
                    .append("PatrimonialId: ")
                    .append(checkedOutItem.getItemPart().getPatrimonialId())
                    .append(". Part description: ")
                    .append(checkedOutItem.getItemPart().getPart().getDescription())
                    .append(". Due Date: ")
                    .append(checkedOutItem.getDueDate().toString())
                    .append("\n"));
            throw new RuntimeException(message.toString());
        }
    }
}
