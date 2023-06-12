package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.view.WindowLoader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewPartItemUIController {
    @FXML
    private Label lblSelectedUser;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConcludeReservation;

    @FXML
    private TextField txtFindPart1;

    @FXML
    private TextField txtFindPart112;

    @FXML
    private ComboBox<String> comboBoxType;

    @FXML
    private void cancelRegisterPartItem() throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    private void registerPartItem() {
        // Implement your logic for registering the part item here
    }
}
