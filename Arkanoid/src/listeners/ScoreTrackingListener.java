// Tamar Rosenzweig
package listeners;

import sprites.Block;
import sprites.Ball;

/**
 * The ScoreTrackingListener class is responsible for tracking the score in the game.
 * It implements the HitListener interface to update the score when blocks are hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener.
     *
     * @param scoreCounter the Counter object used to keep track of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}