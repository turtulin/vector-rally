package it.unicam.cs.mpmgc.vectorrally.api.view;

public class CLIFinishGameView implements FinishGameView {
    IOController ioController;
    public CLIFinishGameView(IOController ioController) {
        this.ioController = ioController;
    }

    @Override
    public boolean playAnotherMatch() {
        return ioController.askToPlayAnotherMatch();
    }
}
