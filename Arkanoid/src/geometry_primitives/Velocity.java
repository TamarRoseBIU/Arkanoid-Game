// Tamar Rosenzweig
package geometry_primitives;
/**
 * Class Velocity represents a 2D velocity vector with components dx and dy.
 * It provides constructors and methods for creating, accessing, and manipulating velocity vectors.
 */
public class Velocity {
    private double dx; // The change in x-coordinate per unit time
    private double dy; // The change in y-coordinate per unit time
    // constructor

    /**
     * Constructs a Velocity with the given change in x and y per unit time.
     *
     * @param dx The change in x-coordinate per unit time.
     * @param dy The change in y-coordinate per unit time.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a copy of the given Velocity.
     *
     * @param other The Velocity object to copy.
     */
    public Velocity(Velocity other) {
        if (other == null) {
            return;
        }
        this.dx = other.dx;
        this.dy = other.dy;
    }

    /**
     * Constructs a Velocity from the given angle and speed.
     *
     * @param angle The angle of the velocity vector.
     * @param speed The magnitude of the velocity vector.
     * @return A new Velocity object with components calculated from angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets the change in x-coordinate per unit time.
     *
     * @return The dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in y-coordinate per unit time.
     *
     * @return The dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the change in x-coordinate per unit time.
     *
     * @param dx The new dx value of the velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the change in y-coordinate per unit time.
     *
     * @param dy The new dy value of the velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point, resulting in a new point with adjusted coordinates.
     *
     * @param p The point to which the velocity is applied.
     * @return A new Point object with coordinates (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}