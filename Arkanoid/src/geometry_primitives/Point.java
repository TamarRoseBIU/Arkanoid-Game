// Tamar Rosenzweig
package geometry_primitives;
/**
 * Class Point represents a point in 2D space with x and y coordinates.
 * It provides constructors and methods for creating, accessing, and manipulating points,
 * as well as calculating distances between points and lines.
 */
public class Point {
    private double x; // The x-coordinate of the point
    private double y; // The y-coordinate of the point

    /**
     * This constructor receives no values, and assigns the coordinates to (0,0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a Point with the given x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a copy of the given Point.
     *
     * @param other The Point object to copy.
     */
    public Point(Point other) {
        if (other == null) {
            return;
        }
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The distance between the two points, or -1 if the other point is null.
     */
    public double distance(Point other) {
        if (other == null) {
            return -1;
        }
        double powX = Math.pow((this.x - other.x), 2);
        double powY = Math.pow((this.y - other.y), 2);
        return Math.sqrt(powX + powY);
    }

    /**
     * Calculates the distance between this point and a line.
     *
     * @param line The line to calculate the distance to.
     * @return The distance between the point and the line, or -1 if the line is null.
     */
    public double distance(Line line) {
        if (line == null) {
            return -1;
        }
        try {
            Equation e1 = new Equation(line);
            double nom = e1.getSlope() * this.getX() - this.getY() + e1.getConstant();
            double den = Math.sqrt(Math.pow(e1.getSlope() + 1, 2));
            return Math.abs(nom / den);
        } catch (Exception e) {
            //meaning the line is of the format x=a. so, the distance is this.x-a
            double a = line.start().getX();
            return Math.abs(a - this.x);
        }
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The other point to compare to.
     * @return True if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        boolean flag1 = Math.abs(this.x - other.x) <= Equation.COMPARISON_THRESHOLD;
        boolean flag2 = Math.abs(this.y - other.y) <= Equation.COMPARISON_THRESHOLD;
        return flag1 && flag2;
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x The new x-coordinate of the point.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y The new y-coordinate of the point.
     */
    public void setY(double y) {
        this.y = y;
    }
}