package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;
import java.util.Scanner;

public class TerminalIOController implements IOController {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        TerminalUtils.printRaceTrack(raceTrack, players);
    }

    @Override
    public int chooseMoveToPerform(List<Move> moves) {
        System.out.println("Choose a move:");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println(i + ": Move to " + moves.get(i).position());
        }
        return scanner.nextInt();
    }

    @Override
    public void printWinMessage(Player player) {
        TerminalUtils.printWinMessage(player);
    }

    @Override
    public void printInvalidMoveMessage(Player player) {
        TerminalUtils.printInvalidMoveMessage(player);
    }

    @Override
    public void printGameOver() {
        TerminalUtils.printGameOver();
    }

    @Override
    public void printMessage(String message) {
        TerminalUtils.printMessage(message);
    }

    @Override
    public String chooseTrack() {
        System.out.println("Choose a track:");
        System.out.println("1: Track 1");
        System.out.println("2: Track 2");
        int choice = scanner.nextInt();
        return choice == 1 ? "track1.txt" : "track2.txt";
    }

    @Override
    public int getNumberOfHumanPlayers() {
        System.out.println("Enter the number of human players:");
        return scanner.nextInt();
    }

    @Override
    public int getNumberOfBotPlayers() {
        System.out.println("Enter the number of bot players:");
        return scanner.nextInt();
    }

    @Override
    public List<Player> setupPlayers(RaceTrack raceTrack, int numHumanPlayers, int numBotPlayers) {
        // Implementation for setting up players
        return null;
    }
}
