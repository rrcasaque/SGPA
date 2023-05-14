package com.example.sgpa.domain.usecases.user;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;

import java.util.List;

public class VerifyUserPendingsUseCase {
    CheckedOutItemDAO checkedOutItemDAO;

    public VerifyUserPendingsUseCase(CheckedOutItemDAO checkedOutItemDAO){
        this.checkedOutItemDAO = checkedOutItemDAO;
    }

    public List<CheckedOutItem> verifyUserPendings(String userId){
        List<CheckedOutItem> lateItems = checkedOutItemDAO.findLateByUser(userId);
        return lateItems;
    }
}
