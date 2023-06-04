package com.example.sgpa.application.controller;

import com.example.sgpa.application.repository.sqlite.*;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.part.StatusPart;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.checkout.CheckOutDAO;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;
import com.example.sgpa.domain.usecases.checkout.CreateCheckOutUseCase;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.part.CheckForPartItemAvailabilityUseCase;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.CheckForUserPendingIssuesUseCase;
import com.example.sgpa.domain.usecases.user.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NewCheckOutUIController {
    private User selectedUser;
    @FXML
    private Button bntFindUser;
    @FXML
    private Button btnAddPartToSelected;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnFinish;
    @FXML
    private Button btnGetPart;
    @FXML
    private Button btnRemovePartFromSelected;
    @FXML
    private Label lblSelectedUser;
    @FXML
    private TableColumn<PartItem, Integer> tcPatrimonialIdFoundPart;
    @FXML
    private TableColumn<PartItem, Integer> tcPatrimonialIdSelectedPart;
    @FXML
    private TableColumn<PartItem, String> tcStatusFoundPart;
    @FXML
    private TableColumn<PartItem, String> tcTypeFoundPart;
    @FXML
    private TableColumn<PartItem, String> tcTypeSelectedPart;
    @FXML
    private TableView<PartItem> tvFoundParts;
    @FXML
    private TableView<PartItem> tvSelectedParts;
    @FXML
    private TextField txtFindPart;
    @FXML
    private TextField txtUserId;
    private final ObservableList<PartItem> searchResult = FXCollections.observableArrayList();
    private final ObservableList<PartItem> selectedParts = FXCollections.observableArrayList();
    private final UserDAO userDAO = new SqliteUserDAO();
    private final PartItemDAO partItemDAO =  new SqlitePartItemDAO();
    private final CheckOutDAO checkOutDAO  = new SqliteCheckOutDAO();
    private final CheckedOutItemDAO checkedOutItemDAO = new SqliteCheckedOutItemDAO();
    private final EventDAO eventDAO = new SqliteEventDAO();
    private final CheckForUserPendingIssuesUseCase checkUserUseCAse =  new CheckForUserPendingIssuesUseCase(checkedOutItemDAO);
    private final CheckForPartItemAvailabilityUseCase checkPartItemsUseCase = new CheckForPartItemAvailabilityUseCase(partItemDAO);

    private final CreateCheckOutUseCase createCheckOutUseCase = new CreateCheckOutUseCase(
            userDAO, partItemDAO, checkOutDAO, checkedOutItemDAO, eventDAO, checkUserUseCAse, checkPartItemsUseCase);
    @FXML
    private void initialize() {
        bindTableViewToItemsLists();
        bindTableColumnsToProperties();
    }
    private void bindTableColumnsToProperties(){
        tcPatrimonialIdFoundPart.setCellValueFactory(new PropertyValueFactory<>("patrimonialId"));
        tcTypeFoundPart.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcStatusFoundPart.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcPatrimonialIdSelectedPart.setCellValueFactory(new PropertyValueFactory<>("patrimonialId"));
        tcTypeSelectedPart .setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    private void bindTableViewToItemsLists(){
        tvFoundParts.setItems(searchResult);
        tvSelectedParts.setItems(selectedParts);
    }
    public void findUser(ActionEvent actionEvent) {
        try{
            selectedUser = userDAO.findOne(Integer.valueOf(txtUserId.getText())).orElseThrow();
            lblSelectedUser.setText("Usuário: "+ selectedUser.getName() + " Tipo: " + selectedUser.getUserType().toString());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User not found.");
            alert.setHeaderText("User not found. Try other id or cancel the checkout");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
    public void findParts(ActionEvent actionEvent) {
        searchResult.clear();
        SqlitePartItemDAO sqlitePartItemDAO = new SqlitePartItemDAO();
        try{
            Set<PartItem> found = sqlitePartItemDAO.findByType(txtFindPart.getText());
            if (found.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Parts not found");
                alert.showAndWait();
            }
            searchResult.addAll(found);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Parts not found.");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void addPart(ActionEvent actionEvent) {
        PartItem selected = tvFoundParts.getSelectionModel().getSelectedItem();
        if(selected != null && !selectedParts.contains(selected) && selected.getStatus() == StatusPart.AVAILABLE){
            selectedParts.add(selected);
            searchResult.remove(selected);
        }
    }
    public void removePart(ActionEvent actionEvent) {
        PartItem selected = tvSelectedParts.getSelectionModel().getSelectedItem();
        if(selected != null){
            selectedParts.remove(selected);
        }
    }
    public void createCheckOut(ActionEvent actionEvent) {
        Set<PartItem> partItemSet = new HashSet<>(tvSelectedParts.getItems());
        if (selectedUser != null && !partItemSet.isEmpty() ) {
            createCheckOutUseCase.createCheckout(selectedUser.getInstitutionalId(), partItemSet);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Empréstimo concluído com sucesso.");
            alert.showAndWait();
            clearFields();
        }
    }

    private void clearFields() {
        searchResult.clear();
        selectedParts.clear();
        txtFindPart.clear();
        txtUserId.clear();
        lblSelectedUser.setText("");
        selectedUser = null;
    }

    public void backToPreviousScene(ActionEvent actionEvent) {
        try {
            WindowLoader.setRoot("MainUI.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
