package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.controller.builders.TrackPathBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Implements the IOController interface, providing methods to handle
 * input and output operations for the terminal-based game interface.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class TerminalIOController implements IOController {
    private final Scanner scanner;
    private final GameMessageProvider messageProvider = new GameMessageProvider();

    public TerminalIOController() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayWelcome() {
        System.out.println(messageProvider.getWelcomeMessage());
        System.out.println(messageProvider.getAskIfPlayerKnowsRulesMessage());
    }

    @Override
    public boolean getAskIfPlayerIgnoresRules() {
        return !getBooleanInput();
    }

    @Override
    public void displayGameRules() {
        System.out.println(messageProvider.getGameRules());
    }

    @Override
    public void displayShiftRuleType() {
        System.out.println(messageProvider.getRuleTypeChoiceMessage());
        System.out.println("1. Four Neighbors Rule");
        System.out.println("2. Eight Neighbors Rule");
    }

    @Override
    public NeighborsGenerator getRuleType() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 1 && choice != 2) {
            System.out.println(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice == 1 ? new FourNeighborsGenerator() : new EightNeighborsGenerator();
    }

    @Override
    public void displayTracks(List<String> trackFiles) {
        System.out.println(messageProvider.getTrackChoiceMessage());
        IntStream.range(0, trackFiles.size()).mapToObj(i -> (i + 1) + ". " + trackFiles.get(i)).forEach(System.out::println);
    }

    @Override
    public String getTrack(List<String> trackFiles) {
        String directoryPath = TrackPathBuilder.checkRootPath();
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > trackFiles.size()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return directoryPath + "/" + trackFiles.get(choice - 1);
    }

    @Override
    public void displayChooseNumHumanPlayers(int maxPlayers) {
        System.out.println(messageProvider.getAskNumberOfHumanPlayersMessage(maxPlayers));
    }

    @Override
    public int getNumberOfHumanPlayers(int maxPlayers) {
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        while (numPlayers < 0 || numPlayers > maxPlayers) {
            numPlayers = scanner.nextInt();
            scanner.nextLine();
        }
        return numPlayers;
    }

    @Override
    public void displayAskIfPlayerWantsToPlayAnotherMatch() {
        System.out.println(messageProvider.getAskToPlayAgainMessage());
    }

    @Override
    public boolean getAskToPlayAnotherMatch() {
        return getBooleanInput();
    }

    @Override
    public void displayMoves(int numMoves) {
        System.out.println(messageProvider.getMoveChoiceMessage());
        for (int i = 0; i < numMoves; i++) System.out.println((i + 1) + ".");
    }

    @Override
    public Move getChosenMove(List<Move> possibleMoves) {
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > possibleMoves.size()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return possibleMoves.get(choice - 1);
    }

    @Override
    public void printRaceTrack(Track raceTrack, List<Player> players, List<Coordinates> destinations) {
        for (int x = 0; x < raceTrack.getLength(); x++) {
            for (int y = 0; y < raceTrack.getWidth(); y++) {
                Coordinates position = new Position(x, y);
                if (!printPlayer(position, players)) {
                    if (destinations != null && destinations.contains(position)) {
                        int index = destinations.indexOf(position) + 1;
                        System.out.print(index);
                    } else System.out.print(raceTrack.getComponentAt(x, y).getSymbol());
                }
            }
            System.out.println();
        }
    }

    @Override
    public void displayGoToNextTurn() {
        System.out.println(messageProvider.getNextTurnMessage());
    }

    @Override
    public void getGoToNextTurn() {
        scanner.nextLine();
    }

    @Override
    public void displayGameOver() {
        System.out.println(messageProvider.getGameOverMessage());
    }

    @Override
    public void displayTurn(Player winner, int counter) {
        System.out.println(messageProvider.getTurnMessage(counter, winner));
    }

    @Override
    public void displayElimination(Player player) {
        System.out.println(messageProvider.getEliminationMessage(player));
    }

    @Override
    public void displayWinningMessage(Player winner) {
        System.out.println(messageProvider.getWinMessage(winner));
        System.out.println(messageProvider.getCongratulationsMessage());
    }

    @Override
    public void displayBotStrategyDifficulty() {
        System.out.println(messageProvider.getChooseBotStrategyDifficultyMessage());
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
    }

    @Override
    public BotStrategy getBotsStrategyDifficulty() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return switch (choice) {
            case 2 -> BotStrategy.MEDIUM;
            case 3 -> BotStrategy.HARD;
            default -> BotStrategy.EASY;
        };
    }

    private boolean printPlayer(Coordinates position, List<Player> players) {
        for (Player player : players) {
            if (player.getPosition().equals(position) &&
                    player.getPosition().getX() != 0 && player.getPosition().getY() != 0) {
                String code = getCarColorCode(player.getPlayerCarColour());
                String reset = "\033[0m";
                String toPrint = player instanceof BotPlayer ? code + 'B' + reset : code + 'P' + reset;
                System.out.print(toPrint);
                return true;
            }
        }
        return false;
    }

    private String getCarColorCode(CarColour colour) {
        return switch (colour) {
            case RED -> "\033[31m";
            case ORANGE -> "\033[38;5;208m";
            case YELLOW -> "\033[33m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            case PURPLE -> "\033[35m";
            case PINK -> "\033[38;5;205m";
            case CYAN -> "\033[36m";
            case BROWN -> "\033[38;5;94m";
        };
    }

    private boolean getBooleanInput() {
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            System.out.println(messageProvider.getInvalidChoiceMessage());
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }
}
