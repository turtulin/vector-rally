package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * Provides utility methods for printing the racetrack and player positions to the terminal.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class TerminalUtils implements CLIUtils {

    /**
     * Prints the racetrack along with the positions of the players.
     *
     * @param raceTrack the racetrack to be printed
     * @param players the list of players on the racetrack
     */
    public void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        printRaceTrack(raceTrack, players, null);
    }

    /**
     * Prints the racetrack along with the positions of the players and destinations.
     *
     * @param raceTrack the racetrack to be printed
     * @param players the list of players on the racetrack
     * @param destinations the list of destination positions to be printed
     */
    public void printRaceTrack(RaceTrack raceTrack, List<Player> players, List<Position> destinations) {
        for (int x = 0; x < raceTrack.getLength(); x++) {
            for (int y = 0; y < raceTrack.getWidth(); y++) {
                Position position = new Position(x, y);
                if (!printPlayer(position, players)) {
                    if (destinations != null && destinations.contains(position)) {
                        int index = destinations.indexOf(position) + 1;
                        Output.printMessage(String.valueOf(index));
                    } else {
                        Output.printMessage(String.valueOf(raceTrack.getComponentAt(x, y).getSymbol()));
                    }
                }
            }
            Output.printlnMessage("");
        }
    }

    /**
     * Checks if a player is at the specified position and prints the player character if found.
     *
     * @param position the position to check for a player
     * @param players the list of players to check
     * @return true if a player is found at the position, false otherwise
     */
    private boolean printPlayer(Position position, List<Player> players) {
        for (Player player : players) {
            if (player.getPosition().equals(position) &&
                    player.getPosition().getX() != 0 && player.getPosition().getY() != 0) {
                String code = getCarColorCode(player.getPlayerCarColour());
                String reset = "\033[0m";
                String toPrint = player instanceof BotPlayer ? code + 'B' + reset : code + 'P' + reset;
                Output.printMessage(toPrint);
                return true;
            }
        }
        return false;
    }

}
