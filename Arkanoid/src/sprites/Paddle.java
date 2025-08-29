// Tamar Rosenzweig
package sprites;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;
import collision_detection.Collidable;
import game.Game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Represents a paddle in the game, which can be moved left and right using the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    static final double HIT_RIGHT_WALL = 780;
    static final double HIT_LEFT_WALL = 20;

    /**
     * Constructs a Paddle object with the specified keyboard sensor.
     *
     * @param object the KeyboardSensor used to control the paddle
     */
    public Paddle(KeyboardSensor object) {
        this.keyboard = object;
        this.paddle = new Block(new Rectangle(new Point(400, 560), 80, 20));
    }

    /**
     * Constructs a Paddle object by copying the specified Paddle object.
     *
     * @param other the Paddle object to copy
     */
    public Paddle(Paddle other) {
        if (other == null) {
            return;
        }
        this.keyboard = other.keyboard;
        this.paddle = new Block(new Rectangle(other.paddle.getRect().getUpperLeft(), other.paddle.getRect().getWidth(),
                other.paddle.getRect().getHeight()));
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        if (this.paddle.getRect().getUpperLeft().getX() <= HIT_LEFT_WALL) {
            this.paddle.setRect(new Rectangle(new Point(HIT_RIGHT_WALL - this.paddle.getRect().getWidth(),
                    this.paddle.getRect().getUpperLeft().getY()), this.paddle.getRect().getWidth(),
                    this.paddle.getRect().getHeight()));
        }
        Point newUpperLeft = new Point(this.paddle.getRect().getUpperLeft().getX() - 5,
                this.paddle.getRect().getUpperLeft().getY());
        // moves the x coordinate 5 units to the left
        this.paddle.getRect().setUpperLeft(newUpperLeft);
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        if (this.paddle.getRect().getUpperLeft().getX() + this.paddle.getRect().getWidth() >= HIT_RIGHT_WALL) {
            this.paddle.setRect(new Rectangle(new Point(HIT_LEFT_WALL, this.paddle.getRect().getUpperLeft().getY()),
                    this.paddle.getRect().getWidth(), this.paddle.getRect().getHeight()));
        }
        Point newUpperLeft = new Point(this.paddle.getRect().getUpperLeft().getX() + 5,
                this.paddle.getRect().getUpperLeft().getY());
        // moves the x coordinate 5 units to the right
        this.paddle.getRect().setUpperLeft(newUpperLeft);
    }

    /**
     * Notifies the paddle that time has passed, and it should update its state.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given drawing surface.
     *
     * @param d the drawing surface on which to draw the paddle
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(222, 49, 99));
        d.fillRectangle((int) this.paddle.getRect().getUpperLeft().getX(),
                (int) this.paddle.getRect().getUpperLeft().getY(), (int) this.paddle.getRect().getWidth(),
                (int) this.paddle.getRect().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getRect().getUpperLeft().getX(),
                (int) this.paddle.getRect().getUpperLeft().getY(), (int) this.paddle.getRect().getWidth(),
                (int) this.paddle.getRect().getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return paddle.getCollisionRectangle();
    }

    /**
     * Notifies the paddle of a collision and calculates the new velocity after the hit.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity before the hit
     * @return the new velocity after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // different reaction to hitting, based on region of hitting
        Velocity vel; // default value
        double regionLength = paddle.getRect().getWidth() / 5;
        int xVal = (int) (collisionPoint.getX() - paddle.getRect().getUpperLeft().getX());
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        // if the speed direction is negative. This measurement is lost when using the math.pow. ->
        if ((currentVelocity.getDx() < 0 && currentVelocity.getDy() > 0) || currentVelocity.getDx() > 0
                && currentVelocity.getDy() < 0) {
            speed = -1 * speed;
        }
        // the value of hitting point, relative to the paddle starting point
        if (xVal >= 0 && xVal <= regionLength) { // region 1
            vel = Velocity.fromAngleAndSpeed(-60, speed);
            vel.setDy(-1 * Math.abs(vel.getDy()));
            return vel;
        }
        if (xVal >= regionLength && xVal <= regionLength * 2) { // region 2
            vel = Velocity.fromAngleAndSpeed(330, speed);
            vel.setDy(-1 * Math.abs(vel.getDy()));
            return vel;
        }
        if (xVal >= regionLength * 2 && xVal <= regionLength * 3) { // region 3, middle section
            vel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            return vel;
        }
        if (xVal >= regionLength * 3 && xVal <= regionLength * 4) { // region 4
            vel = Velocity.fromAngleAndSpeed(30, speed);
            vel.setDy(-1 * Math.abs(vel.getDy()));
            return vel;
        }
        if (xVal >= regionLength * 4 && xVal <= regionLength * 5) { // region 5
            vel = Velocity.fromAngleAndSpeed(60, speed);
            vel.setDy(-1 * Math.abs(vel.getDy()));
            return vel;
        }
        vel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        return vel;
    }

    /**
     * Adds this paddle to the specified game.
     *
     * @param g the game to add this paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}