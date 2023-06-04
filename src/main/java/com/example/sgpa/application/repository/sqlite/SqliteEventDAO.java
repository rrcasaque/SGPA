package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.historical.EventDAO;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class SqliteEventDAO implements EventDAO {
    @Override
    public List<Event> getReportByDate(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public List<Event> getReportByUser(User user, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public List<Event> getReportByPart(Part part, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public Integer create(Event event) {
        int createdId;
        String sql = "INSERT INTO event(event_type, time_stamp, part_item_id, user_id, technician_id) \n" +
                "VALUES (?,?,?,?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1,event.getType().toString());
            ps.setString(2,event.getTimeStamp().toString());
            ps.setInt(3,event.getItemPart().getPatrimonialId());
            ps.setInt(4,event.getRequester().getInstitutionalId());
            ps.setInt(5, Session.getLoggedTechnician().getInstitutionalId());
            ps.executeUpdate();
            createdId = ps.getGeneratedKeys().getInt(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return createdId;
    }

    @Override
    public Optional<Event> findOne(Integer type) {
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public void update(Event obj) {

    }

    @Override
    public boolean delete(Event obj) {
        return false;
    }
}
