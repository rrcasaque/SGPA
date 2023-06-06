package com.example.sgpa.application.repository.sqlite;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlitePartItemDAO implements PartItemDAO {
    @Override
    public Set<PartItem> findReservedItemParts() {
        return null;
    }
    @Override
    public Set<PartItem> findCheckedOutsItemParts() {
        return null;
    }
    @Override
    public Set<PartItem> findCheckedOutsItemPartsByUser(String userId) {
        return null;
    }
    @Override
    public boolean isPartItemAvailable(int patrimonialId) {
        return false;
    }
    @Override
    public Set<PartItem> findByType(String type) {
        Set<PartItem> foundParts = new HashSet<>();
        String sql = "SELECT * \n" +
                     "FROM part_item pi JOIN part p ON pi.part_id = p.id\n" +
                     "WHERE p.part_type LIKE '%"+type+"%'";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int patrimonial_id = rs.getInt("patrimonial_id");
                StatusPart status = StatusPart.strToEnum(rs.getString("status"));
                String observation = rs.getString("observation");
                int part_id = rs.getInt("part_id");
                String part_type = rs.getString("part_type");
                int max_days_for_student = rs.getInt("max_days_for_student");
                int max_days_for_professor = rs.getInt("max_days_for_professor");
                Part part = new Part(part_id, part_type, max_days_for_student, max_days_for_professor);
                PartItem partItem = new PartItem(patrimonial_id,status,observation, part);
                foundParts.add(partItem);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return foundParts;
    }

    public List<PartItem> findByReservationId(Integer reservationId) {
        List<PartItem> parts = new ArrayList<>();
        String sql = "SELECT * from reservation_item" +
                "where reservation_id = ?;";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int part_item_id = rs.getInt("part_item_id");
                PartItem partItem = findOne(part_item_id).orElseThrow();
                parts.add(partItem);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return parts;
    }
    @Override
    public Integer create(PartItem obj) {
        return null;
    }
    @Override
    public Optional<PartItem> findOne(Integer patrimonialId) {
        String sql = "SELECT * " +
                "FROM (part_item pi JOIN part p ON pi.part_id = p.id)" +
                "WHERE patrimonial_id = ?";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, patrimonialId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                StatusPart status = StatusPart.strToEnum(rs.getString("status"));
                String observation = rs.getString("observation");
                int part_id = rs.getInt("part_id");
                String part_type = rs.getString("part_type");
                int max_days_for_student = rs.getInt("max_days_for_student");
                int max_days_for_professor = rs.getInt("max_days_for_professor");
                Part part = new Part(part_id, part_type, max_days_for_student, max_days_for_professor);
                return Optional.of(new PartItem(patrimonialId,status,observation, part));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public List<PartItem> findAll() {
        return null;
    }
    @Override
    public void update(PartItem partItem) {
        String sql ="UPDATE part_item \n" +
                "SET  status = ?, observation = ?\n" +
                "WHERE patrimonial_id = ?";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1, partItem.getStatus().toString());
            ps.setString(2, partItem.getObservation());
            ps.setInt(3, partItem.getPatrimonialId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean delete(PartItem obj) {
        return false;
    }
}
