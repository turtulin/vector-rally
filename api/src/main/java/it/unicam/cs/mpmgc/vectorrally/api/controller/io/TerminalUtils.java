package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.util.List;

public class TerminalUtils implements Utils {

    @Override
    public void printWelcomeMessage() {
        System.out.println();
        System.out.println("Welcome to Vector Rally!");
        System.out.println("In this game, players compete to reach the finish line.");
        System.out.println("All players will start on the starting positions.");
        System.out.println("Players must cross the starting line to begin racing.");
        System.out.println("If a player goes out of bounds, they will be eliminated.");
        System.out.println("Have fun!!!");
        System.out.println();
    }

    @Override
    public void printRules() {
        System.out.println("Rules of the game:");
        System.out.println("1. Move Validation Rule");
        System.out.println("2. Win Condition Rule");
        System.out.println("3. Penalty Rule (for wall and oil spot)");
        System.out.println("4. Starting Position Rule");
        System.out.println("5. Turn-Based Rule");
        System.out.println("6. Speed Limit Rule");
    }

    @Override
    public void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        if (raceTrack == null || players == null)
            throw new NullPointerException("Illegal null argument/s for print operation");
        for (int y = 0; y < raceTrack.getLength(); y++) {
            for (int x = 0; x < raceTrack.getWidth(); x++) {
                Position position = new Position(x, y);
                if (!printPlayer(getPlayerToPrint(position, players)))
                    System.out.print(getCharToPrintFor(raceTrack.getComponentAt(x, y)));
            }
            System.out.println();
        }
    }

    @Override
    public void printSetupResult(RaceTrack raceTrack, List<Player> players) {
        if (raceTrack == null || players == null)
            throw new NullPointerException("Illegal null argument/s for print operation");
        System.out.println("This is a preview of the chosen racetrack:");
        printRaceTrack(raceTrack, players);
    }

    @Override
    public void printTurn(int turn, Player player) {
        if (player != null) {
            System.out.println("TURN " + turn + " for player " + player.getPlayerCarColour());
        }
    }

    @Override
    public void printMoves(RaceTrack raceTrack, List<Player> players, List<Move> moves) {
        if (raceTrack == null || moves == null)
            throw new NullPointerException("Illegal null argument/s for print operation");
        System.out.println("Here are the moves you can perform");
        int moveIdentifier = 0;
        for (int y = 0; y < raceTrack.getLength(); y++) {
            for (int x = 0; x < raceTrack.getWidth(); x++) {
                Position position = new Position(x, y);
                if (isPositionAPossibleDestination(position, moves))
                    System.out.print(moveIdentifier++);
                else if (!printPlayer(getPlayerToPrint(position, players)))
                    System.out.print(getCharToPrintFor(raceTrack.getComponentAt(x, y)));
            }
            System.out.println();
        }
    }

    @Override
    public void printWinMessage(Player player) {
        if (player == null)
            throw new NullPointerException("Cannot print player win message due to null argument");
        System.out.println("Player " + player.getPlayerCarColour() + " has crossed the finish line");
        System.out.println("Player " + player.getPlayerCarColour() + " WINS!!!");
    }

    @Override
    public void printEliminationMessage(Player player) {
        if (player == null)
            throw new NullPointerException("Cannot print player elimination message due to null argument");
        System.out.println("Player " + player.getPlayerCarColour() + " has no more moves to perform");
        System.out.println("Player " + player.getPlayerCarColour() + " LOSES!!!");
    }

    @Override
    public void printDisqualificationWarning(Player player) {
        String colorCode = player.getPlayerCarColour().toString();
        System.out.println("Player " + colorCode + ", if you do not cross the line " +
                "in the next turns you will be disqualified!!!");
    }

    @Override
    public void printDisqualificationMessage(Player player) {
        if (player.getPlayerType() == PlayerType.BOT)
            System.out.println("BOT has been disqualified");
        else {
            String colorCode = player.getPlayerCarColour().toString();
            System.out.println("Player " + colorCode + " has been disqualified");
        }
    }

    private Player getPlayerToPrint(Position position, List<Player> players) {
        for (Player player : players)
            if (player.getPosition().equals(position))
                return player;
        return null;
    }

    private boolean printPlayer(Player player) {
        if (player != null) {
            String code = player.getPlayerCarColour().toString();
            String toPrint = player.getPlayerType() == PlayerType.BOT ? code + 'B' : code + 'P';
            System.out.print(toPrint);
            return true;
        }
        return false;
    }

    private String getCharToPrintFor(TrackComponent trackComponent) {
        if (trackComponent == null)
            throw new NullPointerException("Cannot recover printable char due to null argument");
        return switch (trackComponent) {
            case WALL -> "\033[30m+\033[0m";
            case ROAD -> "\033[90m+\033[0m";
            case START_LINE -> "\033[94m+\033[0m";
            case END_LINE -> "\033[95m+\033[0m";
            case OIL_SPOT -> "\033[33m+\033[0m";
            case START_POSITION -> "\033[93m+\033[0m";
        };
    }

    private boolean isPositionAPossibleDestination(Position position, List<Move> moves) {
        for (Move move : moves)
            if (position.equals(move.position()))
                return true;
        return false;
    }
}
