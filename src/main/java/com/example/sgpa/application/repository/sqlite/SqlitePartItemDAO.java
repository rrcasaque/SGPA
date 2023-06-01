package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.part.PartItemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
                     "WHERE p.description LIKE '%"+type+"%'";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
//            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int patrimonial_id = rs.getInt("patrimonial_id");
                StatusPart status = StatusPart.strToEnum(rs.getString("status"));
                String observation = rs.getString("observation");
                int part_id = rs.getInt("part_id");
                String description = rs.getString("description");
                int max_days_for_student = rs.getInt("max_days_for_student");
                int max_days_for_professor = rs.getInt("max_days_for_professor");
                Part part = new Part(part_id, description, max_days_for_student, max_days_for_professor);
                PartItem partItem = new PartItem(patrimonial_id,status,observation, part);
                foundParts.add(partItem);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return foundParts;
    }

    @Override
    public Integer create(PartItem obj) {
        return null;
    }

    @Override
    public Optional<PartItem> findOne(Integer type) {
        return Optional.empty();
    }

    @Override
    public List<PartItem> findAll() {
        return null;
    }

    @Override
    public void update(PartItem obj) {

    }

    @Override
    public boolean delete(PartItem obj) {
        return false;
    }
}
