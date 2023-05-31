package com.example.sgpa.application.controller;

import com.example.sgpa.application.repository.sqlite.SqliteUserDAO;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.user.UserDAO;
import com.example.sgpa.domain.usecases.utils.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.NoSuchElementException;

public class NewCheckOutUIController {
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
        UserDAO userDAO = new SqliteUserDAO();
        try{
            User user = userDAO.findOne(Integer.valueOf(txtUserId.getText())).orElseThrow();
            lblSelectedUser.setText(user.getName());
        }catch(NoSuchElementException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User not found.");
            alert.setHeaderText("User not found. Try other id or cancel the checkout");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void findParts(ActionEvent actionEvent) {
    }

    public void addPart(ActionEvent actionEvent) {
    }

    public void removePart(ActionEvent actionEvent) {
    }

    public void createCheckOut(ActionEvent actionEvent) {
    }

    public void backToPreviousScene(ActionEvent actionEvent) {
    }
}
