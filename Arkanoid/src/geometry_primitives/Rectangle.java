// Tamar Rosenzweig
package geometry_primitives;

import java.util.ArrayList;
import java.awt.Color;

/**
 * Class Rectangle.
 * Represents a rectangle defined by its upper-left corner, width, and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color = Color.BLACK; // default color

    /**
     * Creates a new rectangle with the specified location and dimensions.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new rectangle with the specified location and dimensions.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     is the color to paint the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Constructs a new rectangle that is a copy of the specified rectangle.
     * If the specified rectangle is null, constructs an empty rectangle with zero width and height.
     *
     * @param other the rectangle to copy
     */
    public Rectangle(Rectangle other) {
        if (other == null) {
            this.upperLeft = new Point(0, 0);
            this.width = 0;
            this.height = 0;
            return;
        }
        this.upperLeft = new Point(other.upperLeft);
        this.width = other.width;
        this.height = other.height;
        this.color = other.color;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections with the rectangle
     * @return a list of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lines = new Line[4];
        lines[0] = new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width,
                upperLeft.getY() + height); // right
        lines[1] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height); // left
        lines[2] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY()); // top
        lines[3] = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height); // bottom
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (lines[i].intersectionWith(line) != null) {
                points.add(lines[i].intersectionWith(line));
            }
        }
        return points;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return a new Point object representing the upper-left corner of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(upperLeft);
    }

    /**
     * Returns the color of this block.
     *
     * @return the color of this block
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the new width of the rectangle
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the new height of the rectangle
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Sets the upper-left point of the rectangle.
     *
     * @param upperLeft the new upper-left point of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = new Point(upperLeft);
    }
}