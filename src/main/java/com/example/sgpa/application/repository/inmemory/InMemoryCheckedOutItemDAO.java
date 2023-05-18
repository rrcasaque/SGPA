package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.CheckedOutItemKey;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryCheckedOutItemDAO implements CheckedOutItemDAO {
    @Override
    public CheckedOutItemKey create(CheckedOutItem obj) {
        return null;
    }

    @Override
    public Optional<CheckedOutItem> findOne(CheckedOutItemKey type) {
        return Optional.empty();
    }

    @Override
    public List<CheckedOutItem> findAll() {
        return null;
    }

    @Override
    public void update(CheckedOutItem obj) {

    }

    @Override
    public boolean delete(CheckedOutItem obj) {
        return false;
    }

    @Override
    public Optional<CheckedOutItem> findNotReturned(String patrimonialId) {
        return Optional.empty();
    }

    @Override
    public List<CheckedOutItem> findLateByUser(String userId) {
        return null;
    }
}
