package it.unicam.cs.mpmgc.vectorrally.api.view;

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

    /**
     * Constructs a TerminalIOController with a new Scanner for input.
     */
    public TerminalIOController() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayWelcomeAndRules() {
        System.out.println(messageProvider.getWelcomeMessage());
        if (!askIfPlayerKnowsRules()) System.out.println(messageProvider.getGameRules());
    }
    
    @Override
    public boolean askIfPlayerKnowsRules() {
        System.out.println(messageProvider.getAskIfPlayerKnowsRulesMessage());
        return getBooleanInput();
    }

    @Override
    public int chooseRuleType() {
        System.out.println(messageProvider.getRuleTypeChoiceMessage());
        System.out.println("1. Four Neighbors Rule");
        System.out.println("2. Eight Neighbors Rule");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 1 && choice != 2) {
            System.out.println(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    @Override
    public String pickTrack(List<String> trackFiles) {
        String directoryPath = TrackPathController.checkRootPath();
        int choice = chooseRaceTrack(trackFiles);
        return directoryPath + "/" + trackFiles.get(choice - 1);
    }

    @Override
    public int askNumberOfHumanPlayers(int maxPlayers) {
        System.out.println(messageProvider.getAskNumberOfHumanPlayersMessage(maxPlayers));
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        while (numPlayers < 0 || numPlayers > maxPlayers) {
            System.out.println(messageProvider.getInvalidChoiceMessage());
            numPlayers = scanner.nextInt();
            scanner.nextLine();
        }
        return numPlayers;
    }

    @Override
    public BotStrategy chooseAllBotsStrategyDifficulty() {
        return getBotStrategyDifficulty(messageProvider.getChooseAllBotStrategyDifficultyMessage());
    }

    @Override
    public boolean askToPlayAnotherMatch() {
        System.out.println(messageProvider.getAskToPlayAgainMessage());
        return getBooleanInput();
    }

    @Override
    public void displayMoves(List<Coordinates> possibleDestinations) {
        System.out.println(messageProvider.getMoveChoiceMessage());
        for (int i = 0; i < possibleDestinations.size(); i++) System.out.println((i + 1) + ".");
    }

    @Override
    public Move chooseMove(List<Move> possibleMoves) {
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > possibleMoves.size()) {
            System.out.println(messageProvider.getInvalidChoiceMessage());
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
                    } else {
                        System.out.print(raceTrack.getComponentAt(x, y).getSymbol());
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public NeighborsGenerator initializeShiftAlgorithm() {
        int ruleType = chooseRuleType();
        return ruleType == 1 ? new FourNeighborsGenerator() : new EightNeighborsGenerator();
    }

    @Override
    public void goToNextTurn() {
        System.out.println(messageProvider.getNextTurnMessage());
        scanner.nextLine();
    }

    @Override
    public void displayTracks(List<String> trackFiles) {
        System.out.println(messageProvider.getTrackChoiceMessage());
        IntStream.range(0, trackFiles.size()).mapToObj(i -> (i + 1) + ". " + trackFiles.get(i)).forEach(System.out::println);
    }

    private BotStrategy getBotStrategyDifficulty(String message) {
        System.out.println(message);
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return switch (choice) {
            case 2 -> BotStrategy.MEDIUM;
            case 3 -> BotStrategy.HARD;
            default -> BotStrategy.EASY;
        };
    }

    private int chooseRaceTrack (List<String> trackFiles) {
        displayTracks(trackFiles);
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > trackFiles.size()) {
            System.out.println(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
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
