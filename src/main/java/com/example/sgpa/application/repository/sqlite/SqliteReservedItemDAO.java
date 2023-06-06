package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.reservation.ReservedItem;
import com.example.sgpa.domain.entities.reservation.ReservedItemKey;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.reservation.ReservedItemDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteReservedItemDAO implements ReservedItemDAO {

    @Override
    public ReservedItemKey create(ReservedItem reservedItem) {
        int reservationId = reservedItem.getRelatedReservation().getReservationId();
        int patrimonialId = reservedItem.getItemPart().getPatrimonialId();
        String sql = "INSERT INTO reservation_item(reservation_id, part_item_id) VALUES(?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, reservationId);
            ps.setInt(2, patrimonialId);
            ps.execute();
            return  new ReservedItemKey( reservationId,  patrimonialId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<ReservedItem> findOne(ReservedItemKey type) {
        return Optional.empty();
    }

    @Override
    public List<ReservedItem> findAll() {
        return null;
    }

    @Override
    public void update(ReservedItem obj) {

    }

    @Override
    public boolean delete(ReservedItem obj) {
        return false;
    }
}
