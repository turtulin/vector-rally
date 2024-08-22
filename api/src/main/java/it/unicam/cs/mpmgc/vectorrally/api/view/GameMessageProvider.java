package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * Provides messages for the game, implementing the MessageProvider interface.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public final class GameMessageProvider {
    public String getTurnMessage(int turn, Player player) {
        return "TURN " + turn + " for player " + player.getName();
    }

    public String getWinMessage(Player player) {
        return "Player " + player.getName() + " has crossed the finish line";
    }

    public String getEliminationMessage(Player player) {
        return "Player " + player.getName() + " has no more moves to perform\n" +
                "Player " + player.getName() + " LOSES!!!";
    }

    public String getInvalidChoiceMessage() {
        return "Invalid choice.";
    }

    public String getAskIfPlayerKnowsRulesMessage() {
        return "Do you know the rules of the game? (yes/no)";
    }

    public String getRuleTypeChoiceMessage() {
        return "Choose the rule type:";
    }

    public String getTrackChoiceMessage() {
        return "Choose a track from the available tracks:";
    }

    public String getMoveChoiceMessage() {
        return "Choose a move:";
    }

    public String getAskToPlayAgainMessage() {
        return "Do you want to play again? (yes/no)";
    }

    public String getAskIfSatisfiedWithConfigurationMessage() {
        return "Are you satisfied with the current configuration? (yes/no)";
    }

    public String getAskToChooseForEachBotMessage() {
        return "Do you want to choose the strategy for each bot? (yes/no)";
    }

    public String getCarColourChoiceMessage() {
        return "Choose a car color:";
    }

    public String getAskNumberOfHumanPlayersMessage(int maxPlayers) {
        return "Enter the number of human players(max " + maxPlayers + "):";
    }

    public String getChooseEachBotStrategyDifficultyMessage(CarColour carColour) {
        return "Choose the " + carColour + " bot strategy difficulty:";
    }

    public String getChooseAllBotStrategyDifficultyMessage() {
        return "Choose the bot strategy difficulty:";
    }

    public String getChooseStartingPositionMessage(CarColour carColour) {
        return carColour + ", choose your starting position:";
    }

    public String getNextTurnMessage() {
        return "[Enter] for next turn";
    }

    public String getGameRules() {
        return """
        \u001B[0m
        \u001B[36mHere are the rules of the game:\u001B[0m
        
        \u001B[34m1. Objective:\u001B[0m
           The objective of the game is to be the first player to reach the finish line.
        
        \u001B[34m2. Movement:\u001B[0m
           - Players take turns to move their cars on the race track.
           - Each car can move to adjacent positions based on their current acceleration.
           - The possible moves are determined using either the four or eight neighbors rule,\s
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
    }

    public String getCongratulationsMessage() {
        return """
                  \u001B[33m\u001B[1m
                  ______   ______   .__   __.   _______ .______          ___   .___________. __    __   __          ___   .___________. __    ______   .__   __.      _______.
                 /      | /  __  \\  |  \\ |  |  /  _____||   _  \\        /   \\  |           ||  |  |  | |  |        /   \\  |           ||  |  /  __  \\  |  \\ |  |     /       |
                |  ,----'|  |  |  | |   \\|  | |  |  __  |  |_)  |      /  ^  \\ `---|  |----`|  |  |  | |  |       /  ^  \\ `---|  |----`|  | |  |  |  | |   \\|  |    |   (----`
                |  |     |  |  |  | |  . `  | |  | |_ | |      /      /  /_\\  \\    |  |     |  |  |  | |  |      /  /_\\  \\    |  |     |  | |  |  |  | |  . `  |     \\   \\  \s
                |  `----.|  `--'  | |  |\\   | |  |__| | |  |\\  \\----./  _____  \\   |  |     |  `--'  | |  `----./  _____  \\   |  |     |  | |  `--'  | |  |\\   | .----)   | \s
                 \\______| \\______/  |__| \\__|  \\______| | _| `._____/__/     \\__\\  |__|      \\______/  |_______/__/     \\__\\  |__|     |__|  \\______/  |__| \\__| |_______/  \s
                 \u001B[0m""";
    }

    public String getGameOverMessage() {
        return """
                \u001B[31m\u001B[1m
                  
                  ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███\s
                 ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒
                ▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒
                ░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄\s
                ░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒
                 ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░
                  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░
                ░ ░   ░   ░   ▒   ░      ░      ░      ░ ░ ░ ▒       ░░     ░     ░░   ░
                      ░       ░  ░       ░      ░  ░       ░ ░        ░     ░  ░   ░   \s
                                                                     ░                \s
                \u001B[0m""";
    }

    public String getWelcomeMessage() {
        return """
                \u001B[32m\u001B[1m
                Welcome to\s
                ░█░█░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░░░█▀▄░█▀█░█░░░█░░░█░█
                ░▀▄▀░█▀▀░█░░░░█░░█░█░█▀▄░░░█▀▄░█▀█░█░░░█░░░░█░
                ░░▀░░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░░░▀░▀░▀░▀░▀▀▀░▀▀▀░░▀░
                \u001B[0m""";
    }
}
