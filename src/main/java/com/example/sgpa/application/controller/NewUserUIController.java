package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.view.WindowLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NewUserUIController {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtFindPart;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConcludeReservation;

    @FXML
    private TextField txtFindPart1;

    @FXML
    private TextField txtFindPart11;

    @FXML
    private TextField txtFindPart12;

    @FXML
    private TextField txtFindPart111;

    @FXML
    private TextField txtFindPart112;

    @FXML
    private RadioButton radioButtonEstudante;

    @FXML
    private RadioButton radioButtonTecnico;

    @FXML
    private RadioButton radioButtonProfessor;

    @FXML
    void cancelRegister(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    private void registerUser() {
        // Lógica para concluir a reserva
    }

    // Outros métodos e lógica adicionais

}
