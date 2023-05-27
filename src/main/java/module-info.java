module SGPA{
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    opens com.example.sgpa.application.view to javafx.fxml;
    opens com.example.sgpa.application.controller to javafx.fxml;
    exports com.example.sgpa.application.view;
    exports com.example.sgpa.application.controller;
}