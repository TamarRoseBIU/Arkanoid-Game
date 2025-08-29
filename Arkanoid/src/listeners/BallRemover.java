// Tamar Rosenzweig
package listeners;

import game.Game;
import sprites.Ball;
import sprites.Block;

/**
 * A BlockRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BlockRemover.
     *
     * @param game           the game instance from which blocks will be removed
     * @param remainingBalls a counter to keep track of the number of blocks that remain
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * This method removes the block from the game and updates the remaining block count.
     * It also removes this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that is being hit and should be removed
     * @param hitter   the ball that is hitting the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}