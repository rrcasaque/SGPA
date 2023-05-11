package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.List;

public interface CheckedOutItemDAO extends DAO<CheckedOutItem, Integer> {
    List<CheckedOutItem> findLate();
    List<CheckedOutItem> findLateByUser(User user);
    List<CheckedOutItem> findByUser(User user);
    List<CheckedOutItem> findByPeriodUser(User user);
    List<CheckedOutItem> findLateByPeriod(User user);
}
