module com.example.sgpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires rt;


    opens com.example.sgpa to javafx.fxml;
    exports com.example.sgpa;
}