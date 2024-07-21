package it.unicam.cs.mpmgc.vectorrally.app.handler;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.*;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GUIGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.GraphicalIOController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaceHandler {

    public Button moveButton;
    private GUIMatchController matchController;

    @FXML
    private Label trackLabel;

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label shiftRuleLabel;

    @FXML
    private GridPane trackGridPane;

    @FXML
    private TextField moveNumber;
    @FXML
    private Button nextTurnButton;

    private List<Player> players;
    private RaceTrack raceTrack;

    private List<List<Move>> allPossibleMoves;

    GraphicalIOController ioController = new GraphicalIOController();
    BasicMovesGenerator<NeighborsGenerator> movesGenerator = new BasicMovesGenerator<>(new FourNeighborsGenerator(), new BasicMoveValidator());

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
        moveButton.setDisable(true);
        String difficulty = this.difficultyLabel.getText();
        String track = this.trackLabel.getText();
        String shiftRule = this.shiftRuleLabel.getText();
        GUIGameSetup setup = new GUIGameSetup(difficulty, track, shiftRule);
        raceTrack = setup.initializeTrack();
        players = setup.initializePlayers(raceTrack);
        allPossibleMoves = new ArrayList<>();
        mapTrack(raceTrack, players);
        matchController = new GUIMatchController(ioController, movesGenerator);
        matchController.initializeMatch(players, raceTrack);
    }

    private void mapTrack(RaceTrack raceTrack, List<Player> players) {
        trackGridPane.getChildren().clear();
        int cellSize = 20;
        for (int x = 0; x < raceTrack.getLength(); x++) {
            for (int y = 0; y < raceTrack.getWidth(); y++) {
                TrackComponent component = raceTrack.getComponentAt(x, y);
                Rectangle rect = new Rectangle(cellSize, cellSize);
                switch (component) {
                    case WALL -> rect.setFill(Color.BLACK);
                    case ROAD -> rect.setFill(Color.WHITE);
                    case START_LINE -> rect.setFill(Color.GREEN);
                    case END_LINE -> rect.setFill(Color.RED);
                    case START_POSITION -> rect.setFill(Color.BLUE);
                }
                trackGridPane.add(rect, y, x);
            }
        }

        for (Player player : players) {
            Rectangle playerRect = new Rectangle(cellSize, cellSize, getColourForCar(player.getPlayerCarColour()));
            trackGridPane.add(playerRect, player.getPosition().getY(), player.getPosition().getX());
        }

        for (List<Move> moves : allPossibleMoves) {
            for (Move move : moves) {
                Rectangle moveRect = new Rectangle(cellSize, cellSize, Color.GREY);
                trackGridPane.add(moveRect, move.getDestination().getY(), move.getDestination().getX());
            }
        }
    }
private void mapTrackMoves(RaceTrack raceTrack, List<Player> players, List<List<Move>> possibleMoves) {
    mapTrack(raceTrack, players);

    if (possibleMoves != null) {
        int cellSize = 20;
        for (List<Move> moves : possibleMoves) {
            for (Move move : moves) {
                System.out.println(move.getDestination().getX() + " " + move.getDestination().getY());
                Rectangle moveRect = new Rectangle(cellSize, cellSize, Color.GREY);
                trackGridPane.add(moveRect, move.getDestination().getY(), move.getDestination().getX());
            }
        }
    }
}

    private Color getColourForCar(CarColour colour) {
        return switch (colour) {
            case BLUE -> Color.BLUE;
            case RED -> Color.RED;
            case GREEN -> Color.GREEN;
            case YELLOW -> Color.YELLOW;
            case ORANGE -> Color.ORANGE;
            case PURPLE -> Color.PURPLE;
            case PINK -> Color.PINK;
            case CYAN -> Color.CYAN;
            default -> Color.BROWN;
        };
    }

    public void showBotPlayerMoves(List<List<Move>> possibleMoves) {
        allPossibleMoves.addAll(possibleMoves);
        mapTrackMoves(raceTrack, players, possibleMoves);
    }

    public void clearPreviousMoves() {
        allPossibleMoves.clear();
    }

    public void nextTurn() {
        clearPreviousMoves();
        BasicMovesGenerator<NeighborsGenerator> movesGenerator = new BasicMovesGenerator<>(new FourNeighborsGenerator(), new BasicMoveValidator());
        allPossibleMoves = new ArrayList<>();
        Integer i = 1;
        List<Player> playersToEliminate = new ArrayList<>();
        for (Player player : players) {
            List<Move> possibleMoves = movesGenerator.generatePossibleMoves(player, raceTrack, players);
            if (possibleMoves.isEmpty())
                playersToEliminate.add(player);
            else {
                System.out.println(i.toString() + player.getPosition() + " " + player.getPlayerAcceleration());

                allPossibleMoves.add(possibleMoves);
                i++;
            }
        }
        for (Player player : playersToEliminate) {
            matchController.handleElimination(player);
        }
        if (players.isEmpty()){
            nextTurnButton.setDisable(true);
            moveButton.setDisable(true);
            // game over
            switchToGameOverScene();
        }else {
            nextTurnButton.setDisable(true);
            moveButton.setDisable(false);
        }
    }

    public void ExecuteMovement(ActionEvent actionEvent) {
        for (Player player : players) {
            Random random = new Random();
            int movement = random.nextInt(allPossibleMoves.get(0).size());
            player.setPosition(allPossibleMoves.get(0).get(movement).getDestination());
            player.setPlayerAcceleration(allPossibleMoves.get(0).get(movement).acceleration());
            //player.setPosition(allPossibleMoves.get(0).get(0).getDestination());
            //player .setPlayerAcceleration(allPossibleMoves.get(0).get(0).acceleration());
            if(matchController.checkIfPlayerWins(allPossibleMoves.get(0).get(0))) {
                //game over
                System.out.println("Player wins");
                switchToWinScene();
            }
            allPossibleMoves.remove(allPossibleMoves.get(0));
        }
        mapTrack(raceTrack, players);
        clearPreviousMoves();
        nextTurnButton.setDisable(false);
        moveButton.setDisable(true);
    }

    private void switchToWinScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/winner.fxml"));
            Stage stage = (Stage) trackGridPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToGameOverScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gameover.fxml"));
            Stage stage = (Stage) trackGridPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
