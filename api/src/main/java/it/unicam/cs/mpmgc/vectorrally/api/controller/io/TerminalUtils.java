package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.PlayerType;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.util.List;

public class TerminalUtils implements Utils {

    @Override
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Vector Rally!");
    }

    @Override
    public void printSetupResult(RaceTrack raceTrack, List<Player> players) {
        System.out.println("Race Track and Players are set up.");
        printRaceTrack(raceTrack, players);
    }

    @Override
    public void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        for (int y = 0; y < raceTrack.getLength(); y++) {
            for (int x = 0; x < raceTrack.getWidth(); x++) {
                Position position = new Position(x, y);
                if (!printPlayer(getPlayerAtPosition(position, players)))
                    System.out.print(getCharForComponent(raceTrack.getComponentAt(x, y)));
            }
            System.out.println();
        }
    }

    private Player getPlayerAtPosition(Position position, List<Player> players) {
        for (Player player : players) {
            if (player.getPosition().equals(position)) {
                return player;
            }
        }
        return null;
    }

    private boolean printPlayer(Player player) {
        if (player != null) {
            String colorCode = getColorCode(player.getPlayerCarColour());
            String reset = "\033[0m";
            String toPrint = player.getPlayerType() == PlayerType.BOT ? colorCode + 'B' + reset : colorCode + 'P' + reset;
            System.out.print(toPrint);
            return true;
        }
        return false;
    }

    private String getCharForComponent(TrackComponent component) {
        return switch (component) {
            case WALL -> "\033[30m#\033[0m";
            case ROAD -> "\033[90m+\033[0m";
            case START_LINE -> "\033[94m+\033[0m";
            case END_LINE -> "\033[95m+\033[0m";
            case OIL_SPOT -> "\033[33mO\033[0m";
            case START_POSITION -> "\033[93m+\033[0m";
        };
    }

    private String getColorCode(CarColour colour) {
        return switch (colour) {
            case RED -> "\033[31m";
            case ORANGE -> "\033[33m";
            case YELLOW -> "\033[93m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            case PURPLE -> "\033[35m";
        };
    }

    @Override
    public void printDisqualificationWarning(Player player) {
        String colorCode = getColorCode(player.getPlayerCarColour());
        System.out.println("Player " + colorCode + "P" + "\033[0m" + ", if you do not cross the line " +
                "in the next turns you will be disqualified!!!");
    }

    @Override
    public void printDisqualificationMessage(Player player) {
        if (player.getPlayerType() == PlayerType.BOT)
            System.out.println("BOT has been disqualified");
        else {
            String colorCode = getColorCode(player.getPlayerCarColour());
            System.out.println("Player " + colorCode + "P" + "\033[0m" + " has been disqualified");
        }
    }

    @Override
    public void printTurn(int turn, Player player) {
        if (player.getPlayerType() != PlayerType.BOT) {
            String playerColorCode = getColorCode(player.getPlayerCarColour());
            System.out.println("TURN " + turn + " for player " + playerColorCode + "P" + "\033[0m");
        }
    }

    @Override
    public void printMoves(RaceTrack raceTrack, List<Player> players, List<Move> moves) {
        System.out.println("Here are the moves you can perform:");
        int moveIdentifier = 0;
        for (int y = 0; y < raceTrack.getLength(); y++) {
            for (int x = 0; x < raceTrack.getWidth(); x++) {
                Position position = new Position(x, y);
                if (isPositionAPossibleDestination(position, moves))
                    System.out.print(moveIdentifier++);
                else if (!printPlayer(getPlayerAtPosition(position, players)))
                    System.out.print(getCharForComponent(raceTrack.getComponentAt(x, y)));
            }
            System.out.println();
        }
    }

    private boolean isPositionAPossibleDestination(Position position, List<Move> moves) {
        for (Move move : moves)
            if (position.equals(move.position()))
                return true;
        return false;
    }

    @Override
    public void printWinMessageFor(Player player) {
        System.out.println("Player " + player.getPlayerCarColour() + " has crossed the finish line.");
        System.out.println("Player " + player.getPlayerCarColour() + " WINS!!!");
    }

    @Override
    public void printEliminationMessageFor(Player player) {
        System.out.println("Player " + player.getPlayerCarColour() + " has no more moves to perform.");
        System.out.println("Player " + player.getPlayerCarColour() + " LOSES!!!");
    }
}
