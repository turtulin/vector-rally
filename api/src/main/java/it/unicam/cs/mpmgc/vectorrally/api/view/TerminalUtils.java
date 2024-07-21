package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public class TerminalUtils implements CLIUtils {

    public void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        printRaceTrack(raceTrack, players, null);
    }

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
