package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.reservation.ReservationStatus;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class SqliteReservationDAO implements ReservationDAO {
    PartItemDAO partItemDAO = new SqlitePartItemDAO();
    UserDAO userDAO = new SqliteUserDAO();
    @Override
    public Integer create(Reservation reservation) {
        String sql = "INSERT INTO reservation(date_time_scheduled_for_checkout, user_id, technician_id, status) VALUES(?,?,?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1, reservation.getDateScheduledForCheckout().toString());
            ps.setInt(2,reservation.getRequester().getInstitutionalId());
            ps.setInt(3,Session.getLoggedTechnician().getInstitutionalId());
            ps.setString(4,reservation.getStatus().toString());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            return  rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Reservation> findOne(Integer reservationId) {
        Reservation reservation = getEmptyReservation(reservationId).orElse(null);
        if (reservation == null) return Optional.empty();
        List<PartItem> partItems = new ArrayList<>(partItemDAO.findByReservationId(reservationId));
        reservation.addItems(partItems);
        return Optional.of(reservation);
    }

    private Optional<Reservation> getEmptyReservation(Integer reservationId) {
        Reservation reservation;
        String sql = "select * from reservation where reservation_id = ?;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int user_id = rs.getInt("user_id");
                User user = userDAO.findOne(user_id).orElseThrow();
                int technician_id= rs.getInt("technician_id");
                User technician = userDAO.findOne(technician_id).orElseThrow();
                LocalDate checkoutDate = LocalDate.parse(rs.getString("date_time_scheduled_for_checkout"));
                ReservationStatus status = ReservationStatus.strToEnum(rs.getString("status"));
                reservation = new Reservation(reservationId, checkoutDate, user,  technician, status);
                return Optional.of(reservation);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "select * from reservation";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int reservationId = rs.getInt("reservation_id");
                Reservation reservation = getEmptyReservation(reservationId).orElseThrow();
                List<PartItem> reservedItems = partItemDAO.findByReservationId(reservationId);
                reservation.addItems(reservedItems);
                reservations.add(reservation);
            }
            return reservations;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) {
        String sql ="UPDATE reservation \n" +
                "SET  date_time_scheduled_for_checkout = ?, user_id = ?, technician_id = ?, status = ?\n" +
                "WHERE reservation_id = ?;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1, reservation.getScheduledCheckOutDate());
            ps.setInt(2, reservation.getUserId());
            ps.setInt(3, reservation.getTechnician().getInstitutionalId());
            ps.setString(4, reservation.getStatus().toString());
            ps.setInt(5, reservation.getReservationId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Reservation> findExpired() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "select * from reservation " +
                     "where status = 'Aguardando retirada' " +
                          "and date(date_time_scheduled_for_checkout) < date('now', 'localtime')";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int reservationId = rs.getInt("reservation_id");
                Reservation reservation = getEmptyReservation(reservationId).orElseThrow();
                List<PartItem> reservedItems = partItemDAO.findByReservationId(reservationId);
                reservation.addItems(reservedItems);
                reservations.add(reservation);
            }
            return reservations;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public boolean delete(Reservation obj) {
        return false;
    }
}
