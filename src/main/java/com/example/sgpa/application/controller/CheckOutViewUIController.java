package com.example.sgpa.application.controller;

import com.example.sgpa.application.repository.sqlite.*;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.usecases.checkout.CheckOutDAO;
import com.example.sgpa.domain.usecases.checkout.CheckedOutItemDAO;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.user.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

public class CheckOutViewUIController {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSee;
    @FXML
    private TableView<Checkout> tvCheckouts;
    @FXML
    private TableColumn<Checkout, String> tcCheckOutDate;
    @FXML
    private TableColumn<Checkout, Integer> tcCheckoutId;
    @FXML
    private TableColumn<Checkout, Integer> tcUserId;
    @FXML
    private TableView<CheckedOutItem> tvParts;
    @FXML
    private TableColumn<CheckedOutItem, Integer> tcPatrimonialId;
    @FXML
    private TableColumn<CheckedOutItem, String> tcStatus;
    @FXML
    private TableColumn<CheckedOutItem, String> tcType;
    @FXML
    private TableColumn<CheckedOutItem, String> tcDueDate;
    private final ObservableList<Checkout> checkOutList = FXCollections.observableArrayList();
    private final ObservableList<CheckedOutItem> checkedOutItemsList = FXCollections.observableArrayList();
    private final UserDAO userDAO = new SqliteUserDAO();
    private final PartItemDAO partItemDAO =  new SqlitePartItemDAO();
    private final CheckOutDAO checkOutDAO  = new SqliteCheckOutDAO();
    private final CheckedOutItemDAO checkedOutItemDAO = new SqliteCheckedOutItemDAO();
    private final EventDAO eventDAO = new SqliteEventDAO();
    @FXML
    private void initialize() {
        bindTableViewsToItemsLists();
        bindTableColumnsToProperties();
        loadCheckOutsList();
    }
    private void loadCheckOutsList() {
        checkOutList.clear();
        checkOutList.addAll(checkOutDAO.findAll());
    }
    private void bindTableColumnsToProperties(){
        tcUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        tcCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        tcCheckoutId.setCellValueFactory(new PropertyValueFactory<>("checkOutId"));

        tcPatrimonialId.setCellValueFactory(new PropertyValueFactory<>("patrimonialId"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }
    private void bindTableViewsToItemsLists(){
        tvParts.setItems(checkedOutItemsList);
        tvCheckouts.setItems(checkOutList);
    }
    @FXML
    void backToPreviousScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }
    @FXML
    void seeCheckOutItems(ActionEvent event) {
        Checkout checkout = tvCheckouts.getSelectionModel().getSelectedItem();
        if (checkout != null){
            checkedOutItemsList.clear();
            checkedOutItemsList.addAll(checkout.getCheckedOutItems());
        }
    }
}
