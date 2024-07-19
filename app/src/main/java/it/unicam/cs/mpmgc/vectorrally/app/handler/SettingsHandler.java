package it.unicam.cs.mpmgc.vectorrally.app.handler;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOControllerNew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import java.io.IOException;
import java.util.List;

public class SettingsHandler {

    private final IOControllerNew IOControllerNew;

    @FXML
    private SplitMenuButton trackMenuButton;

    @FXML
    private SplitMenuButton difficultyMenuButton;

    @FXML
    private SplitMenuButton shiftRuleMenuButton;

    @FXML
    private Button setupButton;

    public SettingsHandler(IOControllerNew IOControllerNew) {
        this.IOControllerNew = IOControllerNew;
    }

    @FXML
    public void initialize() throws Exception {
        setupButton.setDisable(true);
        loadTrackNames();
        setupDifficultyMenu();
        setupShiftRuleMenu();
        trackMenuButton.setOnAction(event -> checkReadyStatus());
        difficultyMenuButton.setOnAction(event -> checkReadyStatus());
        shiftRuleMenuButton.setOnAction(event -> checkReadyStatus());
        setupButton.setOnAction(event -> {
            try {
                handleSetupButtonAction(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void loadTrackNames() throws Exception {
        List<String> trackNames = IOControllerNew.findTrack();
        for (String track : trackNames) {
            MenuItem menuItem = new MenuItem(track);
            menuItem.setOnAction(event -> {
                trackMenuButton.setText(track);
                checkReadyStatus();
            });
            trackMenuButton.getItems().add(menuItem);
        }
    }

    private void setupDifficultyMenu() {
        for (BotStrategy strategy : BotStrategy.values()) {
            MenuItem menuItem = new MenuItem(strategy.name());
            menuItem.setOnAction(event -> {
                difficultyMenuButton.setText(strategy.name());
                checkReadyStatus();
            });
            difficultyMenuButton.getItems().add(menuItem);
        }
    }

    private void setupShiftRuleMenu() {
        List<Class<?>> shiftRules = List.of(FourNeighborsGenerator.class, EightNeighborsGenerator.class);

        for (Class<?> shiftRuleClass : shiftRules) {
            String className = shiftRuleClass.getSimpleName();
            String menuText = className.replace("NeighborsGenerator", " neighbors");
            MenuItem menuItem = new MenuItem(menuText);
            menuItem.setOnAction(event -> {
                shiftRuleMenuButton.setText(menuText);
                checkReadyStatus();
            });
            shiftRuleMenuButton.getItems().add(menuItem);
        }
    }

    private void checkReadyStatus() {
        boolean isReady = !trackMenuButton.getText().equals("Racetracks") &&
                !difficultyMenuButton.getText().equals("Bots difficulty") &&
                !shiftRuleMenuButton.getText().equals("Shift rule");
        setupButton.setDisable(!isReady);
    }

    @FXML
    private void handleSetupButtonAction(ActionEvent event) throws IOException {
        String selectedTrack = trackMenuButton.getText();
        String selectedDifficulty = difficultyMenuButton.getText();
        String selectedShiftRule = shiftRuleMenuButton.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/race.fxml"));
        Parent raceRoot = loader.load();

        RaceHandler raceHandler = loader.getController();
        raceHandler.setTrack(selectedTrack);
        raceHandler.setDifficulty(selectedDifficulty);
        raceHandler.setShiftRule(selectedShiftRule);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(raceRoot));
        stage.show();
    }

}
