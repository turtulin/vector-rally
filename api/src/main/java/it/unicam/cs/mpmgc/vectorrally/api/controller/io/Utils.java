package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public interface Utils {

    static void printTurnMessage(int turn, Player player) {
        String playerColorCode = getCarColorCode(player.getPlayerCarColour());
        System.out.println("TURN " + turn + " for player " + playerColorCode + "P" + "\033[0m");
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

    public static void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        printRaceTrack(raceTrack, players, null);
    }

    public static void printRaceTrack(RaceTrack raceTrack, List<Player> players, List<Position> destinations) {
        for (int x = 0; x < raceTrack.getLength(); x++) {
            for (int y = 0; y < raceTrack.getWidth(); y++) {
                Position position = new Position(x, y);
                if (!printPlayer(position, players)) {
                    if (destinations != null && destinations.contains(position)) {
                        int index = destinations.indexOf(position) + 1;
                        System.out.print(index);
                    } else {
                        System.out.print(raceTrack.getComponentAt(x, y).getSymbol());
                    }
                }
            }
            System.out.println();
        }
    }
}
