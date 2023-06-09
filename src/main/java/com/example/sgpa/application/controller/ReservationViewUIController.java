package com.example.sgpa.application.controller;

import com.example.sgpa.application.repository.sqlite.*;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.reservation.Reservation;
import com.example.sgpa.domain.usecases.checkout.CheckOutDAO;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;
import com.example.sgpa.domain.usecases.checkout.CreateCheckOutUseCase;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.part.CheckForPartItemAvailabilityUseCase;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.reservation.ReservationDAO;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingIssuesUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ReservationViewUIController {
    @FXML private Button btnBack;
    @FXML private Button btnSee;
    @FXML private TableView<Reservation> tvReservations;
    @FXML private TableColumn<Reservation, String> tcCheckOutDate;
    @FXML private TableColumn<Reservation, Integer> tcReservationId;
    @FXML private TableColumn<Reservation, Integer> tcUserId;
    @FXML private TableColumn<Reservation, Integer> tcReservationStatus;
    @FXML private TableView<PartItem> tvParts;
    @FXML private TableColumn<PartItem, Integer> tcPatrimonialId;
    @FXML private TableColumn<PartItem, String> tcStatus;
    @FXML private TableColumn<PartItem, String> tcType;
    private final ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();
    private final ObservableList<PartItem> reservedItemsList = FXCollections.observableArrayList();
    private final UserDAO userDAO = new SqliteUserDAO();
    private final PartItemDAO partItemDAO =  new SqlitePartItemDAO();
    private final ReservationDAO reservationDAO = new SqliteReservationDAO();
    private final CheckOutDAO checkOutDAO = new SqliteCheckOutDAO();
    private final CheckedOutItemDAO checkedOutItemDAO = new SqliteCheckedOutItemDAO();
    private final CheckForUserPendingIssuesUseCase checkUserUseCAse =  new CheckForUserPendingIssuesUseCase(checkedOutItemDAO);
    private final CheckForPartItemAvailabilityUseCase checkPartItemsUseCase = new CheckForPartItemAvailabilityUseCase(partItemDAO);
    private final EventDAO eventDAO = new SqliteEventDAO();
    private final CreateCheckOutUseCase createCheckOutUseCase = new CreateCheckOutUseCase(
            userDAO, partItemDAO, checkOutDAO, checkedOutItemDAO, eventDAO, checkUserUseCAse, checkPartItemsUseCase);


    @FXML
    private void initialize() {
        bindTableViewsToItemsLists();
        bindTableColumnsToProperties();
        loadReservationsList();
    }
    private void loadReservationsList() {
        reservationsList.clear();
        reservationsList.addAll(reservationDAO.findAll());
    }
    private void bindTableColumnsToProperties(){
        tcUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        tcCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("scheduledCheckOutDate"));
        tcReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        tcReservationStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tcPatrimonialId.setCellValueFactory(new PropertyValueFactory<>("patrimonialId"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void bindTableViewsToItemsLists() {
        tvParts.setItems(reservedItemsList);
        tvReservations.setItems(reservationsList);
    }
    @FXML void backToPreviousScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }
    @FXML void seeReservedItems(ActionEvent event) {
        Reservation reservation = tvReservations.getSelectionModel().getSelectedItem();
        if (reservation != null){
            reservedItemsList.clear();
            reservedItemsList.addAll(reservation.getItems());
        }
    }

    public void generateCheckout(ActionEvent actionEvent) {
        Reservation selectedReservation = tvReservations.getSelectionModel().getSelectedItem();
        try {
            if (selectedReservation == null || selectedReservation.isExpired())
                throw new RuntimeException("No reservation Selected");
            createCheckOutUseCase.createCheckout(selectedReservation);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SGPA informa");
            alert.setContentText("Empréstimo registrado com sucesso.");
            alert.showAndWait();
            loadReservationsList();
            reservedItemsList.clear();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error generating checkout.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
