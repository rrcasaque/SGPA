package com.example.sgpa.application.repository.sqlite;

import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.user.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SqliteUserDAO implements UserDAO {
    @Override
    public Optional<User> findOneByIdAndType(UserType type, int institutionalId) {
        String sql = "select * from user where  institutional_id = ? and user_type = ?";
        try(PreparedStatement ps = ConnectionFactory.getPreparedStatement(sql)){
            ps.setInt(1, institutionalId);
            ps.setString(2, type.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int institutional_id = rs.getInt("institutional_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                UserType user_type = UserType.valueOf(rs.getString("user_type"));
                int    room = rs.getInt("room");
                String login = rs.getString("login");
                User user = new User(institutional_id, name, email, phone, user_type);
                if (user_type == UserType.TECHNICIAN){
                    user.setLogin(login);
                } else if (user_type == UserType.PROFESSOR) {
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
                User user = new User(institutional_id, name, email, phone, UserType.TECHNICIAN, login);
                return Optional.of(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer create(User obj) {
        return null;
    }

    @Override
    public Optional<User> findOne(Integer type) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public boolean delete(User obj) {
        return false;
    }
}
