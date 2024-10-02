# Vector Rally - Java Project

Vector Rally is a **turn-based car racing simulation game**. It can be played both through a **command-line interface (CLI)** and a **graphical user interface (GUI)** using **JavaFX**. The game allows players to control their cars on a track, using algorithms to determine valid moves and manage interactions between players and the track.

## Game Overview

- **Objective**: The first player to reach the finish line wins.
- **Gameplay**: Each turn, players move their cars based on their current position, acceleration, and track configuration. Players without valid moves are eliminated.
- **Track Representation**: The track is represented in `.txt` files, where different symbols represent the start line, finish line, roads, and obstacles.

## Technologies Used

- **Java**: Core development language
- **JavaFX**: For the graphical user interface
- **JUnit**: For unit testing
- **Gradle**: For project build and dependencies

## Running the Project

1. **Build the project**:
    ```bash
    gradle build
    ```
2. **Run the project**:
    - For GUI:
      ```bash
      gradle run
      ```
    - For terminal simulation:
      Modify the `build.gradle` to run `TerminalApp` instead of `GraphicalApp`.

For more detailed instructions and information, please refer to the **project report** (PDF) available in this repository.

---

