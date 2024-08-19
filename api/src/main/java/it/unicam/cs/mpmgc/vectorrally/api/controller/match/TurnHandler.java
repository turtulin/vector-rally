package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

public interface TurnHandler {
    void startTurn();
    void endTurn();
    Player getCurrentPlayer();
    int getTurnCounter();
    int getPlayerCounter();
    List<Player> getPlayers();
    void removePlayer(Player player);
}
