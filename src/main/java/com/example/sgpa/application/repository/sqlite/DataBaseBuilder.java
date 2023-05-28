package com.example.sgpa.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseBuilder {
    public void buildDataBaseIfMissing(){
        if (Files.notExists(Paths.get("sgpa.db"))){
            buildTables();
        }else {
            System.out.println("SGPA data base already exists!");
        }
    }
    private void buildTables() {
        try (Statement statement = ConnectionFactory.getStatement()){
            statement.addBatch(buildUserTable());
            statement.addBatch(buildPartTable());
            statement.addBatch(buildPartItemTable());
            statement.addBatch(buildReservationTable());
            statement.addBatch(buildReservationItemTable());
            statement.addBatch(buildCheckOutTable());
            statement.addBatch(buildCheckOutItemTable());
            statement.addBatch(buildEventTable());
            statement.executeBatch();
            System.out.println("SGPA data base built successfully!\nYou can consult the generated tables at DB Browser app.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String buildEventTable() {
        String sql =
        "CREATE TABLE event(\n"+
        "        id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        event_type TEXT,\n"+
        "        time_stamp TEXT,\n"+
        "        part_item_id INTEGER,\n"+
        "        user_id INTEGER,\n"+
        "        technician_id INTEGER,\n"+
        "        FOREIGN KEY(part_item_id) REFERENCES part_item(patrimonial_id),\n"+
        "        FOREIGN KEY(user_id) REFERENCES user(institutional_id),\n"+
        "        FOREIGN KEY(technician_id) REFERENCES user(institutional_id)\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildReservationItemTable() {
        String sql =
        "CREATE TABLE reservation_item(\n"+
        "        reservation_id INTEGER,\n"+
        "        part_item_id INTEGER,\n"+
        "        FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id),\n"+
        "        FOREIGN KEY (part_item_id) REFERENCES part_item(patrimonial_id),\n"+
        "        PRIMARY KEY (reservation_id, part_item_id)\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildReservationTable() {
        String sql =
        "CREATE TABLE reservation(\n"+
        "        reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        date_time_scheduled_for_checkout TEXT,\n"+
        "        user_id INTEGER,\n"+
        "        technician_id INTEGER,\n"+
        "        FOREIGN KEY (user_id) REFERENCES user(institutional_id),\n"+
        "        FOREIGN KEY (technician_id) REFERENCES user(institutional_id)\n"+
        ");";
        System.out.println(sql);
        return sql;
    }
    private String buildCheckOutItemTable() {
        String sql =
        "CREATE TABLE checkout_item(\n"+
        "        checkout_id INTEGER,\n"+
        "        part_item_id INTEGER,\n"+
        "        due_date TEXT,\n"+
        "        return_date TEXT,\n"+
        "        FOREIGN KEY (checkout_id) REFERENCES checkout(checkout_id),\n"+
        "        FOREIGN KEY (part_item_id) REFERENCES part_item(patrimonial_id),\n"+
        "        PRIMARY KEY (checkout_id, part_item_id)\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildCheckOutTable() {
        String sql =
        "CREATE TABLE checkout(\n"+
        "        checkout_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        technician_id INTEGER,\n"+
        "        user_id INTEGER,\n"+
        "        reservation_id INTEGER,\n"+
        "        FOREIGN KEY (technician_id) REFERENCES user(institutional_id),\n"+
        "        FOREIGN KEY (user_id) REFERENCES user(institutional_id),\n"+
        "        FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildPartItemTable() {
        String sql =
        "CREATE TABLE part_item(\n"+
        "        patrimonial_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        status TEXT,\n"+
        "        observation TEXT,\n"+
        "        part_id INTEGER,\n"+
        "        FOREIGN KEY (part_id) REFERENCES part(id)\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildPartTable() {
        String sql =
        "CREATE TABLE part(\n"+
        "        id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        description  TEXT,\n"+
        "        max_days_for_student INTEGER,\n"+
        "        max_days_for_professor INTEGER\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildUserTable() {
        String sql =
        "CREATE TABLE user (\n"+
        "        institutional_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        name TEXT,\n"+
        "        email TEXT,\n"+
        "        phone TEXT,\n"+
        "        user_type TEXT,\n"+
        "        room INTEGER,\n"+
        "        login TEXT,\n"+
        "        password TEXT\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
}
