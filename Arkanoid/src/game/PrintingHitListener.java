package game;
import sprites.Block;
import sprites.Ball;
import listeners.HitListener;
public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
        hitter.setColor(beingHit.getRect().getColor());
    }
}
