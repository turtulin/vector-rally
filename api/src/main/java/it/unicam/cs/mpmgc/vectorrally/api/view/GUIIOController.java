package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import java.util.List;

public class GUIIOController implements IOController{
    @Override
    public boolean askIfPlayerKnowsRules() {
        return false;
    }

    @Override
    public int chooseRuleType() {
        return 0;
    }

    @Override
    public List<String> findTrack() throws Exception {
        return null;
    }

    @Override
    public String pickTrack(List<String> trackFiles) {
        return null;
    }

    @Override
    public int askNumberOfHumanPlayers(int maxPlayers) {
        return 0;
    }

    @Override
    public CarColour chooseCarColor(List<CarColour> availableColors) {
        return null;
    }

    @Override
    public BotStrategy chooseEachBotStrategyDifficulty(CarColour carColour) {
        return null;
    }

    @Override
    public boolean askIfSatisfiedWithConfiguration(RaceTrack raceTrack, List<Player> players) {
        return false;
    }

    @Override
    public int chooseMove(List<Move> possibleMoves) {
        return 0;
    }

    @Override
    public boolean askToPlayAnotherMatch() {
        return false;
    }

    @Override
    public void displayEndMatchMessage() {

    }

    @Override
    public Position chooseStartingPosition(Player player, List<Position> availablePositions) {
        return null;
    }

    @Override
    public boolean askToChooseForEachBot() {
        return false;
    }

    @Override
    public BotStrategy chooseAllBotStrategyDifficulty() {
        return null;
    }
}
