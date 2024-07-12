package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategyDifficulty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalIOController implements IOController {
    private final Scanner scanner;

    public TerminalIOController() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Vector Rally!");
    }

    @Override
    public boolean askIfPlayerKnowsRules() {
        System.out.println("Do you know the rules of the game? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes");
    }

    @Override
    public void displayGameRules() {
        System.out.println("Here are the rules of the game...");
        // TODO: Display the rules of the game
    }

    @Override
    public int chooseRuleType() {
        System.out.println("Choose the rule type:");
        System.out.println("1. Four Neighbors Rule");
        System.out.println("2. Eight Neighbors Rule");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    @Override
    public String chooseTrack() throws IOException {
        String directoryPath = "api/src/main/resources"; // Directory where track files are stored
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Track directory not found or is not a directory.");
            return null;
        }
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No track files found in the directory.");
            return null;
        }
        List<String> trackFiles = new ArrayList<>();
        for (File file : files) {
            trackFiles.add(file.getName());
        }
        System.out.println("Choose a track from the available tracks:");
        for (int i = 0; i < trackFiles.size(); i++) {
            System.out.println((i + 1) + ". " + trackFiles.get(i));
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        if (choice < 1 || choice > trackFiles.size()) {
            System.out.println("Invalid choice.");
            return null;
        }
        return directoryPath + "/" + trackFiles.get(choice - 1);
    }

    @Override
    public int askNumberOfHumanPlayers(int maxPlayers) {
        System.out.println("Enter the number of human players (max " + maxPlayers + "):");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        return numPlayers;
    }

    @Override
    public CarColour chooseCarColor(List<CarColour> availableColors) {
        System.out.println("Choose your car color:");
        for (int i = 0; i < availableColors.size(); i++) {
            System.out.println((i + 1) + ". " + availableColors.get(i));
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return availableColors.get(choice - 1);
    }

    @Override
    public BotStrategyDifficulty chooseBotStrategyDifficulty() {
        System.out.println("Choose the bot strategy difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return switch (choice) {
            case 1 -> BotStrategyDifficulty.EASY;
            case 2 -> BotStrategyDifficulty.MEDIUM;
            case 3 -> BotStrategyDifficulty.HARD;
            default -> throw new IllegalArgumentException("Invalid choice");
        };
    }

    @Override
    public boolean askIfSatisfiedWithConfiguration() {
        System.out.println("Are you satisfied with the current configuration? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes");
    }

    @Override
    public int chooseMove(List<Move> possibleMoves) {
        System.out.println("Choose your move:");
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.println((i + 1) + ". " + possibleMoves.get(i));
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        return choice - 1;
    }

    @Override
    public boolean askToPlayAnotherMatch() {
        System.out.println("Do you want to play another match? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes");
    }

    @Override
    public boolean sameConfiguration() {
        System.out.println("Do you want to use the same configuration? (yes/no)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    @Override
    public void displayEndMatchMessage() {
        System.out.println("The match has ended. Thank you for playing!");
    }

    @Override
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}
