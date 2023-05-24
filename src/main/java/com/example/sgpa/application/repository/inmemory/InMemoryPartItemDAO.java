package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.usecases.part.PartItemDAO;

import java.util.*;

public class InMemoryPartItemDAO implements PartItemDAO {
    private static final Map<String, PartItem> partItemsDB = new HashMap<>();
    private static int nbr_partItems;
    @Override
    public Set<PartItem> findReservedItemParts() {
        return null;
    }

    @Override
    public Set<PartItem> findCheckedOutsItemParts() {
        return null;
    }

    @Override
    public Set<PartItem> findCheckedOutsItemPartsByUser(String userId) {
        return null;
    }

    @Override
    public boolean isPartItemAvailable(String patrimonialId) {
        if(!partItemsDB.containsKey(patrimonialId))
            throw new RuntimeException("Item not found in data base.");
        return partItemsDB.get(patrimonialId).getStatus() == StatusPart.AVAILABLE;
    }

    @Override
    public String create(PartItem obj) {
        return null;
    }

    @Override
    public Optional<PartItem> findOne(String type) {
        return Optional.empty();
    }

    @Override
    public List<PartItem> findAll() {
        return null;
    }

    @Override
    public void update(PartItem obj) {

    }

    @Override
    public boolean delete(PartItem obj) {
        return false;
    }
}
