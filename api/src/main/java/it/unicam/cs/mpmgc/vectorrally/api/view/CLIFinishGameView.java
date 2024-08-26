package it.unicam.cs.mpmgc.vectorrally.api.view;

/**
 * This class implements the {@link FinishGameView} interface to provide a command-line interface (CLI)
 * for handling the end-of-game interactions. This class facilitates the decision-making process for players regarding whether
 * they want to play another match or exit the game, using text-based prompts and inputs.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
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
