package com.example.sgpa.domain.usecases.reservation;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.entities.reservation.ReservedItemKey;
import com.example.sgpa.domain.usecases.utils.DAO;
import java.util.List;

public interface ReservedItemDAO extends DAO<ReservedItem, ReservedItemKey> {
}
