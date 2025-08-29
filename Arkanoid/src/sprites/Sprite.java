// Tamar Rosenzweig
package sprites;
import game.Game;
import biuoop.DrawSurface;

/**
 * The Sprite interface represents an object that can be drawn to the screen and notified when time has passed.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given drawing surface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, allowing it to update its state if necessary.
     */
    void timePassed();

    /**
     * Adds this sprite object to the specified game.
     *
     * @param g the game to add this sprite object to
     */
    void addToGame(Game g);
}