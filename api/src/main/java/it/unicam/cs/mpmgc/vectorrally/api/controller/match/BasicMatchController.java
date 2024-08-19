package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BotStrategyFactory;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.GameView;

import java.util.List;

public class BasicMatchController implements MatchController {
    private TurnHandler turnHandler;
    private boolean isGameEnded;
    private final List<Player> players;
    private final RaceTrack raceTrack;
    private final GameView gameView;
    private final BasicMovesGenerator<NeighborsGenerator> moveGenerator;
    private final BotStrategyFactory botStrategyFactory;


    public BasicMatchController(GameView gameView, BasicMovesGenerator<NeighborsGenerator> moveGenerator, List<Player> players, RaceTrack raceTrack) {
        this.gameView = gameView;
        this.moveGenerator = moveGenerator;
        this.raceTrack = raceTrack;
        this.isGameEnded = false;
        this.players = players;
        this.botStrategyFactory = new BotStrategyFactory(moveGenerator.getNeighborsGenerator());
    }

    @Override
    public void startMatch() throws Exception {
        this.turnHandler = new BasicTurnHandler(players);
        while (!isGameEnded()) {
            handleTurn(turnHandler.getCurrentPlayer());
            gameView.goToNextTurn();
        }
        handleEndGame();
    }

    @Override
    public void handleTurn(Player player) throws Exception {
        turnHandler.startTurn();
        gameView.displayTurn(player, turnHandler.getTurnCounter());
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, raceTrack, turnHandler.getPlayers());
        if (possibleMoves.isEmpty()) handleElimination(player);
        else {
            gameView.displayPossibleMoves(turnHandler.getPlayers(), raceTrack, moveGenerator.getPossibleDestinations(possibleMoves));
            Move chosenMove = findMove(player, possibleMoves);
            player.makeMove(chosenMove);
            if(moveGenerator.isWinningMove(chosenMove, raceTrack)) setGameEnded(true);
        }
        turnHandler.endTurn();
    }

    @Override
    public void handleElimination(Player player) throws Exception {
        gameView.displayElimination(player);
        turnHandler.removePlayer(player);
        if (turnHandler.getPlayers().isEmpty()) setGameEnded(true);
    }

    @Override
    public void handleEndGame() throws Exception {
        if (turnHandler.getPlayers().isEmpty()) gameView.displayGameOver();
        else gameView.displayWinner(turnHandler.getCurrentPlayer());
    }

    @Override
    public boolean isGameEnded() {
        return isGameEnded;
    }

    @Override
    public void setGameEnded(boolean isGameEnded) {
        this.isGameEnded = isGameEnded;
    }

    @Override
    public Move findMove(Player player, List<Move> possibleMoves) {
        if (player instanceof BotPlayer botPlayer) return botStrategyFactory.getStrategy(botPlayer.getStrategy()).decideMove(botPlayer, possibleMoves);
        else return gameView.getMoveChoice(possibleMoves);
    }
}
