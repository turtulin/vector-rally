package it.unicam.cs.mpmgc.vectorrally.app.handler;

import it.unicam.cs.mpmgc.vectorrally.api.view.GUIIOController;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuHandler {

    @FXML
    private void handleStartButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/settings.fxml"));

        IOController IOController = new GUIIOController();
        loader.setControllerFactory(param -> new SettingsHandler(IOController));

        Parent settingsRoot = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(settingsRoot));
        stage.show();
    }

    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
        Platform.exit();
    }
}
