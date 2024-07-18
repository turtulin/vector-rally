package it.unicam.cs.mpmgc.vectorrally.app.handler;

import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.view.TerminalIOController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import java.util.Collections;
import java.util.List;

public class SettingsHandler {

    @FXML
    private SplitMenuButton splitMenuButton;
    @FXML
    public void handleListTracks(ActionEvent actionEvent) {
        IOController ioController = new TerminalIOController();
        try {
            List<String> tracks = Collections.singletonList(ioController.findTrack());
            System.out.println("Tracks: " + tracks);
            populateSplitMenuButton(tracks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateSplitMenuButton(List<String> tracks) {

        splitMenuButton.getItems().clear();  // Clear any existing items
        for (String track : tracks) {
            MenuItem menuItem = new MenuItem(track);
            menuItem.setOnAction(event -> handleTrackSelection(track));
            splitMenuButton.getItems().add(menuItem);
        }
    }

    private void handleTrackSelection(String track) {
        // Handle the track selection
        System.out.println("Selected track: " + track);
    }
}
