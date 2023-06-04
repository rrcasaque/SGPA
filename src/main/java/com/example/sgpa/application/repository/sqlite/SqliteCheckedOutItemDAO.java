package com.example.sgpa.application.repository.sqlite;
import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.CheckedOutItemKey;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.usecases.checkout.CheckOutDAO;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SqliteCheckedOutItemDAO implements CheckedOutItemDAO {
    @Override
    public Optional<CheckedOutItem> findOpenByPartItemId(int patrimonialId) {
        CheckOutDAO checkOutDAO = new SqliteCheckOutDAO();
        PartItemDAO partItemDAO = new SqlitePartItemDAO();
        String sql = "select * from checkout_item where part_item_id = ? and return_date is null;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, patrimonialId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int checkout_id = rs.getInt("checkout_id") ;
                int part_item_id = rs.getInt("part_item_id");
                LocalDate due_date = LocalDate.parse(rs.getString("due_date"));
                Checkout checkout = checkOutDAO.findOne(checkout_id).orElseThrow();
                PartItem partItem = partItemDAO.findOne(part_item_id).orElseThrow();
                CheckedOutItem  checkedOutItem = new CheckedOutItem(partItem, checkout, due_date);
                return Optional.of(checkedOutItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public List<CheckedOutItem> findLateByUser(int userId) {
        PartItemDAO partItemDAO = new SqlitePartItemDAO();
        CheckOutDAO checkOutDAO = new SqliteCheckOutDAO();
        List<CheckedOutItem> lateParts = new ArrayList<>();
        String sql = "SELECT * \n" +
                "from ((checkout_item ci join checkout c on ci.checkout_id = c.checkout_id) join user u on u.institutional_id = c.user_id) \n" +
                "where user_id = ? and ci.return_date is null and due_date < DATE('now','localtime');";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int part_item_id = rs.getInt("part_item_id");
                PartItem partItem = partItemDAO.findOne(part_item_id).orElseThrow();
                int checkout_id = rs.getInt("checkout_id");
                Checkout checkout = checkOutDAO.findOne(checkout_id).orElseThrow();
                CheckedOutItem checkedOutItem = new CheckedOutItem(partItem, checkout);
                lateParts.add(checkedOutItem);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lateParts;
    }

    @Override
    public List<CheckedOutItem> findByCheckOutId(int checkOutId) {
        PartItemDAO partItemDAO = new SqlitePartItemDAO();
        List<CheckedOutItem> parts = new ArrayList<>();
        String sql = "SELECT * from checkout_item" +
                " where checkout_id = ?;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, checkOutId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int part_item_id = rs.getInt("part_item_id");
                PartItem partItem = partItemDAO.findOne(part_item_id).orElseThrow();
                LocalDate due_date = LocalDate.parse(rs.getString("due_date"));
                CheckedOutItem checkedOutItem = new CheckedOutItem(partItem,due_date,null);
                String return_date = rs.getString("return_date");
                if (return_date != null)
                checkedOutItem.setReturnDate(LocalDateTime.parse(return_date));
                parts.add(checkedOutItem);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return parts;
    }

    @Override
    public CheckedOutItemKey create(CheckedOutItem checkedOutItem) {
        CheckedOutItemKey checkedOutItemKey;
        String sql = "INSERT INTO checkout_item(checkout_id, part_item_id, due_date) VALUES(?,?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            int patItemId =checkedOutItem.getPartItem().getPatrimonialId();
            int checkoutId = checkedOutItem.getRelatedCheckout().getCheckOutId();
            ps.setInt(1,checkoutId);
            ps.setInt(2,patItemId);
            ps.setString(3,checkedOutItem.getDueDate().toString());
            ps.executeUpdate();
            checkedOutItemKey = new CheckedOutItemKey(checkoutId, patItemId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return checkedOutItemKey;
    }
    @Override
    public Optional<CheckedOutItem> findOne(CheckedOutItemKey key) {
        CheckOutDAO checkOutDAO = new SqliteCheckOutDAO();
        PartItemDAO partItemDAO = new SqlitePartItemDAO();
        String sql ="select * from checkout_item where checkout_id = ? and part_item_id= ?;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)) {
            ps.setInt(1, key.getCheckoutId());
            ps.setInt(2, key.getPartItemId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Checkout checkout = checkOutDAO.findOne(rs.getInt("checkout_id")).orElseThrow();
                PartItem partItem = partItemDAO.findOne(rs.getInt("part_item_id")).orElseThrow();
                LocalDate dueDate = LocalDate.parse(rs.getString("due_date"));
                CheckedOutItem checkedOutItem = new CheckedOutItem(partItem, checkout, dueDate);
                String return_date = rs.getString("return_date");
                if (return_date != null) checkedOutItem.setReturnDate(LocalDateTime.parse(return_date));
                return Optional.of(checkedOutItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<CheckedOutItem> findAll() {
        return null;
    }

    @Override
    public void update(CheckedOutItem checkedOutItem) {
        String sql ="UPDATE checkout_item \n" +
                "SET  return_date = ?\n" +
                "WHERE checkout_id = ? AND part_item_id = ?";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1, checkedOutItem.getReturnDate().toString());
            ps.setInt(2, checkedOutItem.getRelatedCheckout().getCheckOutId());
            ps.setInt(3, checkedOutItem.getPartItem().getPatrimonialId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(CheckedOutItem obj) {
        return false;
    }
}
