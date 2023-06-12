package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.view.WindowLoader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListPartUIController {
    @FXML
    private Label lblSelectedUser;

    @FXML
    private Button btnCancel;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnMaxTimeStudent;

    @FXML
    private TableColumn<?, ?> columnMaxTimeProfessor;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private TableColumn<?, ?> columnProntuario;

    @FXML
    private void cancelList() throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }
}
