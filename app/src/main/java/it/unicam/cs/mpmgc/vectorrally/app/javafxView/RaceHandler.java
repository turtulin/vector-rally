/*
package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.BasicMatchController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.match.MatchController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BasicGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.CLIMatchGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.CLISetupGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.view.TerminalIOController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class RaceHandler {

    public Label trackLabel;
    public Label difficultyLabel;
    public Label shiftRuleLabel;
    public Label turnLabel;
    public GridPane trackGridPane;
    public Button nextTurnButton;

    private MatchController matchController;
    private GUIMatchGameView gameView;
    private RaceTrack raceTrack;

    @FXML
    public void initialize() {
        gameView = new GUIMatchGameView();
        nextTurnButton.setOnAction(event -> gameView.onNextTurnButtonPressed());
    }

    public void setTrack(String track) {
        if (trackLabel != null) trackLabel.setText(track);
        checkAllFieldsSet();
    }

    public void setDifficulty(String difficulty) {
        if (difficultyLabel != null) difficultyLabel.setText(difficulty);
        checkAllFieldsSet();
    }

    public void setShiftRule(String shiftRule) {
        if (shiftRuleLabel != null) shiftRuleLabel.setText(shiftRule);
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
        String track = trackLabel.getText();
        String difficulty = difficultyLabel.getText();
        String shiftRule = shiftRuleLabel.getText();
        IOController ioController = new TerminalIOController();
        CLISetupGameView game = new CLISetupGameView(ioController);
        GameSetup setup = new BasicGameSetup(game);
        raceTrack = setup.initializeTrack();
        List<Player> players = setup.initializePlayers(raceTrack);

        BasicMovesGenerator<NeighborsGenerator> movesGenerator =
                new BasicMovesGenerator<>(new FourNeighborsGenerator(), new BasicMoveValidator());

        matchController = new BasicMatchController(gameView, movesGenerator, players, raceTrack);
    }

    @FXML
    private void executeMovement() throws Exception {
        matchController.startMatch();
        nextTurnButton.setDisable(true);
    }
}
*/
