package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.BasicMatchController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.match.MatchController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.SetupResult;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RaceHandler implements MatchGameView {
    @FXML
    private Label turnLabel;

    @FXML
    private GridPane trackGridPane;

    @FXML
    private Button nextTurnButton;

    private List<Move> possibleMoves;
    private Move selectedMove;
    private CountDownLatch moveSelectedLatch;
    private CountDownLatch nextTurnLatch;
    private MatchController matchController;
    private final GameMessageProvider messageProvider = new GameMessageProvider();

    @FXML
    public void initialize() {
        nextTurnButton.setOnAction(event -> goToNextTurn());
        turnLabel.setText(messageProvider.getPreparationMessage());
    }

    public void initializeRace(SetupResult setupResult) {
        BasicMovesGenerator<NeighborsGenerator> movesGenerator = new BasicMovesGenerator<>(setupResult.generator(), new BasicMoveValidator());
        matchController = new BasicMatchController(this, movesGenerator, setupResult.players(), setupResult.raceTrack());
        Task<Void> matchTask = new Task<>() {
            @Override
            protected Void call() {
                matchController.startMatch();
                return null;
            }
        };
        matchTask.setOnFailed(e -> {
            Throwable ex = matchTask.getException();
            ex.printStackTrace();
        });
        new Thread(matchTask).start();
    }

    @Override
    public void displayPossibleMoves(List<Player> players, Track raceTrack, List<Coordinates> possibleDestinations) {
        if (matchController.getTurnHandler().getCurrentPlayer() instanceof HumanPlayer) {
            nextTurnButton.setDisable(true);
        }
        Platform.runLater(() -> {
            trackGridPane.getChildren().clear();
            int cellSize = 20;
            for (int x = 0; x < raceTrack.getLength(); x++) {
                for (int y = 0; y < raceTrack.getWidth(); y++) {
                    Rectangle rect = new Rectangle(cellSize, cellSize);
                    Position currentPosition = new Position(x, y);
                    if (possibleDestinations.contains(currentPosition)) {
                        rect.setFill(Color.GREY);
                        rect.setOnMouseClicked(event -> handleGridClick(currentPosition));
                    } else {
                        switch (raceTrack.getComponentAt(x, y)) {
                            case WALL -> rect.setFill(Color.BLACK);
                            case ROAD -> rect.setFill(new Color(1, 1, 1, 0));
                            case START_LINE -> rect.setFill(Color.WHITE);
                            case END_LINE -> rect.setFill(Color.RED);
                        }
                    }
                    trackGridPane.add(rect, y, x);
                }
            }
            for (Player player : players) {
                Coordinates playerPosition = player.getPosition();
                Rectangle playerRect = new Rectangle(cellSize, cellSize, getColourForCar(player.getPlayerCarColour()));
                trackGridPane.add(playerRect, playerPosition.getY(), playerPosition.getX());
            }
        });
    }

    private void handleGridClick(Position position) {
        for (Move move : possibleMoves) {
            if (move.getDestination().equals(position)) {
                selectedMove = move;
                moveSelectedLatch.countDown();
                break;
            }
        }
        nextTurnButton.setDisable(false);
    }

    @Override
    public void displayWinner(Player winner) {
        Platform.runLater(() -> SceneManager.getInstance().switchToScene("/winner.fxml", controller -> {
            if (controller instanceof WinnerHandler winnerHandler) {
                winnerHandler.setWinner(winner);
            }
        }));
    }

    @Override
    public void displayGameOver() {
        Platform.runLater(() -> SceneManager.getInstance().switchToScene("/gameOver.fxml"));
    }

    @Override
    public void displayTurn(Player player, int counter) {
        Platform.runLater(() -> turnLabel.setText(messageProvider.getTurnMessage(counter, player)));
    }

    @Override
    public void displayElimination(Player player) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(messageProvider.getEliminationTitle());
            alert.setHeaderText(messageProvider.getEliminationMessage(player));
            alert.showAndWait();
        });
    }

    @Override
    public Move getMoveChoice(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
        selectedMove = null;
        moveSelectedLatch = new CountDownLatch(1);
        try {
            moveSelectedLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectedMove;
    }

    @Override
    public void goToNextTurn() {
        nextTurnLatch = new CountDownLatch(1);
        nextTurnButton.setOnAction(event -> {
            if (nextTurnLatch != null) nextTurnLatch.countDown();
        });
        try {
            nextTurnLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
}
