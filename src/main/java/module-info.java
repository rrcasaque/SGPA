module com.example.sgpa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sgpa to javafx.fxml;
    exports com.example.sgpa;
}