// Tamar Rosenzweig
package geometry_primitives;
/**
 * The Equation class represents a linear equation in the form of "y = mx + c",
 * where 'm' is the slope and 'c' is the constant term.
 * It provides methods for creating equations, accessing their slope and constant,
 * and performing operations such as checking equality, parallelism, intersection,
 * and solving systems of linear equations.
 */
public class Equation {
    /**
     * Threshold value used for comparing constants and slopes.
     */
    static final double COMPARISON_THRESHOLD = 0.00001;
    private double slope;
    private double constant;

    /**
     * Constructs an Equation with the given slope and constant.
     *
     * @param slope    The slope of the equation.
     * @param constant The constant term of the equation.
     */
    public Equation(double slope, double constant) {
        this.slope = slope;
        this.constant = constant;
    }

    /**
     * Constructs an Equation passing through the given two points.
     * If the points are coincident, a horizontal line (y = constant) is created.
     *
     * @param p1 The first point on the line.
     * @param p2 The second point on the line.
     */
    public Equation(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            this.slope = 0;
            this.constant = 0;
            return;
        }
        // the equation of the slope is m = (y1 - y2) / (x1 - x2)
        if (p1.getX() == p2.getX()) { //&& (p1.getY() != p2.getY())
            // divide by zero, edge case. set to default
            this.slope = 0;
            this.constant = 0;
            return;
        }
        this.slope = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        this.constant = p1.getY() - slope * p1.getX(); // the equation of the const b, is b = y - mx
    }

    /**
     * Constructs an Equation from the given Line.
     *
     * @param l1 The line to create the equation from.
     */
    public Equation(Line l1) {
        this(l1.start(), l1.end());
    }

    /**
     * Constructs a copy of the given Equation.
     *
     * @param other The Equation object to copy.
     */
    public Equation(Equation other) {
        if (other == null) {
            return;
        }
        this.slope = other.slope;
        this.constant = other.constant;
    }

    /**
     * Gets the slope of the equation.
     *
     * @return The slope of the equation.
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * Gets the constant term of the equation.
     *
     * @return The constant term of the equation.
     */
    public double getConstant() {
        return this.constant;
    }

    /**
     * Checks if this equation is equal to another Equation.
     *
     * @param other The other Equation to compare.
     * @return True if the equations are approximately equal, otherwise false.
     */
    public boolean equal(Equation other) {
        // if the slope and the constant are the same
        boolean flag1 = Math.abs(this.constant - other.constant) <= COMPARISON_THRESHOLD;
        boolean flag2 = Math.abs(this.slope - other.slope) <= COMPARISON_THRESHOLD;
        return flag1 && flag2;
    }

    /**
     * Checks if this equation is parallel to another Equation.
     *
     * @param other The other Equation to compare.
     * @return True if the equations are approximately parallel but not equal, otherwise false.
     */
    public boolean parallel(Equation other) {
        // if the slope is the same, but the constant is different
        boolean flag1 = Math.abs(this.slope - other.slope) <= COMPARISON_THRESHOLD;
        return flag1 && !this.equal(other);
    }

    /**
     * Checks if this equation intersects with another Equation.
     *
     * @param other The other Equation to compare.
     * @return True if the equations intersect, otherwise false.
     */
    public boolean intersect(Equation other) {
        return !this.parallel(other);
        // only if the lines are parallel do they not have an intersection point
    }

    /**
     * Solves the system of linear equations formed by this and another Equation.
     *
     * @param other The other Equation to solve against.
     * @return The point of intersection if the equations intersect, otherwise null.
     */
    public Point solve(Equation other) {
        if (!this.intersect(other)) {
            return null; // as the default, sorry
        }
        // m1x + b1 = m2x + b2
        double sl = this.slope - other.slope; // (m1 - m2) x
        double co = other.constant - this.constant; // (b2 - b1)
        // (b2 - b1) / (m1 - m2) = the x Value of the intersection point
        double xVal = co / sl;
        // putting the values in the equation of the line
        double yVal = this.slope * xVal + this.constant;
        return new Point(xVal, yVal);
    }
}