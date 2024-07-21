package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * Represents the result of the game setup process, containing the neighbors generator,
 * racetrack, and list of players.
 *
 * @param generator the neighbors generator used in the game
 * @param raceTrack the racetrack on which the game is played
 * @param players   the list of players participating in the game
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public record SetupResult(NeighborsGenerator generator, RaceTrack raceTrack, List<Player> players) {
}
