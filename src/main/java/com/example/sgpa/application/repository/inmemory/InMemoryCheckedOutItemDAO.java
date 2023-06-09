package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.CheckedOutItemKey;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCheckedOutItemDAO implements CheckedOutItemDAO {

    Map<CheckedOutItemKey, CheckedOutItem> db = new HashMap<>();
    @Override
    public CheckedOutItemKey create(CheckedOutItem item) {
        CheckedOutItemKey key = new CheckedOutItemKey(item.getRelatedCheckout().getCheckOutId(), item.getPartItem().getPatrimonialId());
        if (db.containsKey(key))
            throw new RuntimeException("Key already exists.");
        db.put(key, item);
        return key;
    }

    @Override
    public Optional<CheckedOutItem> findOne(CheckedOutItemKey key) {
        return Optional.ofNullable(db.get(key));
    }

    @Override
    public List<CheckedOutItem> findAll() {
       return db.values().stream().toList();
    }

    @Override
    public void update(CheckedOutItem item) {
        CheckedOutItemKey key = new CheckedOutItemKey(item.getRelatedCheckout().getCheckOutId(), item.getPartItem().getPatrimonialId());
        if (!db.containsKey(key))
            throw new RuntimeException("Item do not exist in data base.");
        db.put(key, item);
    }

    @Override
    public boolean delete(CheckedOutItem item) {
        CheckedOutItemKey key = new CheckedOutItemKey(item.getRelatedCheckout().getCheckOutId(), item.getPartItem().getPatrimonialId());
        return db.remove(key, item);
    }

    @Override
    public Optional<CheckedOutItem> findOpenByPartItemId(int patrimonialId) {
        return db.values().stream()
                .filter(checkedOutItem -> checkedOutItem.getPartItem().getPatrimonialId()==patrimonialId)
                .filter(checkedOutItem -> checkedOutItem.isOpen()).findFirst();
    }

    @Override
    public List<CheckedOutItem> findLateByUser(int userId) {
        return db.values().stream()
                .filter(checkedOutItem -> checkedOutItem.getRelatedCheckout().getUser().getInstitutionalId()==userId)
                .filter(checkedOutItem -> checkedOutItem.isLate()).collect(Collectors.toList());
    }

    @Override
    public List<CheckedOutItem> findByCheckOutId(int userId) {
        return null;
    }

    @Override
    public List<CheckedOutItem> getReportByPart(int patrimonialId, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public List<CheckedOutItem> getReportByUser(int userId, LocalDateTime start, LocalDateTime end) {
        return null;
    }
}
