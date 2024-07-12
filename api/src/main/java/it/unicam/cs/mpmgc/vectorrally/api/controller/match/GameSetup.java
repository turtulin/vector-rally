package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategyDifficulty;

import java.io.IOException;
import java.util.List;

/**
 * This interface defines methods for setting up the game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameSetup {
    RaceTrack selectTrack() throws Exception;
    List<Player> configurePlayers(int maxPlayers, List<Player> existingPlayers);
    BotStrategyDifficulty chooseBotStrategy(CarColour carColour) throws IOException;
}
