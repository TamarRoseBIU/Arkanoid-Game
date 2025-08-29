// Tamar Rosenzweig
package sprites;

import listeners.HitListener;
import geometry_primitives.Point;
import geometry_primitives.Line;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;
import collision_detection.Collidable;
import game.Game;
import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;
import listeners.HitNotifier;
import java.awt.Color;

/**
 * Represents a block object that implements the {@link Collidable} interface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private List<HitListener> hitListeners = new ArrayList<>();
    static final double HIT_THRESHOLD = 0.00001; // used to signal the ball is this distance away from hitting

    /**
     * Constructs a new Block object with the specified collision rectangle.
     *
     * @param rect the collision rectangle defining the block's shape
     */
    public Block(Rectangle rect) {
        this.rect = new Rectangle(rect);
    }

    /**
     * Returns the rectangle.
     *
     * @return the Rectangle object
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Sets the rectangle for this block.
     *
     * @param rect the new Rectangle object
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return a new rectangle representing the collision shape of the block
     */
    @Override
    public Rectangle getCollisionRectangle() { // check for copy constructor
        return new Rectangle(rect);
    }

    /**
     * Determines the new velocity of the block after a collision.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity of the block after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity);
        Point collCopy = currentVelocity.applyToPoint(new Point(collisionPoint));
        Line[] lines = new Line[4];
        lines[0] = new Line(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight()); // right
        lines[1] = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(), rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight()); // left
        lines[2] = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()); // top
        lines[3] = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight(),
                rect.getUpperLeft().getX() + rect.getWidth(),
                rect.getUpperLeft().getY() + rect.getHeight()); // bottom
        // if smaller than threshold, there's a hit
        if (Math.abs(collisionPoint.getY() - lines[2].start().getY()) <= HIT_THRESHOLD
                || Math.abs(collisionPoint.getY() - lines[3].start().getY()) <= HIT_THRESHOLD) {
            newVelocity.setDy(newVelocity.getDy() * -1);
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
            return newVelocity;
            // if a collision happened on the y coordinates, change only the y -
            // used to treat edge hits, in which case only the y direction will change
        }
        if (Math.abs(collisionPoint.getX() - lines[0].start().getX()) <= HIT_THRESHOLD
                || Math.abs(collisionPoint.getX() - lines[1].start().getX()) <= HIT_THRESHOLD) {
            newVelocity.setDx(newVelocity.getDx() * -1);
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return newVelocity;
    }

    /**
     * method drawOn draws the ball on the given DrawSurface.
     *
     * @param surface is the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.rect.getColor());
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Notifies the sprite that time has passed, allowing it to update its state if necessary.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds this sprite to the specified game.
     *
     * @param g the game to add this sprite to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the color of the specified Ball object matches the color of this object's rectangle.
     *
     * @param ball the Ball object whose color is to be matched
     * @return true if the color of the Ball matches the color of the rectangle, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return (ball.getColor()).equals(rect.getColor());
    }

    /**
     * Removes this object from the specified Game instance.
     *
     * @param game the Game instance from which this object will be removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Notifies all registered HitListeners about a hit event.
     *
     * @param hitter the Ball that is doing the hitting
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds the specified HitListener as a listener to hit events.
     *
     * @param hl the HitListener to be added
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes the specified HitListener from the list of listeners to hit events.
     *
     * @param hl the HitListener to be removed
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}