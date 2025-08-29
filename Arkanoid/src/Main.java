// Tamar Rosenzweig

import game.Game;

/**
 * The main class for running the game.
 */
public class Main {
    /**
     * The main method that initializes and runs the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}