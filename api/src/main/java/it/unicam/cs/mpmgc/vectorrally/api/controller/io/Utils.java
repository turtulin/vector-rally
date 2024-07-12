package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public interface Utils {
    static void printDisqualificationWarning(Player player) {
        String colorCode = getCarColorCode(player.getPlayerCarColour());
        System.out.println("Player " + colorCode + "P" + "\033[0m" + ", if you do not cross the line in the next turns you will be disqualified!!!");
    }

    static void printDisqualificationMessage(Player player) {
        String colorCode = getCarColorCode(player.getPlayerCarColour());
        System.out.println("Player " + colorCode + "P" + "\033[0m" + " has been disqualified.");
    }

    static void printTurnMessage(int turn, Player player) {
        String playerColorCode = getCarColorCode(player.getPlayerCarColour());
        System.out.println("TURN " + turn + " for player " + playerColorCode + "P" + "\033[0m");
    }

    static void printMoves(RaceTrack raceTrack, List<Player> players, List<Position> possibleDestinations) {
        System.out.println("Here are the moves you can perform:");
        for (int y = 0; y < raceTrack.getLength(); y++) {
            for (int x = 0; x < raceTrack.getWidth(); x++) {
                Position position = new Position(x, y);
                if (possibleDestinations.contains(position)) {
                    System.out.print("*"); // Mark possible destinations
                } else if (!printPlayer(position, players)) {
                    System.out.print(raceTrack.getComponentAt(x, y).getSymbol());
                }
            }
            System.out.println();
        }
    }

    private static boolean printPlayer(Position position, List<Player> players) {
        for (Player player : players) {
            if (player.getPosition().equals(position)) {
                String code = getCarColorCode(player.getPlayerCarColour());
                String reset = "\033[0m";
                String toPrint = player instanceof BotPlayer ? code + 'B' + reset : code + 'P' + reset;
                System.out.print(toPrint);
                return true;
            }
        }
        return false;
    }

    private static String getCarColorCode(CarColour colour) {
        return switch (colour) {
            case RED -> "\033[31m";
            case ORANGE -> "\033[38;5;208m";
            case YELLOW -> "\033[33m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            case PURPLE -> "\033[35m";
            case WHITE -> "\033[37m";
            case PINK -> "\033[38;5;205m";
            case GREY -> "\033[38;5;240m";
            case BROWN -> "\033[38;5;94m";
        };
    }

    static void printWinMessage(Player player) {
        System.out.println("Player " + player.getPlayerCarColour() + " has crossed the finish line");
        System.out.println("Player " + player.getPlayerCarColour() + " WINS!!!");
    }

    static void printEliminationMessage(Player player) {
        System.out.println("Player " + player.getPlayerCarColour() + " has no more moves to perform");
        System.out.println("Player " + player.getPlayerCarColour() + " LOSES!!!");
    }

    static void printErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    static void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        for (int y = 0; y < raceTrack.getLength(); y++) {
            for (int x = 0; x < raceTrack.getWidth(); x++) {
                Position position = new Position(x, y);
                if (!printPlayer(position, players)) {
                    System.out.print(raceTrack.getComponentAt(y, x).getSymbol());
                }
            }
            System.out.println();
        }
    }
}
