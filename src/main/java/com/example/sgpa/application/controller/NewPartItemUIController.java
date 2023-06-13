package com.example.sgpa.application.controller;
import com.example.sgpa.application.repository.sqlite.SqlitePartDAO;
import com.example.sgpa.application.repository.sqlite.SqlitePartItemDAO;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.usecases.part.CreatePartItemUseCase;
import com.example.sgpa.domain.usecases.part.PartDAO;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewPartItemUIController {
    PartDAO partDAO = new SqlitePartDAO();
    PartItemDAO partItemDAO = new SqlitePartItemDAO();
    CreatePartItemUseCase createPartItemUseCase = new CreatePartItemUseCase(partItemDAO, partDAO);
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConclude;

    @FXML
    private ComboBox<Part> cbPartType;

    @FXML
    private Label lblSelectedUser;

    @FXML
    private TextField txtObs;

    @FXML
    private void initialize() {
        loadComboBox();
    }

    private void loadComboBox(){
        List<Part> parts = partDAO .findAll();
        cbPartType.setItems(FXCollections.observableList(parts));
    }

    @FXML
    void backToPreviousScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    void registerPartItem(ActionEvent event) {
        Part selectedItem = cbPartType.getSelectionModel().getSelectedItem();
        String obs = txtObs.getText();
        try{
            int generatedId = createPartItemUseCase.createPartItem(selectedItem, obs).getPatrimonialId();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa:");
            alert.setContentText("Item with id "+generatedId+" successfully created.");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa:");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}
