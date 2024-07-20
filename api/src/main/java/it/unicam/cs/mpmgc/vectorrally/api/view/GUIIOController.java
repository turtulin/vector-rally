package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GUIIOController implements GUIIController{
    @Override
    public List<String> findTrack() throws Exception {
        String directoryPath = IOController.checkRootPath();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        List<String> trackFiles = new ArrayList<>();
        for (File file : files) {
            trackFiles.add(file.getName());
        }
        return trackFiles;
    }

    @Override
    public boolean askToPlayAnotherMatch() {
        return false;
    }

    @Override
    public int chooseMove(List<Move> possibleMoves) {
        return 0;
    }

    @Override
    public Position chooseStartingPosition(Player player, List<Position> availablePositions) {
        return null;
    }

}
