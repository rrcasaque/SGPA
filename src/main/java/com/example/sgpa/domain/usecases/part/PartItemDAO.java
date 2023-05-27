package com.example.sgpa.domain.usecases.part;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.Set;

public interface PartItemDAO extends DAO<PartItem, Integer> {
   Set<PartItem> findReservedItemParts();
   Set<PartItem> findCheckedOutsItemParts();
   Set<PartItem> findCheckedOutsItemPartsByUser(String userId);
   boolean isPartItemAvailable(int patrimonialId);
}
