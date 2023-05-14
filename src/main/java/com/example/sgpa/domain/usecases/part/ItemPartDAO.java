package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ItemPartDAO extends DAO<ItemPart, String> {
   Set<ItemPart> findReservedItemParts();
   Set<ItemPart> findCheckedOutsItemParts();
   Set<ItemPart> findCheckedOutsItemPartsByUser(String userId);
   boolean isItemPartAvailable(String patrimonialId);
}
