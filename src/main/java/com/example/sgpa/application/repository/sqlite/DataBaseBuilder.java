package com.example.sgpa.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseBuilder {
    public void buildDataBaseIfMissing(){
        if (Files.notExists(Paths.get("sgpa.db"))){
            buildTables();
            populateTables();
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

    private void populateTables() {
        try (Statement statement = ConnectionFactory.getStatement()){
            statement.addBatch(populateUserTable());
            statement.addBatch(populatePartTable());
            statement.addBatch(populatePartItemTable());
            statement.executeBatch();
            System.out.println("SGPA data base populate successfully!\nYou can consult the generated tuples at DB Browser app.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String buildEventTable() {
        String sql =
        "CREATE TABLE event(\n"+
        "        id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        event_type TEXT NOT NULL,\n"+
        "        time_stamp TEXT NOT NULL,\n"+
        "        part_item_id INTEGER NOT NULL,\n"+
        "        user_id INTEGER NOT NULL,\n"+
        "        technician_id INTEGER NOT NULL,\n"+
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
        "        date_time_scheduled_for_checkout TEXT NOT NULL,\n"+
        "        user_id INTEGER NOT NULL,\n"+
        "        technician_id INTEGER NOT NULL,\n"+
        "        status TEXT NOT NULL,\n"+
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
        "        due_date TEXT NOT NULL,\n"+
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
        "        technician_id INTEGER NOT NULL,\n"+
        "        user_id INTEGER NOT NULL,\n"+
        "        checkout_date TEXT NOT NULL,\n"+
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
        "        status TEXT NOT NULL,\n"+
        "        observation TEXT,\n"+
        "        part_id INTEGER NOT NULL,\n"+
        "        FOREIGN KEY (part_id) REFERENCES part(id)\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildPartTable() {
        String sql =
        "CREATE TABLE part(\n"+
        "        id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        part_type TEXT NOT NULL,\n"+
        "        max_days_for_student INTEGER NOT NULL,\n"+
        "        max_days_for_professor INTEGER NOT NULL\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }
    private String buildUserTable() {
        String sql =
        "CREATE TABLE user (\n"+
        "        institutional_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
        "        name TEXT NOT NULL,\n"+
        "        email TEXT,\n"+
        "        phone TEXT,\n"+
        "        user_type TEXT NOT NULL,\n"+
        "        room INTEGER,\n"+
        "        login TEXT,\n"+
        "        password TEXT\n"+
        ");\n";
        System.out.println(sql);
        return sql;
    }

    private String populatePartTable(){
        String sql = "INSERT INTO part(part_type, max_days_for_student, max_days_for_professor)\n" +
                "VALUES ('valvula', 5, 15), ('helice', 15, 25), ('pastilha', 7, 7);";
        System.out.println(sql);
        return sql;
    }

    private String populatePartItemTable(){
        String sql = "INSERT INTO part_item(status, part_id)\n" +
                "VALUES ('Disponível', 1), ('Disponível', 1), ('Disponível', 1), ('Disponível', 1),\n" +
                "       ('Disponível', 2), ('Disponível', 2), ('Disponível', 2), ('Disponível', 2),\n" +
                "       ('Disponível', 3), ('Disponível', 3), ('Disponível', 3), ('Disponível', 3);";
        System.out.println(sql);
        return sql;
    }

    private String populateUserTable(){
       String sql = "insert into user(name, email, phone, user_type, login, password) \n" +
               "values ('Mário', 'mario@email.com','16887766776', 'Técnico', 'mprado', 'm1234'),\n" +
               "       ('Paula', 'paula@email.com','16887766776', 'Professor', NULL , NULL),\n" +
               "       ('Carmen', 'carmen@email.com','16887766776', 'Estudante', NULL, NULL);";
        System.out.println(sql);
        return sql;
    }
}
