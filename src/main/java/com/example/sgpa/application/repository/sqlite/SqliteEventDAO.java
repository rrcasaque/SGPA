package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.historical.EventType;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteEventDAO implements EventDAO {
    @Override
    public List<Event> getReportByPart(int patrimonialId, LocalDateTime start, LocalDateTime end) {
        PartItemDAO partItemDAO = new SqlitePartItemDAO();
        UserDAO userDAO = new SqliteUserDAO();
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM event " +
                     "WHERE part_item_id = ? " +
                     "and datetime(time_stamp) between datetime(?) and datetime(?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, patrimonialId);
            ps.setString(2, start.toString());
            ps.setString(3, end.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int part_item_id = rs.getInt("part_item_id");
                PartItem partItem = partItemDAO.findOne(part_item_id).orElseThrow();
                int user_id = rs.getInt("user_id");
                User requester = userDAO.findOne(user_id).orElseThrow();
                int technician_id = rs.getInt("technician_id");
                User technician = userDAO.findOne(technician_id).orElseThrow();
                LocalDateTime time_stamp = LocalDateTime.parse(rs.getString("time_stamp"));
                EventType event_type = EventType.strToEnum(rs.getString("event_type"));
                Event event = new Event(event_type, requester, technician, partItem, time_stamp);
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }
    @Override
public List<Event> getReportByUser(int userId, LocalDateTime start, LocalDateTime end) {
    PartItemDAO partItemDAO = new SqlitePartItemDAO();
    UserDAO userDAO = new SqliteUserDAO();
    List<Event> events = new ArrayList<>();
    String sql = "SELECT * FROM event " +
                 "WHERE user_id = ? " +
                 "AND datetime(time_stamp) BETWEEN datetime(?) AND datetime(?);";
    try (PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)) {
        ps.setInt(1, userId);
        ps.setString(2, start.toString());
        ps.setString(3, end.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int part_item_id = rs.getInt("part_item_id");
            PartItem partItem = partItemDAO.findOne(part_item_id).orElseThrow();
            int user_id = rs.getInt("user_id");
            User requester = userDAO.findOne(user_id).orElseThrow();
            int technician_id = rs.getInt("technician_id");
            User technician = userDAO.findOne(technician_id).orElseThrow();
            LocalDateTime time_stamp = LocalDateTime.parse(rs.getString("time_stamp"));
            EventType event_type = EventType.strToEnum(rs.getString("event_type"));
            Event event = new Event(event_type, requester, technician, partItem, time_stamp);
            events.add(event);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return events;
}

@Override
public List<Event> getReportByDate(LocalDateTime start, LocalDateTime end) {
    PartItemDAO partItemDAO = new SqlitePartItemDAO();
    UserDAO userDAO = new SqliteUserDAO();
    List<Event> events = new ArrayList<>();
    String sql = "SELECT * FROM event " +
                 "WHERE datetime(time_stamp) BETWEEN datetime(?) AND datetime(?);";
    try (PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)) {
        ps.setString(1, start.toString());
        ps.setString(2, end.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int part_item_id = rs.getInt("part_item_id");
            PartItem partItem = partItemDAO.findOne(part_item_id).orElseThrow();
            int user_id = rs.getInt("user_id");
            User requester = userDAO.findOne(user_id).orElseThrow();
            int technician_id = rs.getInt("technician_id");
            User technician = userDAO.findOne(technician_id).orElseThrow();
            LocalDateTime time_stamp = LocalDateTime.parse(rs.getString("time_stamp"));
            EventType event_type = EventType.strToEnum(rs.getString("event_type"));
            Event event = new Event(event_type, requester, technician, partItem, time_stamp);
            events.add(event);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return events;
}

    @Override
    public Integer create(Event event) {
        int createdId;
        String sql = "INSERT INTO event(event_type, time_stamp, part_item_id, user_id, technician_id) \n" +
                "VALUES (?,?,?,?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1,event.getEventType().toString());
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
