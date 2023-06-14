package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.CheckedOutItemKey;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CheckedOutItemDAO extends DAO<CheckedOutItem, CheckedOutItemKey> {
    Optional<CheckedOutItem> findOpenByPartItemId(int patrimonialId);
    List<CheckedOutItem> findLateByUser(int userId);
    List<CheckedOutItem> findByCheckOutId(int userId);

    List<CheckedOutItem> getReportByPart(int patrimonialId, LocalDateTime start, LocalDateTime end);

    List<CheckedOutItem> getReportByUser(int userId, LocalDateTime start, LocalDateTime end);
}
