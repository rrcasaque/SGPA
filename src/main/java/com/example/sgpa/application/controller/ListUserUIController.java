package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.user.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListUserUIController {
    @FXML
    private Label lblSelectedUser;

    @FXML
    private Button btnCancel;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> nomeColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> telefoneColumn;

    @FXML
    private TableColumn<User, String> tipoUsuarioColumn;

    @FXML
    private TableColumn<User, String> salaColumn;

    @FXML
    private TableColumn<User, String> prontuarioColumn;

    public void initialize() {        
                
    }

    @FXML
    private void cancelList() throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }
}
