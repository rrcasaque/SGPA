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
import java.util.List;
import java.util.Optional;

public class SqliteCheckOutDAO implements CheckOutDAO {
    @Override
    public Integer create(Checkout checkout) {
        String sql = "INSERT INTO checkout(technician_id, user_id) VALUES(?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, Session.getLoggedTechnician().getInstitutionalId());
            ps.setInt(2,checkout.getUser().getInstitutionalId());
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
        CheckedOutItemDAO checkedOutItemDAO = new SqliteCheckedOutItemDAO();
        List<CheckedOutItem> relatedCheckedOutItems = checkedOutItemDAO.findByCheckOutId(checkOutId);
        relatedCheckedOutItems.forEach(item -> item.setRelatedCheckout(checkout));
        checkout.addCheckedOutItems(relatedCheckedOutItems);
        return Optional.of(checkout);
    }

    @Override
    public List<Checkout> findAll() {
        return null;
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
//        ReservationDAO reservationDAO = new SqliteReservationDAO();
//        Reservation reservation;
        String sql = "select * from checkout where checkout_id = ?;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, checkOutId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int user_id = rs.getInt("user_id");
                User user = userDAO.findOne(user_id).orElseThrow();
                int technician_id= rs.getInt("technician_id");
                User technician = userDAO.findOne(technician_id).orElseThrow();
//                int reservation_id = rs.getInt("reservation_id");
//                reservation = reservationDAO.findOne(reservation_id).orElse(null);
//                checkOut = new Checkout(checkOutId,user,technician,reservation);
                checkOut = new Checkout(checkOutId,user,technician,null);
                return Optional.of(checkOut);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
