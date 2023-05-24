package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.usecases.checkout.CheckOutDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCheckOutDAO implements CheckOutDAO {
    private static final Map<Integer, Checkout> db = new HashMap<>();
    private static int nbr_checkouts;
    @Override
    public Integer create(Checkout checkout) {
        nbr_checkouts++;
        db.put(nbr_checkouts, checkout);
        return nbr_checkouts;
    }

    @Override
    public Optional<Checkout> findOne(Integer key) {
        return Optional.ofNullable(db.get(key));
    }

    @Override
    public List<Checkout> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public void update(Checkout checkout) {
        int key = checkout.getCheckOutId();
        if (!db.containsKey(key))
            throw new RuntimeException("Check out do not exist in data base.");
        db.put(key, checkout);
    }

    @Override
    public boolean delete(Checkout checkout) {
        return db.remove(checkout.getCheckOutId(), checkout);
    }
}
