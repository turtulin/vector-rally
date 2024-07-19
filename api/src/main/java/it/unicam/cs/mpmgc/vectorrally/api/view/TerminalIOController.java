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

public class TerminalIOController implements IOController {
    private final Scanner scanner;

    public TerminalIOController() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean askIfPlayerKnowsRules() {
        Utils.printMessage("Do you know the game rules? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            Utils.printMessage("Invalid choice.");
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }

    @Override
    public int chooseRuleType() {
        Utils.printMessage("Choose the rule type:");
        Utils.printMessage("1. Four Neighbors Rule");
        Utils.printMessage("2. Eight Neighbors Rule");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 1 && choice != 2) {
            Utils.printMessage("Invalid choice.");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    @Override
    public List<String> findTrack() {
        String directoryPath = IOController.checkRootPath();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (!doesDirectoryExist(directory) || !doFilesExist(files)) {
            Utils.printMessage("Invalid choice.");
            return null;
        }
        List<String> trackFiles = new ArrayList<>();
        for (File file : files) {
            trackFiles.add(file.getName());
        }
        return trackFiles;
    }

    @Override
    public String pickTrack(List<String> trackFiles){
        String directoryPath = IOController.checkRootPath();
        int choice = chooseRaceTrack(trackFiles);
        return directoryPath + "/" + trackFiles.get(choice - 1);
    }

    private boolean doesDirectoryExist(File directory) {
        return directory.exists() && directory.isDirectory();
    }

    private boolean doFilesExist(File[] files) {
        return files != null && files.length > 0;
    }

    private int chooseRaceTrack (List<String> trackFiles) {
        Utils.printMessage("Choose a track from the available tracks:");
        IntStream.range(0, trackFiles.size()).mapToObj(i -> (i + 1) + ". " + trackFiles.get(i)).forEach(Utils::printMessage);
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > trackFiles.size()) {
            Utils.printMessage("Invalid choice.");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    @Override
    public int askNumberOfHumanPlayers(int maxPlayers) {
        Utils.printMessage("Enter the number of human players (max " + maxPlayers + "):");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        while (numPlayers < 0 || numPlayers > maxPlayers) {
            Utils.printMessage("Invalid choice.");
            numPlayers = scanner.nextInt();
            scanner.nextLine();
        }
        return numPlayers;
    }

    @Override
    public CarColour chooseCarColor(List<CarColour> availableColors) {
        Utils.printMessage("Choose your car color:");
        for (int i = 0; i < availableColors.size(); i++) {
            Utils.printMessage((i + 1) + ". " + availableColors.get(i));
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > availableColors.size()) {
            Utils.printMessage("Invalid choice.");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return availableColors.get(choice - 1);
    }


    public boolean askToChooseForEachBot() {
        Utils.printMessage("Do you want to choose the strategy for each bot? (yes/no)");
        String response = scanner.nextLine();
        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            Utils.printMessage("Invalid choice.");
            response = scanner.nextLine();
        }
        return response.equalsIgnoreCase("yes");
    }

    @Override
    public BotStrategy chooseEachBotStrategyDifficulty(CarColour carColour) {
        return getBotStrategyDifficulty("Choose the " + carColour + " bot strategy difficulty:");
    }

    @Override
    public BotStrategy chooseAllBotStrategyDifficulty() {
        return getBotStrategyDifficulty("Choose the difficulty for all bots:");
    }

    private BotStrategy getBotStrategyDifficulty(String message) {
        Utils.printMessage(message);
        Utils.printMessage("1. Easy");
        Utils.printMessage("2. Medium");
        Utils.printMessage("3. Hard");
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
        Utils.printRaceTrack(raceTrack, players);
        Utils.printMessage("Are you satisfied with the current configuration? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            Utils.printMessage("Invalid choice.");
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }

    @Override
    public int chooseMove(List<Move> possibleMoves) {
        Utils.printMessage("Choose your move:");
        for (int i = 0; i < possibleMoves.size(); i++) {
            Utils.printMessage((i + 1) + ".");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > possibleMoves.size()) {
            Utils.printMessage("Invalid choice.");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice - 1;
    }

    @Override
    public boolean askToPlayAnotherMatch() {
        Utils.printMessage("Do you want to play another match? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            Utils.printMessage("Invalid choice.");
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }


    @Override
    public void displayEndMatchMessage() {
        Utils.printMessage("The match has ended. Thank you for playing!");
    }

    @Override
    public Position chooseStartingPosition(Player player, List<Position> availablePositions) {
        Utils.printMessage(player.getPlayerCarColour() + ", choose your starting position:");
        for (int i = 0; i < availablePositions.size(); i++) {
            Position pos = availablePositions.get(i);
            Utils.printMessage((i + 1) + ". (" + pos.getX() + ", " + pos.getY() + ")");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 1 || choice > availablePositions.size()) {
            Utils.printMessage("Invalid choice.");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return availablePositions.get(choice - 1);
    }

}