// Tamar Rosenzweig
package listeners;

import sprites.Block;
import sprites.Ball;

/**
 * The HitListener interface should be implemented by any class that wants to
 * be notified when a hit event occurs.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the Block that is being hit
     * @param hitter   the Ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}