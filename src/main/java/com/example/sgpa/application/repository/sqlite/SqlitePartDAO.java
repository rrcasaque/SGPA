package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.usecases.part.PartDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePartDAO implements PartDAO {
    @Override
    public Optional<Part> findByDescription(String part_type) {
        String sql = "select * from part where part_type = ?;";
        try(PreparedStatement  ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1,part_type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt("id");
                int max_days_for_student = rs.getInt("max_days_for_student");
                int max_days_for_professor = rs.getInt("max_days_for_professor");
                Part part = new Part(id,part_type,max_days_for_student,max_days_for_professor);
                return Optional.of(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer create(Part part) {
        String sql = "insert into part(part_type, max_days_for_student, max_days_for_professor) values(?,?,?);";
        try(PreparedStatement  ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1,part.getType());
            ps.setInt(2,part.getMaxDaysCheckedOutForStudent());
            ps.setInt(3,part.getMaxDaysCheckedOutForProfessor());
            ps.executeUpdate();
            return ps.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Part> findOne(Integer id) {
        String sql = "select * from part where id = ?;";
        try(PreparedStatement  ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String part_type = rs.getString("part_type");
                int max_days_for_student = rs.getInt("max_days_for_student");
                int max_days_for_professor = rs.getInt("max_days_for_professor");
                Part part = new Part(id,part_type,max_days_for_student,max_days_for_professor);
                return Optional.of(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Part> findAll() {
        List<Part> parts = new ArrayList<>();
        String sql = "select * from part;";
        try(PreparedStatement  ps = ConnectionFactory.getPreparedStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String part_type = rs.getString("part_type");
                int max_days_for_student = rs.getInt("max_days_for_student");
                int max_days_for_professor = rs.getInt("max_days_for_professor");
                parts.add(new Part(id,part_type,max_days_for_student,max_days_for_professor));
            }
            return  parts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parts;
    }

    @Override
    public void update(Part obj) {

    }

    @Override
    public boolean delete(Part obj) {
        return false;
    }
}
