// Tamar Rosenzweig

package sprites;

import geometry_primitives.Point;
import geometry_primitives.Line;
import geometry_primitives.Velocity;
import collision_detection.GameEnvironment;
import collision_detection.CollisionInfo;
import game.Game;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Class Ball represents a ball with size (radius), color, and location.
 */
public class Ball implements Sprite {
    // Balls have size (radius), color, and location (a Point)
    private int size;
    private Color color;
    private Point point;
    private Velocity velocity;
    private boolean insideRec; // whether the ball is within the lines of a rectangle.
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a Ball with a given center, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = new Point(center.getX(), center.getY());
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.insideRec = true;
    }

    /**
     * constructor Ball.
     *
     * @param center is the center of the ball
     * @param r      is the radius
     * @param color  is the color of the ball
     * @param v      is the velocity
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity v) {
        this.point = new Point(center.getX(), center.getY());
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(v.getDx(), v.getDy());
        this.insideRec = true;
    }

    /**
     * constructor Ball.
     *
     * @param x     is the x value of the center
     * @param y     is the y value of the center
     * @param r     is the radius
     * @param color is the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
        this.insideRec = true;
    }

    /**
     * copy constructor Ball.
     *
     * @param other is the ball to be copied
     */
    public Ball(Ball other) {
        if (other == null) {
            return;
        }
        this.point = new Point(other.getX(), other.getY());
        this.size = other.size;
        this.color = other.color;
        this.velocity = new Velocity(other.velocity.getDx(), other.velocity.getDy());
        this.insideRec = other.insideRec;
    }
    // accessors

    /**
     * method getPoint returns the center point.
     *
     * @return the center point
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * method getX returns the x value of the center.
     *
     * @return x value of center
     */
    public double getX() {
        return this.point.getX();
    }

    /**
     * method getY returns the x value of the center.
     *
     * @return y value of center
     */
    public double getY() {
        return this.point.getY();
    }

    /**
     * method getSize returns the size of the ball.
     *
     * @return the size of the ball
     */
    public int getSize() {
        return this.size;
    }

    /**
     * method getColor returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * method getColor returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * method getInsideRec returns whether the ball is within a rectangle.
     *
     * @return whether the ball is within the rectangle.
     */
    public boolean getInsideRec() {
        return insideRec;
    }

    /**
     * method setX changes the x value of the center.
     *
     * @param x is the value to change to
     */
    public void setX(double x) {
        this.point.setX(x);
    }

    /**
     * method setY changes the y value of the center.
     *
     * @param y is the value to change to
     */
    public void setY(double y) {
        this.point.setY(y);
    }

    /**
     * method setCenter changes the center of the ball.
     *
     * @param p is the value to change to
     */
    public void setPoint(Point p) {
        if (this.point == null) {
            this.point = new Point(p.getX(), p.getY());
            return;
        }
        this.point.setX(p.getX());
        this.point.setY(p.getY());
    }

    /**
     * method setSize changes the size of the ball.
     *
     * @param s is the value to change to
     */
    public void setSize(int s) {
        this.size = s;
    }

    /**
     * method setColor changes the color of the ball.
     *
     * @param c is the color to change to
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * method setVelocity changes the velocity of the ball.
     *
     * @param v is the velocity to change to
     */
    public void setVelocity(Velocity v) {
        if (this.velocity == null) {
            this.velocity = new Velocity(v.getDx(), v.getDy());
            return;
        }
        this.velocity.setDx(v.getDx());
        this.velocity.setDy(v.getDy());
    }

    /**
     * method setVelocity changes the velocity of the ball.
     *
     * @param dx is the dx value to change to.
     * @param dy is the dy value to change to
     */
    public void setVelocity(double dx, double dy) {
        if (this.velocity == null) {
            this.velocity = new Velocity(dx, dy);
            return;
        }
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * method setInsideRec changes the insideRec field of the ball.
     *
     * @param insideRec is the boolean value to change to.
     */
    public void setInsideRec(boolean insideRec) {
        this.insideRec = insideRec;
    }

    /**
     * method setInsideRec changes the insideRec field of the ball.
     *
     * @param ge is the boolean value to change to.
     */
    public void setGameEnvironment(GameEnvironment ge) {
        //setting a reference
        this.gameEnvironment = ge;
    }

    /**
     * method drawOn draws the ball on the given DrawSurface.
     *
     * @param surface is the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), size);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.getX(), (int) this.getY(), size);
    }

    /**
     * Creates a new line representing the trajectory of an object from its current point based on its velocity.
     *
     * @return a Line object representing the trajectory of the object, starting from the current point
     * and extending to the point reached by applying the object's velocity to its current point
     */
    public Line trajectoryLine() {
        return new Line(this.point, this.getVelocity().applyToPoint(this.point));
    }

    /**
     * Moves the ball one step based on the border-lines.
     */
    public void moveOneStep() {
        CollisionInfo colInfo = this.gameEnvironment.getClosestCollision(this.trajectoryLine());
        if (colInfo != null) { // a collision occurs next step
            this.setVelocity(colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.getVelocity()));
            Line almostHit = new Line(this.point, trajectoryLine().middle());
            this.point = almostHit.middle(); // move up to 75% of the trajectory line
        } else {
            this.point = this.getVelocity().applyToPoint(this.point);
        }
    }

    /**
     * Moves the ball one step based on the border-lines.
     *
     * @param lines the array of lines representing the border.
     *              lines is ordered as following: right border, left, top, bottom
     */
    public void moveOneStep(Line[] lines) {
        if (this.insideRec) {
            moveOneStepInsideRec(lines);
        } else {
            moveOneStepOutsideRec(lines);
        }
    }

    /**
     * method moveOneStep moves the ball one step inside the rectangle.
     *
     * @param lines is the array of lines that make the border.
     *              lines is ordered as following: right border, left, top, bottom
     */
    public void moveOneStepInsideRec(Line[] lines) {
        if (this.velocity == null) {
            this.velocity = new Velocity(0, 0);
        }
        boolean flag0 = hitBorder(lines[0], this.velocity, 0); // right gray
        boolean flag1 = hitBorder(lines[1], this.velocity, 1); // left gray
        boolean flag2 = hitBorder(lines[2], this.velocity, 2); // top gray
        boolean flag3 = hitBorder(lines[3], this.velocity, 3); //bottom gray
        boolean flag5 = false;
        boolean flag6 = false;
        if (lines.length > 4) {
            // meaning we move inside a rectangle with additional conditions - the yellow rectangle
            flag5 = hitBorder(lines[5], this.velocity, 5); // left yellow
            flag6 = hitBorder(lines[6], this.velocity, 6); // top yellow
        }
        if (flag0 || flag1 || flag5) {
            // hit the left / right border, and the direction is wrong
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (flag2 || flag3 || flag6) {
            // hit the top / bottom border, and the direction is wrong
            this.velocity.setDy(-this.velocity.getDy());

        } // could be that two conditions are met - left + top, etc.
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * method moveOneStep moves the ball one step outside the rectangle.
     *
     * @param lines is the array of lines that make the border.
     *              lines is ordered as following: right border, left, top, bottom
     */
    public void moveOneStepOutsideRec(Line[] lines) {
        if (this.velocity == null) {
            this.velocity = new Velocity(0, 0);
        }
        boolean flagRightGray = hitBorder(lines[0], this.velocity, 4); // right side of gray
        boolean flagRightYellow = hitBorder(lines[4], this.velocity, 4); // right side of yellow
        boolean flagLeftScreen = hitBorder(lines[9], this.velocity, 1); // left side of screen
        boolean flagLeftGray = hitBorder(lines[1], this.velocity, 5); // left side of gray
        boolean flagLeftYellow = hitBorder(lines[5], this.velocity, 5); // left side of yellow
        boolean flagRightScreen = hitBorder(lines[8], this.velocity, 0); // right side of screen
        boolean flagTopGray = hitBorder(lines[2], this.velocity, 6); // top side of gray
        boolean flagTopYellow = hitBorder(lines[6], this.velocity, 6); // top side of yellow
        boolean flagBottomScreen = hitBorder(lines[11], this.velocity, 3); // bottom side of screen
        boolean flagTopScreen = hitBorder(lines[10], this.velocity, 2); // top side of screen
        boolean flagBottomGray = hitBorder(lines[3], this.velocity, 7); //bottom side of gray
        if (flagRightGray || flagLeftGray || flagRightYellow || flagLeftYellow || flagRightScreen || flagLeftScreen) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (flagTopGray || flagBottomGray || flagTopYellow || flagTopScreen || flagBottomScreen) {
            this.velocity.setDy(-this.velocity.getDy());
        }
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * Determines if the ball hits a borderline based on its velocity and the border type.
     *
     * @param line the border-line to check
     * @param v    the velocity of the ball
     * @param i    the type of border to check
     * @return true if the ball hits the border, false otherwise
     */
    private boolean hitBorder(Line line, Velocity v, int i) {
        boolean flagRangeY;
        boolean flagRangeX;
        boolean flagRight;
        boolean flagLeft;
        boolean flagTop;
        boolean flagBottom;
        boolean flagDir;
        switch (i) {
            // first four options check from inside the borders, the rest from outside
            case 0:  // internal right border
                flagRight = this.getX() - this.getSize() <= line.start().getX()
                        && line.start().getX() <= this.getX() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDx())) {
                    flagRight = flagRight || this.getX() + v.getDx() - this.getSize() >= line.start().getX()
                            && this.getX() + this.getSize() <= line.start().getX();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeY = line.start().getY() <= this.getY() && this.getY() <= line.end().getY();
                flagDir = this.getX() + v.getDx() >= this.getX();
                return flagRight && flagRangeY && flagDir;
            case 1: // internal left border
                flagLeft = this.getX() - this.getSize() <= line.start().getX() && line.start().getX()
                        <= this.getX() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDx())) {
                    flagLeft = flagLeft || this.getX() + v.getDx() + this.getSize() <= line.start().getX()
                            && this.getX() - this.getSize() >= line.start().getX();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeY = line.start().getY() <= this.getY() && this.getY() <= line.end().getY();
                flagDir = this.getX() + v.getDx() <= this.getX();
                return flagLeft && flagRangeY && flagDir;
            case 2:  // internal top border
                flagTop = this.getY() - this.getSize() <= line.start().getY() && line.start().getY()
                        <= this.getY() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDy())) {
                    flagTop = flagTop || this.getY() + v.getDy() + this.getSize() <= line.start().getY()
                            && this.getY() - this.getSize() >= line.start().getY();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeX = line.start().getX() <= this.getX() && this.getX() <= line.end().getX();
                flagDir = this.getY() + v.getDy() <= this.getY();
                return flagTop && flagRangeX && flagDir;
            case 3:  // internal bottom border
                flagBottom = this.getY() - this.getSize() <= line.start().getY()
                        && line.start().getY() <= this.getY() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDy())) {
                    flagBottom = flagBottom || this.getY() + v.getDy() - this.getSize() >= line.start().getY()
                            && this.getY() + this.getSize() <= line.start().getY();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeX = line.start().getX() <= this.getX() && this.getX() <= line.end().getX();
                flagDir = this.getY() + v.getDy() >= this.getY();
                return flagBottom && flagRangeX && flagDir;
            case 4:  // external right border
                flagRight = this.getX() - this.getSize() <= line.start().getX() && line.start().getX()
                        <= this.getX() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDx())) {
                    flagRight = flagRight || this.getX() + v.getDx() + this.getSize() <= line.start().getX()
                            && this.getX() - this.getSize() >= line.start().getX();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeY = line.start().getY() <= this.getY() && this.getY() <= line.end().getY();
                flagDir = this.getX() + v.getDx() <= this.getX();
                return flagRight && flagRangeY && flagDir;
            case 5:  // external left border
                flagLeft = this.getX() - this.getSize() <= line.start().getX() && line.start().getX()
                        <= this.getX() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDx())) {
                    flagLeft = flagLeft || this.getX() + v.getDx() - this.getSize() >= line.start().getX()
                            && this.getX() + this.getSize() <= line.start().getX();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeY = line.start().getY() <= this.getY() && this.getY() <= line.end().getY();
                flagDir = this.getX() + v.getDx() >= this.getX();
                return flagLeft && flagRangeY && flagDir;
            case 6:  // external top border
                flagTop = this.getY() - this.getSize() <= line.start().getY() && line.start().getY()
                        <= this.getY() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDy())) {
                    flagTop = flagTop || this.getY() + v.getDy() - this.getSize() >= line.start().getY()
                            && this.getY() + this.getSize() <= line.start().getY();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeX = line.start().getX() <= this.getX() && this.getX() <= line.end().getX();
                flagDir = this.getY() + v.getDy() >= this.getY();
                return flagTop && flagRangeX && flagDir;
            case 7:  // external hit bottom border
                flagBottom = this.getY() - this.getSize() <= line.start().getY() && line.start().getY()
                        <= this.getY() + this.getSize();
                // checking if the ball is on the border
                if (this.getSize() <= Math.abs(v.getDy())) {
                    flagBottom = flagBottom || this.getY() + v.getDy() + this.getSize() <= line.start().getY()
                            && this.getY() - this.getSize() >= line.start().getY();
                } // if the ball is too small, it could be that it passes a border without first being on it
                flagRangeX = line.start().getX() <= this.getX() && this.getX() <= line.end().getX();
                flagDir = this.getY() + v.getDy() <= this.getY();
                return flagBottom && flagRangeX && flagDir;
            default:
                return false;
        }
    }

    /**
     * Notifies the sprite that time has passed, allowing it to update its state if necessary.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds this sprite to the specified game.
     *
     * @param g the game to add this sprite to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes this object from the specified Game instance.
     *
     * @param game the Game instance from which this object will be removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}