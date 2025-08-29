// Tamar Rosenzweig
package sprites;

import biuoop.DrawSurface;
import game.Game;
import listeners.Counter;

import java.awt.Color;

/**
 * The ScoreIndicator class is responsible for displaying the current score on the screen.
 * It implements the Sprite interface to be drawable and updateable within the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;

    /**
     * Constructs a ScoreIndicator with the given score counter.
     *
     * @param currentScore the Counter object used to keep track of the current score
     */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface on which the score will be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        //white.addToGame(this);
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 30);
        String curScore = "Score: " + this.currentScore.getValue();
        d.setColor(Color.BLACK);
        d.drawText(350, 22, curScore, 20);
    }

    /**
     * Notifies the sprite that time has passed.
     * This method is called once per frame.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds this ScoreIndicator to the given game.
     *
     * @param g the Game to which this ScoreIndicator will be added
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}