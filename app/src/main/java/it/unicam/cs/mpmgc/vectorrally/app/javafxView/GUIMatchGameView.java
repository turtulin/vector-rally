/*
package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.view.MatchGameView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GUIMatchGameView implements MatchGameView {

    private Move selectedMove;
    private List<Move> possibleMoves;
    private CountDownLatch moveSelectedLatch;
    private CountDownLatch nextTurnLatch;

    private GridPane pane;
    private Label label;

    public GUIMatchGameView() {
    }

    @Override
    public void displayPossibleMoves(List<Player> players, RaceTrack raceTrack, List<Position> possibleDestinations) {
        Platform.runLater(() -> {
            pane.getChildren().clear();
            int cellSize = 20;
            for (int x = 0; x < raceTrack.getLength(); x++) {
                for (int y = 0; y < raceTrack.getWidth(); y++) {
                    Rectangle rect = new Rectangle(cellSize, cellSize);
                    Position currentPosition = new Position(x, y);
                    if (possibleDestinations.contains(currentPosition)) {
                        rect.setFill(Color.LIGHTYELLOW); // Highlight available move positions
                        rect.setOnMouseClicked(event -> handleGridClick(currentPosition));
                    } else {
                        switch (raceTrack.getComponentAt(x, y)) {
                            case WALL -> rect.setFill(Color.BLACK);
                            case ROAD -> rect.setFill(Color.WHITE);
                            case START_LINE -> rect.setFill(Color.GREEN);
                            case END_LINE -> rect.setFill(Color.RED);
                            case START_POSITION -> rect.setFill(Color.BLUE);
                        }
                    }
                    pane.add(rect, y, x);
                }
            }
            for (Player player : players) {
                Position playerPosition = player.getPosition();
                Rectangle playerRect = new Rectangle(cellSize, cellSize, getColourForCar(player.getPlayerCarColour()));
                pane.add(playerRect, playerPosition.getY(), playerPosition.getX());
            }
        });
    }

    @Override
    public void displayWinner(Player winner) {
        Platform.runLater(() -> {
            SceneManager.getInstance().switchToScene("/path/to/winner.fxml", controller -> {
                if (controller instanceof WinnerHandler) {
                    ((WinnerHandler) controller).setWinner(winner.getName());
                }
            });
        });
    }

    @Override
    public void displayGameOver() {
        Platform.runLater(() -> {
            SceneManager.getInstance().switchToScene("/path/to/gameover.fxml");
        });
    }

    @Override
    public void displayTurn(Player player, int counter) {
        Platform.runLater(() -> label.setText("Turn " + counter + ": " + player.getName() + "'s turn"));
    }

    @Override
    public void displayElimination(Player player) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Player Eliminated");
            alert.setHeaderText(player.getName() + " has been eliminated.");
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
        try {
            nextTurnLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initializeRace(GridPane pane, Label label) {
        this.pane = pane;
        this.label = label;
    }

    public void onNextTurnButtonPressed() {
        // controllare che vada bene quello di GraphicalIOController
        if (nextTurnLatch != null) {
            nextTurnLatch.countDown();
        }
    }

    private void handleGridClick(Position position) {
        for (Move move : possibleMoves) {
            if (move.getDestination().equals(position)) {
                selectedMove = move;
                moveSelectedLatch.countDown();
                break;
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
}
*/
