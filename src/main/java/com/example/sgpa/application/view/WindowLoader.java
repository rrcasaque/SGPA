package com.example.sgpa.application.view;

import com.example.sgpa.application.repository.sqlite.DataBaseBuilder;
import com.example.sgpa.domain.usecases.reservation.UpdateExpiredReservationsUseCase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class WindowLoader extends Application {
    UpdateExpiredReservationsUseCase updateExpired = new UpdateExpiredReservationsUseCase();
    private static Scene scene;
    private static Object controller;
    @Override
    public void start(Stage stage) throws IOException {
        DataBaseBuilder DBBuilder = new DataBaseBuilder();
        DBBuilder.buildDataBaseIfMissing();
        updateExpired.update();
        scene = new Scene(loadFxml("LoginUI.fxml"));
        stage.setMinWidth(690);
        stage.setTitle("SGPA");
        stage.setScene(scene);
        stage.show();
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFxml(fxml));
    }
    private static Parent loadFxml(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = fxmlLoader.load(WindowLoader.class.getResource(fxml).openStream());
        controller = fxmlLoader.getController();
        return parent;
    }
    public static Object getController() {
        return controller;
    }
    public static void main(String[] args) {
        launch();
    }
}