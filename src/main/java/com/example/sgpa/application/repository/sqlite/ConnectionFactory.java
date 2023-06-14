package com.example.sgpa.application.repository.sqlite;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class ConnectionFactory implements AutoCloseable{
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static Connection getConnection() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection("jdbc:sqlite:sgpa.db");
        }
        return connection;
    }
    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        preparedStatement = getConnection().prepareStatement(sql);
        return preparedStatement;
    }
    public static Statement getStatement() throws SQLException {
        statement = getConnection().createStatement();
        return statement;
    }
    @Override
    public void close() throws Exception {
        if (preparedStatement != null)
            preparedStatement.close();
        if (statement != null)
            statement.close();
        if (connection != null)
            connection.close();
    }
}
