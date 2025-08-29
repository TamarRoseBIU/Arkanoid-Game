// Tamar Rosenzweig
package listeners;

import game.Game;
import sprites.Block;
import sprites.Ball;

/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover.
     *
     * @param game            the game instance from which blocks will be removed
     * @param remainingBlocks a counter to keep track of the number of blocks that remain
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * This method removes the block from the game and updates the remaining block count.
     * It also removes this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that is being hit and should be removed
     * @param hitter   the ball that is hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        hitter.setColor(beingHit.getRect().getColor());
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}