package it.unicam.cs.mpmgc.vectorrally.app.handler;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.GameEngine;
import it.unicam.cs.mpmgc.vectorrally.api.controller.match.GUIGameEngine;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GUIGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class RaceHandler {

    @FXML
    private Label trackLabel;

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label shiftRuleLabel;

    @FXML
    private GridPane trackGridPane;



    public void setTrack(String track) {
        if (trackLabel != null) {
            trackLabel.setText(track);
        }
        checkAllFieldsSet();
    }

    public void setDifficulty(String difficulty) {
        if (difficultyLabel != null) {
            difficultyLabel.setText(difficulty);
        }
        checkAllFieldsSet();
    }

    public void setShiftRule(String shiftRule) {
        if (shiftRuleLabel != null) {
            shiftRuleLabel.setText(shiftRule);
        }
        checkAllFieldsSet();
    }

    private void checkAllFieldsSet() {
        if (trackLabel.getText() != null && !trackLabel.getText().isEmpty() &&
                difficultyLabel.getText() != null && !difficultyLabel.getText().isEmpty() &&
                shiftRuleLabel.getText() != null && !shiftRuleLabel.getText().isEmpty()) {

            Platform.runLater(() -> {
                try {
                    handleSetup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void handleSetup() throws Exception {
        String difficulty = this.difficultyLabel.getText();
        String track = this.trackLabel.getText();
        String shiftRule = this.shiftRuleLabel.getText();
        GameSetup setup = new GUIGameSetup(difficulty, track, shiftRule);
        RaceTrack raceTrack = setup.initializeTrack();
        mapTrack(raceTrack);
        GameEngine engine = new GUIGameEngine(setup);
        engine.startGame();
    }

    private void mapTrack(RaceTrack raceTrack) {
        trackGridPane.getChildren().clear();
        int cellSize = 20;  // Increase the size of each cell
        for (int x = 0; x < raceTrack.getLength(); x++) {
            for (int y = 0; y < raceTrack.getWidth(); y++) {
                TrackComponent component = raceTrack.getComponentAt(x, y);
                Rectangle rect = new Rectangle(cellSize, cellSize);
                switch (component) {
                    case WALL:
                        rect.setFill(Color.BLACK);
                        break;
                    case ROAD:
                        rect.setFill(Color.WHITE);
                        break;
                    case START_LINE:
                        rect.setFill(Color.GREEN);
                        break;
                    case END_LINE:
                        rect.setFill(Color.RED);
                        break;
                    case START_POSITION:
                        rect.setFill(Color.BLUE);
                        break;
                }
                trackGridPane.add(rect, y, x);
            }
        }
    }
}
