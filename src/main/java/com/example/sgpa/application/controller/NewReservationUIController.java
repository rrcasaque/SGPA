package com.example.sgpa.application.controller;


import com.example.sgpa.application.repository.sqlite.*;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.user.User;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @FXML
    private DatePicker dpCheckoutScheduledDate;
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
    private User selectedUser;

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
        PartItem selected = tvFoundParts.getSelectionModel().getSelectedItem();
        if(selected != null && !selectedParts.contains(selected) && selected.getStatus() == StatusPart.AVAILABLE){
            selectedParts.add(selected);
            foundParts.remove(selected);
        }
    }
    @FXML
    void removeFromReservation(ActionEvent event) {
        PartItem selected = tvSelectedParts.getSelectionModel().getSelectedItem();
        if(selected != null){
            selectedParts.remove(selected);
        }
    }
    @FXML
    void cancelReservation(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }
    @FXML
    void findUser(ActionEvent event) {
        try{
            selectedUser = userDAO.findOne(Integer.valueOf(txtUserId.getText())).orElseThrow();
            lblSelectedUser.setText("Usuário(a) selecionado(a): "+ selectedUser.getName() + "  |  Tipo: " + selectedUser.getUserType().toString());
            lblSelectedUser.setVisible(true);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error loading user.");
            alert.setHeaderText("SGPA informa");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void findPartsByType(ActionEvent event) {
        foundParts.clear();
        SqlitePartItemDAO sqlitePartItemDAO = new SqlitePartItemDAO();
        try{
            Set<PartItem> found = sqlitePartItemDAO.findByType(txtFindPart.getText());
            if (found.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("SGPA informa");
                alert.setContentText("Parts not found");
                alert.showAndWait();
            }
            foundParts.addAll(found);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error loading parts.");
            alert.setHeaderText("SGPA informa");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearFields() {
        foundParts.clear();
        selectedParts.clear();
        txtFindPart.clear();
        txtUserId.clear();
        lblSelectedUser.setText("");
        selectedUser = null;
    }

    @FXML
    void concludeReservation(ActionEvent event) {
        Set<PartItem> partItemSet = new HashSet<>(tvSelectedParts.getItems());
        LocalDate scheduledCheckoutDate = dpCheckoutScheduledDate.getValue();
        try{
            if (selectedUser == null || partItemSet.isEmpty() || scheduledCheckoutDate == null)
                throw new RuntimeException("User, checkout date and reserved items must be informed.");
            createReservationUseCase.createReservation(selectedUser.getInstitutionalId(), partItemSet, scheduledCheckoutDate);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa");
            alert.setContentText("Reserva registrada com sucesso.");
            alert.showAndWait();
            clearFields();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
