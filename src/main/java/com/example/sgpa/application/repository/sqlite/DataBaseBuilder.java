package com.example.sgpa.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseBuilder {
    public void buildDataBaseIfMissing(){
        if (Files.notExists(Paths.get("sgpa.db"))){
            buildTables();
        }
    }
    private void buildTables() {
        try (Statement statement = ConnectionFactory.getStatement()){
            statement.addBatch(buildUserTable());
            statement.addBatch(buildPartTable());
            statement.addBatch(buildPartItemTable());
            statement.addBatch(buildCheckOutTable());
            statement.addBatch(buildCheckOutItemTable());
            statement.addBatch(buildReservationTable());
            statement.addBatch(buildReservationItemTable());
            statement.addBatch(buildEventTable());
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String buildEventTable() {
        return null;
    }
    private String buildReservationItemTable() {
        return null;
    }
    private String buildReservationTable() {
        return null;
    }
    private String buildCheckOutItemTable() {
        return null;
    }
    private String buildCheckOutTable() {
        return null;
    }
    private String buildPartItemTable() {
        return null;
    }
    private String buildPartTable() {
        return null;
    }
    private String buildUserTable() {
        return null;
    }
}
