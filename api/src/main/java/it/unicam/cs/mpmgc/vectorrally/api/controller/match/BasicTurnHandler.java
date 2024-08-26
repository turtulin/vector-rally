package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class implements the {@link TurnHandler} interface to manage the turns in a basic, round-robin style game.
 * The {@code BasicTurnHandler} class is responsible for cycling through the list of active players,
 * keeping track of the current turn, and handling player removal when necessary.
 * <p>
 * The class uses a {@link Queue} to maintain the order of players, allowing it to easily move to the next player
 * at the end of each turn. When a player's turn is completed, they are moved to the back of the queue.
 * The class also tracks the number of turns taken and the number of players still active in the game.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BasicTurnHandler implements TurnHandler {
    private final Queue<Player> activePlayers;
    private int turnCounter = 1;
    private int playerCounter = 0;

    public BasicTurnHandler(List<Player> players) {
        this.activePlayers = new LinkedList<>(players);
    }

    @Override
    public void startTurn() {
        if (playerCounter++ >= activePlayers.size())
        {
            turnCounter++;
            playerCounter = 0;
        }
    }

    @Override
    public void endTurn() {
        activePlayers.offer(activePlayers.poll());
    }

    @Override
    public Player getCurrentPlayer() {
        return activePlayers.peek();
    }

    @Override
    public int getTurnCounter() {
        return turnCounter;
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(activePlayers);
    }

    @Override
    public void removePlayer(Player player) {
        player.setRacing(false);
        activePlayers.remove(player);
    }
}
