package it.unicam.cs.mpmgc.vectorrally.app.handler;

import it.unicam.cs.mpmgc.vectorrally.api.view.IOControllerNew;
import it.unicam.cs.mpmgc.vectorrally.api.view.TerminalIOControllerNew;
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

        IOControllerNew IOControllerNew = new TerminalIOControllerNew();
        loader.setControllerFactory(param -> new SettingsHandler(IOControllerNew));

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
