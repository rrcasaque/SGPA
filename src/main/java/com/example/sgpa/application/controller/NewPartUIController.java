package com.example.sgpa.application.controller;

import java.io.IOException;

import com.example.sgpa.application.repository.sqlite.SqlitePartDAO;
import com.example.sgpa.application.view.WindowLoader;

import com.example.sgpa.domain.usecases.part.CreatePartUseCase;
import com.example.sgpa.domain.usecases.part.PartDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewPartUIController {
    PartDAO partDAO = new SqlitePartDAO();
    CreatePartUseCase createPartUseCase = new CreatePartUseCase(partDAO);
    @FXML
    private Label lblSelectedUser;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConcludeRegistration;

    @FXML
    private TextField txtMaxDaysStudent;

    @FXML
    private TextField txtMaxDaysProfessor;

    @FXML
    private TextField txtPartType;

    @FXML
    private void cancelRegisterPart() throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }

    @FXML
    private void registerPart() {
        String partType = txtPartType.getText();
        try {
            int maxDaysStudent = Integer.parseInt(txtMaxDaysStudent.getText());
            int maxDaysProfessor = Integer.parseInt(txtMaxDaysProfessor.getText());
            createPartUseCase.createPart(partType, maxDaysStudent, maxDaysProfessor);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa:");
            alert.setContentText("Nova peça cadastrada com sucesso.");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa:");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
