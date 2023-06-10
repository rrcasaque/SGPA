package com.example.sgpa.application.controller;


import com.example.sgpa.application.repository.sqlite.SqliteEventDAO;
import com.example.sgpa.application.repository.sqlite.SqlitePartItemDAO;
import com.example.sgpa.application.view.WindowLoader;
import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.usecases.historical.EventDAO;
import com.example.sgpa.domain.usecases.part.PartItemDAO;
import com.example.sgpa.domain.usecases.report.ExportReportUseCase;
import com.example.sgpa.domain.usecases.report.GenerateReportByPartUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportUIController {
    ReportUIMode mode;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnExportPDF;
    @FXML
    private Button btnGenerateReport;
    @FXML
    private DatePicker dpEnd;
    @FXML
    private DatePicker dpStart;
    @FXML
    private Label lbUserOrPartId;
    @FXML
    private TableColumn<Event, String> tcEventType;
    @FXML
    private TableColumn<Event, String> tcPartType;
    @FXML
    private TableColumn<Event, Integer> tcPatrimonialId;
    @FXML
    private TableColumn<Event, String> tcTechnician;
    @FXML
    private TableColumn<Event, String> tcTimeStamp;
    @FXML
    private TableColumn<Event, String> tcUser;
    @FXML
    private TableView<Event> tvEvents;
    @FXML
    private TextField txtUserOrPartId;
    @FXML
    private CheckBox chbLateOnly;
    @FXML
    private ComboBox<Integer> cbHoraFim;

    @FXML
    private ComboBox<Integer> cbHoraIni;

    @FXML
    private ComboBox<Integer> cbMinFim;

    @FXML
    private ComboBox<Integer> cbMinIni;
    private final ObservableList<Event> searchResult = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        bindTableViewToItemsLists();
        bindTableColumnsToProperties();
        loadComboBox();
    }
    private void loadComboBox(){
        List<Integer> hours = new ArrayList<>();
        List<Integer> minutes = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if(i<24) hours.add(i);
            minutes.add(i);
        }
        cbHoraIni.setItems(FXCollections.observableList(hours));
        cbMinIni.setItems(FXCollections.observableList(minutes));
        cbHoraFim.setItems(FXCollections.observableList(hours));
        cbMinFim.setItems(FXCollections.observableList(minutes));
    }
    private void bindTableColumnsToProperties(){
        tcPatrimonialId.setCellValueFactory(new PropertyValueFactory<>("patrimonialId"));
        tcPartType.setCellValueFactory(new PropertyValueFactory<>("partType"));
        tcEventType.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        tcTimeStamp.setCellValueFactory(new PropertyValueFactory<>("stringTimeStamp"));
        tcUser.setCellValueFactory(new PropertyValueFactory<>("requesterName"));
        tcTechnician.setCellValueFactory(new PropertyValueFactory<>("technicianName"));
    }
    private void bindTableViewToItemsLists(){
        tvEvents.setItems(searchResult);
    }
    @FXML
    void backToPreviousScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("MainUI.fxml");
    }
    @FXML
    void exportPDF(ActionEvent event) {
        ExportReportUseCase exportReportUseCase = new ExportReportUseCase();
        List<Event> events = tvEvents.getItems();
        if (!events.isEmpty()) {
            exportReportUseCase.export(events);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SGPA informa");
            alert.setContentText("Relatório salvo com sucesso!");
            alert.showAndWait();
        }
    }
    @FXML
    void generateReport(ActionEvent event) {
        switch (mode){
            case BY_PART -> {generateReportByPart();}
            case BY_USER -> {System.out.println("método by user não implementado");}
            case GENERAL -> {System.out.println("método general não implementado");}
            default -> throw new IllegalArgumentException("Undefined report mode.");
        }
    }

    private void generateReportByPart(){
        try {
            if(isMissingParameters())
                throw new IllegalArgumentException("There are missing filters.");
            int patrimonialId = Integer.parseInt(txtUserOrPartId.getText());
            int hourStart = cbHoraIni.getSelectionModel().getSelectedItem();
            int minuteStart = cbMinIni.getSelectionModel().getSelectedItem();
            int hourEnd = cbHoraFim.getSelectionModel().getSelectedItem();
            int minuteEnd = cbMinFim.getSelectionModel().getSelectedItem();
            LocalDateTime start = dpStart.getValue().atTime(hourStart, minuteStart);
            LocalDateTime end = dpEnd.getValue().atTime(hourEnd, minuteEnd);
            EventDAO eventDAO = new SqliteEventDAO();
            PartItemDAO partItemDAO = new SqlitePartItemDAO();
            GenerateReportByPartUseCase generateReportByPartUseCase = new GenerateReportByPartUseCase(eventDAO, partItemDAO);
            searchResult.clear();
            searchResult.addAll(generateReportByPartUseCase.generate(patrimonialId, start, end));
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SGPA INFORMA");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private boolean isMissingParameters() {
        return txtUserOrPartId.getText().isEmpty()
                || cbHoraIni.getSelectionModel().isEmpty()
                || cbMinIni.getSelectionModel().isEmpty()
                || cbHoraFim.getSelectionModel().isEmpty()
                || cbMinFim.getSelectionModel().isEmpty()
                || dpStart.getValue() == null
                || dpEnd.getValue() == null;
    }

    public void configMode(ReportUIMode mode){
        this.mode = mode;
        switch (mode){
            case BY_PART -> {txtUserOrPartId.setPromptText("Digite nº do patrimônio");
                lbUserOrPartId.setText("Nº de patrmônio da peça");}
            case BY_USER -> {txtUserOrPartId.setPromptText("Digite o id do solicitante");
                lbUserOrPartId.setText("Id do solicitante");}
            case GENERAL -> {txtUserOrPartId.setVisible(false);lbUserOrPartId.setVisible(false);}
            default -> throw new IllegalArgumentException("Undefined report screen mode.");
        }
    }
}