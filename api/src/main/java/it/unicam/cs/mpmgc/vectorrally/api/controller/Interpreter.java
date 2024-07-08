package it.unicam.cs.mpmgc.vectorrally.api.controller;

import it.unicam.cs.mpmgc.vectorrally.api.model.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.Shift;

public interface Interpreter {
    void interpretMove(Player player, Shift shift);
}
