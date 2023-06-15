package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.repository.sqlite.SqliteUserDAO;
import com.example.sgpa.application.view.WindowLoader;

import com.example.sgpa.domain.usecases.user.CreateUserUseCase;
import com.example.sgpa.domain.usecases.user.UpdateUserUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.SGPAlerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class EditUserUIController {

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
    private Button btnEditUser;

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

    private final UserDAO userDAO  = new SqliteUserDAO();
    
    private UpdateUserUseCase updateUserUseCase;
    private SGPAlerts sgpAlerts;

    @FXML
    private void initialize() {
        this.updateUserUseCase = new UpdateUserUseCase(userDAO);
        this.sgpAlerts = new SGPAlerts();
        radioButtonTecnico.setToggleGroup(toggleGroup);
        radioButtonEstudante.setToggleGroup(toggleGroup);
        radioButtonProfessor.setToggleGroup(toggleGroup);
    }
    
    @FXML
    private void cancelEdit() throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    private void editUser() {
        if (toggleGroup.getSelectedToggle() == radioButtonProfessor) {

            try{
                updateUserUseCase.updateProfessor(Integer.parseInt(txtProntuario.getText()), txtNome.getText(),
                        txtEmail.getText(), txtTelefone.getText(), Integer.parseInt(txtSala.getText()));
                sgpAlerts.showSuccessAlert("Professor editado com sucesso.");
                clearFields();
            } catch (Exception e) {
                sgpAlerts.showErrorAlert(e);
            }

        } else if (toggleGroup.getSelectedToggle() == radioButtonEstudante) {

            try{
                updateUserUseCase.updateStudent(Integer.parseInt(txtProntuario.getText()), txtNome.getText(),
                        txtEmail.getText(), txtTelefone.getText());
                sgpAlerts.showSuccessAlert("Estudante editado com sucesso.");
                clearFields();
            } catch (Exception e) {
                sgpAlerts.showErrorAlert(e);
            }

        } else if (toggleGroup.getSelectedToggle() == radioButtonTecnico) {

            try{
                updateUserUseCase.updateTechnician(Integer.parseInt(txtProntuario.getText()), txtNome.getText(),
                        txtEmail.getText(), txtTelefone.getText(), txtLogin.getText(), txtSenha.getText());
                sgpAlerts.showSuccessAlert("Técnico editado com sucesso.");
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
        txtTelefone.clear();
        txtEmail.clear();
        txtSala.clear();
        txtSenha.clear();
    }

}
