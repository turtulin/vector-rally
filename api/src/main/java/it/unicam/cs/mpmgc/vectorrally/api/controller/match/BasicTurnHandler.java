package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BasicTurnHandler implements TurnHandler {
    private final Queue<Player> activePlayers;
    private int turnCounter = 0;
    private int playerCounter = 0;

    public BasicTurnHandler(List<Player> players) {
        this.activePlayers = new LinkedList<>(players);
    }

    @Override
    public void startTurn() {
        if(playerCounter++ >= activePlayers.size()) ++turnCounter;
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
    public int getPlayerCounter() {
        return playerCounter;
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
