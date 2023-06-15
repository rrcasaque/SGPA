module SGPA{
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires itextpdf;
    opens com.example.sgpa.application.view to javafx.fxml;
    opens com.example.sgpa.application.controller to javafx.fxml;
    opens com.example.sgpa.domain.entities.part to javafx.base;
    opens com.example.sgpa.domain.entities.checkout to javafx.base;
    opens com.example.sgpa.domain.entities.reservation to javafx.base;
    opens com.example.sgpa.domain.entities.historical to javafx.base;
    opens com.example.sgpa.domain.entities.user to javafx.base;
    exports com.example.sgpa.application.view;
    exports com.example.sgpa.application.controller;
}