package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.repository.sqlite.SqliteUserDAO;
import com.example.sgpa.application.view.WindowLoader;


import com.example.sgpa.domain.usecases.user.CreateUserUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.SGPAlerts;
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
    private PasswordField txtSenha;

    @FXML
    private TextField txtSala;

    @FXML
    private RadioButton radioButtonEstudante;

    @FXML
    private RadioButton radioButtonTecnico;

    @FXML
    private RadioButton radioButtonProfessor;

    private final UserDAO userDAO  = new SqliteUserDAO();

    private CreateUserUseCase createUserUseCase;

    private final SGPAlerts sgpAlerts = new SGPAlerts();

    @FXML
    void cancelRegister(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    private void initialize() {
        this.createUserUseCase = new CreateUserUseCase(userDAO);
        radioButtonTecnico.setToggleGroup(toggleGroup);
        radioButtonEstudante.setToggleGroup(toggleGroup);
        radioButtonProfessor.setToggleGroup(toggleGroup);
    }

    @FXML
    private void registerUser() {
        if (toggleGroup.getSelectedToggle() == radioButtonProfessor) {

            try{
                createUserUseCase.createProfessor(Integer.parseInt(txtProntuario.getText()), txtNome.getText(),
                        txtEmail.getText(), txtTelefone.getText(), Integer.parseInt(txtSala.getText()));
                sgpAlerts.showSuccessAlert("Professor registrado com sucesso.");
                clearFields();
            } catch (Exception e) {
                sgpAlerts.showErrorAlert(e);
            }

        } else if (toggleGroup.getSelectedToggle() == radioButtonEstudante) {

            try{
                createUserUseCase.createStudent(Integer.parseInt(txtProntuario.getText()), txtNome.getText(),
                        txtEmail.getText(), txtTelefone.getText());
                sgpAlerts.showSuccessAlert("Estudante registrado com sucesso.");
                clearFields();
            } catch (Exception e) {
                sgpAlerts.showErrorAlert(e);
            }

        } else if (toggleGroup.getSelectedToggle() == radioButtonTecnico) {

            try{
                createUserUseCase.createTechnician(Integer.parseInt(txtProntuario.getText()), txtNome.getText(),
                        txtEmail.getText(), txtTelefone.getText(), txtLogin.getText(), txtSenha.getText());
                sgpAlerts.showSuccessAlert("Técnico registrado com sucesso.");
                clearFields();
            } catch (Exception e) {
                sgpAlerts.showErrorAlert(e);
            }

        } else {
            sgpAlerts.showWarningAlert("Selecione um tipo de usuário.");
        }
    }

    private void clearFields() {
        txtProntuario.clear();
        txtNome.clear();
        txtLogin.clear();
        txtSenha.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtSala.clear();
    }


}
