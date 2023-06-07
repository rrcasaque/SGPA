package com.example.sgpa.application.controller;


import com.example.sgpa.application.repository.sqlite.*;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.part.CheckForPartItemAvailabilityUseCase;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.reservation.CreateReservationUseCase;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;
import com.example.sgpa.domain.usecases.reservation.ReservedItemDAO;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingIssuesUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class NewReservationUIController {
    @FXML
    private Button btnAddToReservation;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnConcludeReservation;
    @FXML
    private Button btnFindPart;
    @FXML
    private Button btnFindUser;
    @FXML
    private Button btnRemoveFromReservation;
    @FXML
    private Label lblSelectedUser;
    @FXML
    private TableColumn<PartItem, Integer> tcFoundPartId;
    @FXML
    private TableColumn<PartItem, String> tcFoundPartStatus;
    @FXML
    private TableColumn<PartItem, String> tcFoundPartType;
    @FXML
    private TableColumn<PartItem, Integer> tcSelectedPartId;
    @FXML
    private TableColumn<PartItem, String> tcSelectedPartType;
    @FXML
    private TableView<PartItem> tvFoundParts;
    @FXML
    private TableView<PartItem> tvSelectedParts;
    @FXML
    private TextField txtFindPart;
    @FXML
    private TextField txtUserId;
    private final ObservableList<PartItem> foundParts = FXCollections.observableArrayList();
    private final ObservableList<PartItem> selectedParts = FXCollections.observableArrayList();
    private final UserDAO userDAO = new SqliteUserDAO();
    private final PartItemDAO partItemDAO =  new SqlitePartItemDAO();
    private final CheckedOutItemDAO checkedOutItemDAO =  new SqliteCheckedOutItemDAO();
    private final EventDAO eventDAO = new SqliteEventDAO();
    private final ReservationDAO reservationDAO  = new SqliteReservationDAO();
    private final ReservedItemDAO reservedItemDAO  = new SqliteReservedItemDAO();
    private final CheckForUserPendingIssuesUseCase checkUserUseCAse =  new CheckForUserPendingIssuesUseCase(checkedOutItemDAO);
    private final CheckForPartItemAvailabilityUseCase checkPartItemsUseCase = new CheckForPartItemAvailabilityUseCase(partItemDAO);
    private final CreateReservationUseCase createReservationUseCase = new CreateReservationUseCase(
            userDAO, partItemDAO, reservationDAO, eventDAO, checkUserUseCAse, checkPartItemsUseCase, reservedItemDAO);
    @FXML
    private void initialize() {
        bindTableViewToItemsLists();
        bindTableColumnsToProperties();
    }
    private void bindTableColumnsToProperties(){
        tcFoundPartId.setCellValueFactory(new PropertyValueFactory<>("patrimonialId"));
        tcFoundPartType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcFoundPartStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcSelectedPartId.setCellValueFactory(new PropertyValueFactory<>("patrimonialId"));
        tcSelectedPartType .setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    private void bindTableViewToItemsLists(){
        tvFoundParts.setItems(foundParts);
        tvSelectedParts.setItems(selectedParts);
    }
    @FXML
    void addToReservation(ActionEvent event) {
    }
    @FXML
    void cancelReservation(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");

    }
    @FXML
    void concludeReservation(ActionEvent event) {

    }
    @FXML
    void findPartsByType(ActionEvent event) {

    }
    @FXML
    void findUser(ActionEvent event) {

    }
    @FXML
    void removeFromReservation(ActionEvent event) {

    }
}
