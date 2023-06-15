package com.example.sgpa.domain.usecases.utils;

import javafx.scene.control.Alert;

public class SGPAlerts {
    public void showErrorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("SGPA informa");
        alert.setContentText(e.getLocalizedMessage());
        alert.showAndWait();
    }

    public void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("SGPA informa");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("SGPA informa");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
