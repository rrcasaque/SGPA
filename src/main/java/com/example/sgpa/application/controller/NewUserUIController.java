package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.view.WindowLoader;

import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.checkout.CreateCheckOutUseCase;
import com.example.sgpa.domain.usecases.user.CreateUserUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class NewUserUIController {

    ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TextField txtProntuario;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConcludeRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSala;

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
    private void initialize() {
        radioButtonTecnico.setToggleGroup(toggleGroup);
        radioButtonEstudante.setToggleGroup(toggleGroup);
        radioButtonProfessor.setToggleGroup(toggleGroup);

    }

    @FXML
    private void registerUser() {
        if (toggleGroup.getSelectedToggle() == radioButtonProfessor) {

        } else if (toggleGroup.getSelectedToggle() == radioButtonEstudante) {
            try{
                User newUser = new User(Integer.parseInt(txtProntuario.getText()), txtNome.getText(),
                        txtEmail.getText(), txtTelefone.getText(), UserType.Student.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("SGPA informa");
                alert.setContentText("Estudante registrado com sucesso.");
                alert.showAndWait();
                clearFields();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("SGPA informa");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else if (toggleGroup.getSelectedToggle() == radioButtonTecnico) {

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa");
            alert.setContentText("Selecione um tipo de usuário.");
            alert.showAndWait();
        }
    }

    private void clearFields() {
        txtProntuario.clear();
        txtNome.clear();
        txtLogin.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtSala.clear();
    }
    // Outros métodos e lógica adicionais

}
