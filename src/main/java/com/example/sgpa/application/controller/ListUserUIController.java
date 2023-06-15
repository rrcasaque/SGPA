package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.repository.sqlite.SqliteUserDAO;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.user.User;

import com.example.sgpa.domain.entities.user.UserType;
import com.example.sgpa.domain.usecases.user.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<User, Integer> salaColumn;

    @FXML
    private TableColumn<User, Integer> prontuarioColumn;

    private final ObservableList<User> usersList = FXCollections.observableArrayList();

    private final UserDAO userDAO = new SqliteUserDAO();

    @FXML
    private void initialize() {
        bindTableViewsToItemsLists();
        bindTableColumnsToProperties();
        loadReservationsList();
    }
    private void loadReservationsList() {
        usersList.clear();
        usersList.addAll(userDAO.findAll());
    }
    private void bindTableColumnsToProperties(){
        prontuarioColumn.setCellValueFactory(new PropertyValueFactory<>("institutionalId"));
        salaColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        tipoUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    private void bindTableViewsToItemsLists() {
        tableView.setItems(usersList);
    }
    @FXML void backToPreviousScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    private void cancelList() throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }
}
