package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.view.WindowLoader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewPartUIController {
    @FXML
    private TextField txtUserId;

    @FXML
    private Label lblSelectedUser;

    @FXML
    private TextField txtFindPart;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConcludeReservation;

    @FXML
    private TextField txtFindPart1;

    @FXML
    private TextField txtFindPart111;

    @FXML
    private TextField txtFindPart112;

    @FXML
    private void cancelRegisterPart() throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    private void registerPart() {
        // Implement your logic for registering the part here
    }
}
