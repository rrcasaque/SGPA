package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.CheckedOutItemKey;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCheckedOutItemDAO implements CheckedOutItemDAO {

    Map<CheckedOutItemKey, CheckedOutItem> db = new HashMap<>();
    @Override
    public CheckedOutItemKey create(CheckedOutItem item) {
        CheckedOutItemKey key = new CheckedOutItemKey(item.getRelatedCheckout(), item.getItemPart());
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
        CheckedOutItemKey key = new CheckedOutItemKey(item.getRelatedCheckout(), item.getItemPart());
        if (!db.containsKey(key))
            throw new RuntimeException("Item do not exist in data base.");
        db.put(key, item);
    }

    @Override
    public boolean delete(CheckedOutItem item) {
        CheckedOutItemKey key = new CheckedOutItemKey(item.getRelatedCheckout(), item.getItemPart());
        return db.remove(key, item);
    }

    @Override
    public Optional<CheckedOutItem> findNotReturned(String patrimonialId) {
        return db.values().stream()
                .filter(checkedOutItem -> checkedOutItem.getItemPart().getPatrimonialId().equals(patrimonialId))
                .filter(checkedOutItem -> checkedOutItem.isOpen()).findFirst();
    }

    @Override
    public List<CheckedOutItem> findLateByUser(String userId) {
        return db.values().stream()
                .filter(checkedOutItem -> checkedOutItem.getRelatedCheckout().getUser().getInstitutionalId().equals(userId))
                .filter(checkedOutItem -> checkedOutItem.isLate()).collect(Collectors.toList());
    }
}
