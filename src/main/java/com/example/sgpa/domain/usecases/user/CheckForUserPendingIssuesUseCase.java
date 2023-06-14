package com.example.sgpa.domain.usecases.user;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;

import java.util.List;

public class CheckForUserPendingIssuesUseCase {
    private CheckedOutItemDAO checkedOutItemDAO;

    public CheckForUserPendingIssuesUseCase(CheckedOutItemDAO checkedOutItemDAO){
        this.checkedOutItemDAO =   checkedOutItemDAO;
    }

    public void checkForUserPendingIssues(int userId) throws Exception {
        List<CheckedOutItem> lateItemsOfUser = checkedOutItemDAO.findLateByUser(userId);
        if (!lateItemsOfUser.isEmpty()){
            StringBuilder message = new StringBuilder("User has the following overdue parts:\n");
            lateItemsOfUser.forEach(checkedOutItem -> message
                    .append("PatrimonialId: ")
                    .append(checkedOutItem.getPartItem().getPatrimonialId())
                    .append(". Part description: ")
                    .append(checkedOutItem.getPartItem().getPart().getType())
                    .append(". Due Date: ")
                    .append(checkedOutItem.getDueDate().toString())
                    .append("\n"));
            throw new Exception(message.toString());
        }
    }
}
