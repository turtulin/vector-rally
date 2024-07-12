package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.handlers.*;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.BasicMoveGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.MoveGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.BasicComponentPassChecker;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.ComponentPassChecker;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.FinishLineWinCondition;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.StartingPositionValidator;

import java.util.List;

public class VectorRallyMatchController implements MatchController {
    private final GameSetup gameSetup;
    private final TerminalIOController ioController;
    private final BasicMoveGenerator moveGenerator;

    public VectorRallyMatchController(GameSetup gameSetup, TerminalIOController ioController, NeighborsGenerator neighborsGenerator) {
        this.gameSetup = gameSetup;
        this.ioController = ioController;
        this.moveGenerator = new BasicMoveGenerator<>(neighborsGenerator);
    }

    @Override
    public void startSimulation() {
        RaceTrack raceTrack = gameSetup.getRaceTrack();
        List<Player> players = gameSetup.getPlayers();
        boolean gameRunning = true;

        while (gameRunning) {
            for (Player player : players) {
                if (!player.isRacing()) continue;
                List<Move> possibleMoves = moveGenerator.generateMoves(player, raceTrack);
                ioController.printRaceTrack(raceTrack, players);
                int chosenMoveIndex = ioController.chooseMoveToPerform(possibleMoves);
                Move chosenMove = possibleMoves.get(chosenMoveIndex);

                MoveHandler handlerChain = createHandlersChain(raceTrack);

                if (!handlerChain.handleMove(player, (Position) chosenMove.position())) {
                    ioController.printInvalidMoveMessage(player);
                    player.setRacing(false);
                } else {
                    player.setPosition((Position) chosenMove.position());
                    player.setPlayerAcceleration(chosenMove.acceleration());
                }

                if (new FinishLineWinCondition(raceTrack).isRespected(player)) {
                    ioController.printWinMessage(player);
                    gameRunning = false;
                    break;
                }
            }
        }
        ioController.printGameOver();
    }

    private MoveHandler createHandlersChain(RaceTrack raceTrack) {
        MoveHandler illegalDirectionHandler = new IllegalDirectionHandler(new FinishLineWinCondition(raceTrack));
        MoveHandler oilSpotHandler = new OilSpotHandler(new BasicComponentPassChecker(raceTrack));
        MoveHandler wallHandler = new WallHandler(new BasicComponentPassChecker(raceTrack));
        MoveHandler startPositionHandler = new StartingPositionHandler(new StartingPositionValidator(raceTrack));

        ((IllegalDirectionHandler) illegalDirectionHandler).setNextHandler(oilSpotHandler);
        ((OilSpotHandler) oilSpotHandler).setNextHandler(wallHandler);
        ((WallHandler) wallHandler).setNextHandler(startPositionHandler);

        return illegalDirectionHandler;
    }
}
