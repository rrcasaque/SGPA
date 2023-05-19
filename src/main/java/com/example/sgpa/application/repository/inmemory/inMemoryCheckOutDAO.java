package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.usecases.checkout.CheckOutDAO;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class inMemoryCheckOutDAO implements CheckOutDAO {
    private static final Map<Integer, Checkout> db = new HashMap<>();
    private static int checkoutId;
    @Override
    public Integer create(Checkout obj) {
        return null;
    }

    @Override
    public Optional<Checkout> findOne(Integer type) {
        return Optional.empty();
    }

    @Override
    public List<Checkout> findAll() {
        return null;
    }

    @Override
    public void update(Checkout obj) {

    }

    @Override
    public boolean delete(Checkout obj) {
        return false;
    }
}
