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
    void goToListUserScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("ListUserUI.fxml");
    }
    @FXML
    void goToEditUserScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("EditUserUI.fxml");
    }
    @FXML
    void goToNewUserScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("NewUserUI.fxml");
    }
    @FXML
    void goToCheckOutScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("NewCheckOutUI.fxml");
    }
    @FXML
    public void goToReturnScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ReturnUI.fxml");
    }
    @FXML
    public void goToCheckOutListViewScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CheckOutViewUI.fxml");
    }
    @FXML
    public void goToNewReservationScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("NewReservationUI.fxml");
    }
    @FXML
    public void ggoToReservationsListViewScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("NewReservationUI.fxml");
    }
    @FXML
    public void goToReservationsListViewScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ReservationViewUI.fxml");
    }
    @FXML
    public void goToReportByPartScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ReportUI.fxml");
        ReportUIController controller = (ReportUIController) WindowLoader.getController();
        controller.configMode(ReportUIMode.BY_PART);
    }
    @FXML
    public void goToReportByUserScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ReportUI.fxml");
        ReportUIController controller = (ReportUIController) WindowLoader.getController();
        controller.configMode(ReportUIMode.BY_USER);
    }
    @FXML
    public void goToGeneralReportScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ReportUI.fxml");
        ReportUIController controller = (ReportUIController) WindowLoader.getController();
        controller.configMode(ReportUIMode.GENERAL);
    }
}
