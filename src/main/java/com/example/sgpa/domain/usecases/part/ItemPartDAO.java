package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface ItemPartDAO extends DAO<ItemPart, String> {
    String create(ItemPart obj);
    Optional<ItemPart> findOne(ItemPart obj);
    List<ItemPart> findAll();
    void update(ItemPart obj);
    boolean delete(ItemPart obj);
}
