package com.example.sgpa.application.controller;

import com.example.sgpa.application.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class MainUIController {

    @FXML
    private MenuItem menuItemNewCheckOut;

    @FXML
    void goToCheckOutScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("NewCheckOutUI.fxml");
    }

    public void goToReturnScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ReturnUI.fxml");
    }
}
