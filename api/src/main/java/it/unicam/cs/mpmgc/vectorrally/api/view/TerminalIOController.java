package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TerminalIOController implements CLIIOController {
    private final Scanner scanner;
    private final MessageProvider messageProvider = new GameMessageProvider();
    private final TerminalUtils utils = new TerminalUtils();

    public TerminalIOController() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayWelcomeAndRules() {
        Output.printlnMessage(messageProvider.getWelcomeMessage());
        if (!askIfPlayerKnowsRules()) Output.printlnMessage(messageProvider.getGameRules());
    }
    
    @Override
    public boolean askIfPlayerKnowsRules() {
        Output.printlnMessage(messageProvider.getAskIfPlayerKnowsRulesMessage());
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }

    @Override
    public int chooseRuleType() {
        Output.printlnMessage(messageProvider.getRuleTypeChoiceMessage());
        Output.printlnMessage("1. Four Neighbors Rule");
        Output.printlnMessage("2. Eight Neighbors Rule");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 1 && choice != 2) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    @Override
    public String pickTrack(List<String> trackFiles) {
        String directoryPath = IOController.checkRootPath();
        int choice = chooseRaceTrack(trackFiles);
        return directoryPath + "/" + trackFiles.get(choice - 1);
    }

    @Override
    public int askNumberOfHumanPlayers(int maxPlayers) {
        Output.printlnMessage(messageProvider.getAskNumberOfHumanPlayersMessage(maxPlayers));
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        while (numPlayers < 0 || numPlayers > maxPlayers) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            numPlayers = scanner.nextInt();
            scanner.nextLine();
        }
        return numPlayers;
    }

    @Override
    public CarColour chooseCarColor(List<CarColour> availableColors) {
        Output.printlnMessage(messageProvider.getCarColourChoiceMessage());
        for (int i = 0; i < availableColors.size(); i++) {
            Output.printlnMessage((i + 1) + ". " + availableColors.get(i));
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > availableColors.size()) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return availableColors.get(choice - 1);
    }

    @Override
    public boolean askToChooseForEachBot() {
        Output.printlnMessage(messageProvider.getAskToChooseForEachBotMessage());
        String response = scanner.nextLine();
        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            response = scanner.nextLine();
        }
        return response.equalsIgnoreCase("yes");
    }
    
    @Override
    public BotStrategy chooseEachBotStrategyDifficulty(CarColour carColour) {
        return getBotStrategyDifficulty(messageProvider.getChooseEachBotStrategyDifficultyMessage(carColour));
    }

    @Override
    public BotStrategy chooseAllBotsStrategyDifficulty() {
        return getBotStrategyDifficulty(messageProvider.getChooseAllBotStrategyDifficultyMessage());
    }

    private BotStrategy getBotStrategyDifficulty(String message) {
        Output.printlnMessage(message);
        Output.printlnMessage("1. Easy");
        Output.printlnMessage("2. Medium");
        Output.printlnMessage("3. Hard");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return switch (choice) {
            case 2 -> BotStrategy.MEDIUM;
            case 3 -> BotStrategy.HARD;
            default -> BotStrategy.EASY;
        };
    }

    @Override
    public boolean askIfSatisfiedWithConfiguration(RaceTrack raceTrack, List<Player> players) {
        utils.printRaceTrack(raceTrack, players);
        Output.printlnMessage(messageProvider.getAskIfSatisfiedWithConfigurationMessage());
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }

    @Override
    public void displayEndMatchMessage() {
        Output.printlnMessage(messageProvider.getEndMessage());
    }

    @Override
    public List<String> findTrack() throws Exception {
        String directoryPath = IOController.checkRootPath();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (!doesDirectoryExist(directory) || !doFilesExist(files)) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            return null;
        }
        List<String> trackFiles = new ArrayList<>();
        for (File file : files) {
            trackFiles.add(file.getName());
        }
        return trackFiles;
    }

    @Override
    public boolean askToPlayAnotherMatch() {
        Output.printlnMessage(messageProvider.getAskToPlayAgainMessage());
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }

    @Override
    public int chooseMove(List<Move> possibleMoves) {
        Output.printlnMessage(messageProvider.getMoveChoiceMessage());
        for (int i = 0; i < possibleMoves.size(); i++) {
            Output.printlnMessage((i + 1) + ".");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > possibleMoves.size()) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice - 1;
    }

    @Override
    public Position chooseStartingPosition(Player player, List<Position> availablePositions) {
        Output.printlnMessage(messageProvider.getChooseStartingPositionMessage(player.getPlayerCarColour()));
        for (int i = 0; i < availablePositions.size(); i++) {
            Position pos = availablePositions.get(i);
            Output.printlnMessage((i + 1) + ". (" + pos.getX() + ", " + pos.getY() + ")");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > availablePositions.size()) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return availablePositions.get(choice - 1);
    }

    private int chooseRaceTrack (List<String> trackFiles) {
        Output.printlnMessage(messageProvider.getTrackChoiceMessage());
        IntStream.range(0, trackFiles.size()).mapToObj(i -> (i + 1) + ". " + trackFiles.get(i)).forEach(Output::printlnMessage);
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > trackFiles.size()) {
            Output.printlnMessage(messageProvider.getInvalidChoiceMessage());
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    @Override
    public void waitForNextTurn() {
        Output.printlnMessage(messageProvider.getNextTurnMessage());
        scanner.nextLine();
    }

    @Override
    public void printRaceTrack(RaceTrack raceTrack, List<Player> players, List<Position> destinations) {
        utils.printRaceTrack(raceTrack, players, destinations);
    }

    @Override
    public void displayMessage(String message) {
        Output.printlnMessage(message);
    }
}
