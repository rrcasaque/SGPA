package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.user.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteUserDAO implements UserDAO {
    @Override
    public Optional<User> findOneByIdAndType(String type, int institutionalId) {
        String sql = "select * from user where  institutional_id = ? and user_type = ?";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, institutionalId);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int institutional_id = rs.getInt("institutional_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String user_type = rs.getString("user_type");
                int    room = rs.getInt("room");
                String login = rs.getString("login");
                User user = new User(institutional_id, name, email, phone, user_type);
                if (user_type == UserType.TECHNICIAN.toString()){
                    user.setLogin(login);
                } else if (user_type == UserType.PROFESSOR.toString()) {
                    user.setRoom(room);
                }
                return Optional.of(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public Optional<User> findOneByLoginAndPassword(String login, String password) {
        String sql = "select * from user where login = ? and password = ?";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int institutional_id = rs.getInt("institutional_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                User user = new User(institutional_id, name, email, phone, UserType.TECHNICIAN.toString(), login, password);
                return Optional.of(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public Integer create(User user) {
        String sql = "INSERT INTO user(institutional_id, name, email, phone, user_type, room, login, password) \n" +
                "VALUES (?,?,?,?,?,?,?,?);";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1,user.getInstitutionalId());
            ps.setString(2,user.getName());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getPhone());
            ps.setString(5, user.getUserType());
            ps.setInt(6, user.getRoom());
            ps.setString(7, user.getLogin());
            ps.setString(8, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user.getInstitutionalId();
    }

    @Override
    public Optional<User> findOne(Integer institutionalId) {
        String sql = "select * from user where institutional_id = ?";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, institutionalId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int institutional_id = rs.getInt("institutional_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String user_type = rs.getString("user_type");
                User user = new User(institutional_id, name, email, phone, user_type);
                if (UserType.TECHNICIAN.toString().equals(user_type)) {
                    String login = rs.getString("login");
                    user.setLogin(login);
                    String senha = rs.getString("password");
                    user.setPassword(senha);
                } else if (UserType.PROFESSOR.toString().equals(user_type)) {
                    int room = rs.getInt("room");
                    user.setRoom(room);
                }
                return Optional.of(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from user";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userInstitutitionalId = rs.getInt("institutional_id");
                User user = findOne(userInstitutitionalId).orElseThrow();
                users.add(user);
            }
            return users;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public void update(User obj) {

    }
    @Override
    public boolean delete(User obj) {
        return false;
    }
}
