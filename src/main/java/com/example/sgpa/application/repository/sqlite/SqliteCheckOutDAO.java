package com.example.sgpa.application.repository.sqlite;
import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.checkout.CheckOutDAO;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteCheckOutDAO implements CheckOutDAO {
    CheckedOutItemDAO checkedOutItemDAO = new SqliteCheckedOutItemDAO();
    @Override
    public Integer create(Checkout checkout) {
        String sql = "INSERT INTO checkout(technician_id, user_id, checkout_date, reservation_id) VALUES(?,?,?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, Session.getLoggedTechnician().getInstitutionalId());
            ps.setInt(2,checkout.getUser().getInstitutionalId());
            ps.setString(3,checkout.getCheckOutDateTime().toString());
            ps.setInt(4, checkout.getAssociatedReservationId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            return  rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Optional<Checkout> findOne(Integer checkOutId) {
        Checkout checkout = getEmptyCheckout(checkOutId).orElse(null);
        if (checkout == null) return Optional.empty();
        List<CheckedOutItem> relatedCheckedOutItems = checkedOutItemDAO.findByCheckOutId(checkOutId);
        relatedCheckedOutItems.forEach(item -> item.setRelatedCheckout(checkout));
        checkout.addCheckedOutItems(relatedCheckedOutItems);
        return Optional.of(checkout);
    }

    @Override
    public List<Checkout> findAll() {
        List<Checkout> checkOuts = new ArrayList<>();
        String sql = "select * from checkout";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int checkOutId = rs.getInt("checkout_id");
                Checkout checkout = getEmptyCheckout(checkOutId).orElseThrow();
                List<CheckedOutItem> relatedCheckedOutItems = checkedOutItemDAO.findByCheckOutId(checkOutId);
                relatedCheckedOutItems.forEach(item -> item.setRelatedCheckout(checkout));
                checkout.addCheckedOutItems(relatedCheckedOutItems);
                checkOuts.add(checkout);
            }
            return checkOuts;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return checkOuts;
    }

    @Override
    public void update(Checkout obj) {

    }

    @Override
    public boolean delete(Checkout obj) {
        return false;
    }

    public Optional<Checkout> getEmptyCheckout(int checkOutId){
        Checkout checkOut;
        UserDAO userDAO = new SqliteUserDAO();
        String sql = "select * from checkout where checkout_id = ?;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, checkOutId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int user_id = rs.getInt("user_id");
                User user = userDAO.findOne(user_id).orElseThrow();
                int technician_id= rs.getInt("technician_id");
                User technician = userDAO.findOne(technician_id).orElseThrow();
                LocalDateTime checkoutDateTime = LocalDateTime.parse(rs.getString("checkout_date"));
                int associatedReservationId = rs.getInt("reservation_id");
                checkOut = new Checkout(checkOutId,user,technician,associatedReservationId,checkoutDateTime);
                return Optional.of(checkOut);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
