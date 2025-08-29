// Tamar Rosenzweig

package geometry_primitives;
import java.util.List;

/**
 * Class Line represents a line segment in 2D space defined by a start point and an end point.
 * It provides constructors and methods for creating, accessing, and manipulating lines,
 * as well as checking intersections and calculating properties like length and middle point.
 */
public class Line {
    static final double MAX_DISTANCE = 10000;
    private Point start; // The starting point of the line
    private Point end;   // The ending point of the line
    // constructors

    /**
     * This constructor receives no values, and assigns the line to be the coordinates of (0,0).
     */
    public Line() {
        this.start = new Point(0, 0);
        this.end = new Point(0, 0);
    }

    /**
     * Constructs a Line with the given start and end points.
     *
     * @param start The start point of the line.
     * @param end   The end point of the line.
     */
    public Line(Point start, Point end) {
        if (start == null) {
            this.start = new Point(0, 0);
            this.end = new Point(0, 0);
            return;
        }
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a Line with the given coordinates for the start and end points.
     *
     * @param x1 The x-coordinate of the start point.
     * @param y1 The y-coordinate of the start point.
     * @param x2 The x-coordinate of the end point.
     * @param y2 The y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Constructs a copy of the given Line.
     *
     * @param other The Line object to copy.
     */
    public Line(Line other) {
        if (other == null || other.start == null || other.end == null) {
            return;
        }
        this.start = new Point(other.start.getX(), other.start.getY());
        this.end = new Point(other.end.getX(), other.end.getY());
    }

    /**
     * Sets the start point of the line.
     *
     * @param s The point to set as the start point.
     */
    public void setStart(Point s) {
        if (this.start == null) {
            this.start = new Point(s.getX(), s.getY());
        } else {
            this.start.setX(s.getX());
            this.start.setY(s.getY());
        }
    }

    /**
     * Sets the end point of the line.
     *
     * @param s The point to set as the end point.
     */
    public void setEnd(Point s) {
        if (this.end == null) {
            this.end = new Point(s.getX(), s.getY());
        } else {
            this.end.setX(s.getX());
            this.end.setY(s.getY());
        }
    }

    /**
     * Returns the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return this.end().distance(this.start);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        if (this.lineIsAPoint()) {
            return null; // edge case - if a line is a point, it has no middle point
        }
        double midX = (Math.abs(start.getX() + end.getX())) * 0.5;
        double midY = (Math.abs(start.getY() + end.getY())) * 0.5;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return The start point of the line.
     */
    public Point start() {
        if (this.start == null) {
            return null;
        }
        return new Point(start.getX(), start.getY());
    }

    /**
     * Returns the end point of the line.
     *
     * @return The end point of the line.
     */
    public Point end() {
        if (this.end == null) {
            return null;
        }
        return new Point(end.getX(), end.getY());
    }

    /**
     * Determines if the line is actually a point.
     * This method checks if the start and end points of the line are the same.
     *
     * @return if the start and end points of the line are equal, indicating the line is a point;
     * false otherwise.
     */
    public boolean lineIsAPoint() {
        return this.start.equals(this.end);
    }

    /**
     * Determines if a given point is within the range defined by the start and end points of this line segment.
     *
     * @param p the point to check
     * @return if the point is within the borders defined by the start and end points of this line segment.
     * false otherwise
     */
    public boolean pointInRange(Point p) {
        double minY = Math.min(this.start.getY(), this.end.getY());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        return (Math.abs(-minX + p.getX()) <= Equation.COMPARISON_THRESHOLD || p.getX() >= minX)
                && (Math.abs(-p.getX() + maxX) <= Equation.COMPARISON_THRESHOLD || p.getX() <= maxX)
                && (Math.abs(p.getY() - minY) <= Equation.COMPARISON_THRESHOLD || p.getY() >= minY)
                && (Math.abs(maxY - p.getY()) <= Equation.COMPARISON_THRESHOLD || p.getY() <= maxY);
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other The other line to check for intersection.
     * @return True if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        boolean flagCurrent = this.start.getX() == this.end.getX();
        boolean flagOther = other.start.getX() == other.end().getX();
        Equation e1 = new Equation(this);
        Equation e2 = new Equation(other);
        if (this.lineIsAPoint() || other.lineIsAPoint()) {
            //edge case - one of the lines is actually a point
            return false;
        }
        if (flagCurrent || flagOther) { // edge case - one of the lines is vertical
            if (flagCurrent && flagOther) {
                // both lines are vertical
                if (this.start.getX() != other.start.getX()) { // the lines are parallel
                    return false;
                }
                return this.pointInRange(other.start) || this.pointInRange(other.end);
                // the coordinates of x are the same, now checking the y coordinates
                // the starting or ending point of one line is in the range of the other line,
                // indicating the lines coincide. otherwise - they have a space between them, and don't intersect
            }
            // else - one is vertical, the other isn't
            Point solution;
            if (flagCurrent) { // the current line is the vertical line. the other line has an equation
                solution = new Point(this.start.getX(), e2.getSlope() * this.start.getX() + e2.getConstant());
                // y = mx + b
            } else {
                solution = new Point(this.start.getX(), e1.getSlope() * this.start.getX() + e1.getConstant());
            }
            // else - other is the vertical line
            return this.pointInRange(solution) && other.pointInRange(solution);
        }
        if (!e1.intersect(e2)) {
            return false;
        }
        if (e1.equal(e2)) {
            return true;
        }
        //assuming the lines intersect, check if intersection point is in the range of the line
        Point solution = e1.solve(e2);
        return this.pointInRange(solution) && other.pointInRange(solution);
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 The first line.
     * @param other2 The second line.
     * @return True if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return other1.isIntersecting(other2) && this.isIntersecting(other2) && this.isIntersecting(other1);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other The other line to check for intersection.
     * @return The intersection point, if it exists.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        boolean flagCurrent = this.start.getX() == this.end.getX();
        boolean flagOther = other.start.getX() == other.end().getX();
        Equation e1 = new Equation(this);
        Equation e2 = new Equation(other);
        if (flagCurrent || flagOther) {
            // edge case - at least one of the lines is vertical, but the lines do intersect
            if (flagCurrent && flagOther) { // both lines are vertical, and do intersect.
                // if only one point is common to both lines, return it. else - return null.
                if ((this.start.equals(other.start) || this.start.equals(other.end))
                        && !(other.pointInRange(this.end))) {
                    // meaning the starting point of this line is common to both lines, but the ending point is
                    // outside the other line
                    return this.start;
                }
                if ((this.end.equals(other.start) || this.end.equals(other.end)) && !(other.pointInRange(this.start))) {
                    // meaning the ending point of this line is common to both lines, but the starting point is
                    // outside the other line
                    return this.end;
                }
                return null;
                // mx + b
            }
            if (flagCurrent) { // the current line is the vertical line, the other isn't
                return new Point(this.start.getX(), e2.getSlope() * this.start.getX() + e2.getConstant());
                // mx + b
            }
            // else - the other line is the vertical line, the current isn't
            return new Point(other.start.getX(), e1.getSlope() * this.start.getX() + e1.getConstant());
        }
        return e1.solve(e2); // assuming we checked it's in range, in the isIntersecting function
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other The other line to compare to.
     * @return True if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        // checking if the lines are equal, even if the starting and ending points order is switched
        boolean flag1 = this.start.equals(other.start());
        boolean flag2 = this.end.equals(other.end());
        boolean flag3 = this.start.equals(other.end());
        boolean flag4 = this.end.equals(other.start());
        return flag1 && flag2 || flag3 && flag4;
    }

    /**
     * If this line does not intersect with the rectangle, returns null.
     * Otherwise, returns the closest intersection point to the start of the line.
     *
     * @param rect the rectangle to check for intersections with this line
     * @return the closest intersection point to the start of the line, or null if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closestPoint = null;
        double minDistance = MAX_DISTANCE;
        for (Point pointer : intersectionPoints) {
            if (pointer != null) {
                double distance = pointer.distance(this.start);
                if (distance <= minDistance) {
                    closestPoint = pointer;
                    minDistance = distance;
                }
            }
        }
        return closestPoint;
    }
}