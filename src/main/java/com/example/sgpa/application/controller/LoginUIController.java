package com.example.sgpa.application.controller;

import com.example.sgpa.application.repository.sqlite.SqliteUserDAO;
import com.example.sgpa.domain.usecases.auth.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginUIController {
    @FXML
    private Button btnEnter;

    @FXML
    private PasswordField pswdInput;

    @FXML
    private TextField txtLogin;

    @FXML
    void authenticate(ActionEvent event) {
        Auth auth = new Auth(new SqliteUserDAO());
        try {
            auth.authenticate(txtLogin.getText(), pswdInput.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage());
            alert.showAndWait();
        }
    }
}
