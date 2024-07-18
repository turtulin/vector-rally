package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public interface Utils {

    static void printTurnMessage(int turn, Player player) {
        String playerColorCode = getCarColorCode(player.getPlayerCarColour());
        if (player instanceof HumanPlayer) System.out.println("TURN " + turn + " for player " + playerColorCode + "P" + "\033[0m");
        else System.out.println("TURN " + turn + " for player " + playerColorCode + "B" + "\033[0m");
    }

    private static boolean printPlayer(Position position, List<Player> players) {
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

    private static String getCarColorCode(CarColour colour) {
        return switch (colour) {
            case RED -> "\033[31m";
            case ORANGE -> "\033[38;5;208m";
            case YELLOW -> "\033[33m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            case PURPLE -> "\033[35m";
            case WHITE -> "\033[37m";
            case PINK -> "\033[38;5;205m";
            case GREY -> "\033[38;5;240m";
            case BROWN -> "\033[38;5;94m";
        };
    }

    static void printWinMessage(Player player) {
        System.out.println("Player " + player.getPlayerCarColour() + " has crossed the finish line");
        System.out.println("""
                  \u001B[33m\u001B[1m
                  ______   ______   .__   __.   _______ .______          ___   .___________. __    __   __          ___   .___________. __    ______   .__   __.      _______.
                 /      | /  __  \\  |  \\ |  |  /  _____||   _  \\        /   \\  |           ||  |  |  | |  |        /   \\  |           ||  |  /  __  \\  |  \\ |  |     /       |
                |  ,----'|  |  |  | |   \\|  | |  |  __  |  |_)  |      /  ^  \\ `---|  |----`|  |  |  | |  |       /  ^  \\ `---|  |----`|  | |  |  |  | |   \\|  |    |   (----`
                |  |     |  |  |  | |  . `  | |  | |_ | |      /      /  /_\\  \\    |  |     |  |  |  | |  |      /  /_\\  \\    |  |     |  | |  |  |  | |  . `  |     \\   \\   
                |  `----.|  `--'  | |  |\\   | |  |__| | |  |\\  \\----./  _____  \\   |  |     |  `--'  | |  `----./  _____  \\   |  |     |  | |  `--'  | |  |\\   | .----)   |  
                 \\______| \\______/  |__| \\__|  \\______| | _| `._____/__/     \\__\\  |__|      \\______/  |_______/__/     \\__\\  |__|     |__|  \\______/  |__| \\__| |_______/   
                 \u001B[0m""");
    }

    static void printEliminationMessage(Player player) {
        System.out.println("Player " + player.getPlayerCarColour() + " has no more moves to perform");
        System.out.println("Player " + player.getPlayerCarColour() + " LOSES!!!");
    }

    static void printErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }
    static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printRaceTrack(RaceTrack raceTrack, List<Player> players) {
        printRaceTrack(raceTrack, players, null);
    }

    public static void printRaceTrack(RaceTrack raceTrack, List<Player> players, List<Position> destinations) {
        for (int x = 0; x < raceTrack.getLength(); x++) {
            for (int y = 0; y < raceTrack.getWidth(); y++) {
                Position position = new Position(x, y);
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

    static void displayWelcomeMessage() {
        System.out.println("""
                \u001B[32m\u001B[1m
                Welcome to\s
                ░█░█░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░░░█▀▄░█▀█░█░░░█░░░█░█
                ░▀▄▀░█▀▀░█░░░░█░░█░█░█▀▄░░░█▀▄░█▀█░█░░░█░░░░█░
                ░░▀░░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░░░▀░▀░▀░▀░▀▀▀░▀▀▀░░▀░
                \u001B[0m""");
    }

    static void displayGameRules() {
        String rules = """        
        \u001B[0m
        \u001B[36mHere are the rules of the game:\u001B[0m
        
        \u001B[34m1. Objective:\u001B[0m
           The objective of the game is to be the first player to reach the finish line.
        
        \u001B[34m2. Movement:\u001B[0m
           - Players take turns to move their cars on the race track.
           - Each car can move to adjacent positions based on their current acceleration.
           - The possible moves are determined using either the four or eight neighbors rule, 
             depending on the game configuration.
        
        \u001B[34m3. Acceleration:\u001B[0m
           - Acceleration determines the change in position for the next move.
           - Players can choose to change their acceleration within the allowed limits during their turn.
        
        \u001B[34m4. Collisions:\u001B[0m
           - If a car passes through or lands on a wall, it crashes and is eliminated from the race.
           - If a car passes through or lands on another car, it crashes and is eliminated from the race.
        
        \u001B[34m5. Winning the Game:\u001B[0m
           - The first player to cross the finish line wins the game.
           - If all players crash before reaching the finish line, the game ends with no winner.
        
        \u001B[34m6. Game Setup:\u001B[0m
           - Players choose a race track to race on.
           - Each player selects a car color.
           - The number of players can be configured, and players can be either human or bots.
        
        \u001B[34m7. Bot Strategies:\u001B[0m
           - Bots can have different difficulty levels: Easy, Medium, and Hard.
           - Easy bots make random moves.
           - Medium bots choose the longest move that does not lead to a crash.
           - Hard bots use the A* algorithm to determine the best move.
        
        \u001B[36mGood luck, and may the best driver win!\u001B[0m
            """;
        System.out.println(rules);
    }

    static void displayGameOver() {
        System.out.println("""
                \u001B[31m\u001B[1m
                  
                  ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███ 
                 ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒
                ▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒
                ░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄ 
                ░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒
                 ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░
                  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░
                ░ ░   ░   ░   ▒   ░      ░      ░      ░ ░ ░ ▒       ░░     ░     ░░   ░
                      ░       ░  ░       ░      ░  ░       ░ ░        ░     ░  ░   ░    
                                                                     ░                 
                \u001B[0m""");
    }
}
