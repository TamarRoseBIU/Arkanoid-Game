// Tamar Rosenzweig
package ass3;
import geometry_primitives.Line;
import geometry_primitives.Point;
import sprites.Ball;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * Class MultipleFramesBouncingBallsAnimation creates an animation of balls bouncing inside multiple frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Draws two rectangles on the given DrawSurface.
     *
     * @param d the surface to draw on
     */
    public static void drawRectangles(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(50, 50, 450, 450);
        d.setColor(Color.YELLOW);
        d.fillRectangle(450, 450, 150, 150);
    }
    /**
     * Checks whether the ball (given by its center coordinates and radius) is colliding with any of the borders.
     *
     * @param x       the x-coordinate of the ball center
     * @param y       the y-coordinate of the ball center
     * @param size    the radius of the ball
     * @param borders an array containing the borders that might be hit
     * @return true if the ball collides with one of the borders, false otherwise
     */
    public static boolean collide(int x, int y, int size, Line[] borders) {
        // the points are already within the frame of the
        for (int i = 0; i < borders.length; i++) {
            if (i == 0 || i == 1 || i == 4 || i == 5 || i == 8 || i == 9) { // meaning the line is vertical
                if (x - size < borders[i].start().getX() && x + size > borders[i].start().getX()) {
                    return true;
                }
            } else { // meaning the line is horizontal
                if (y - size < borders[i].start().getY() && y + size > borders[i].start().getY()) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks whether a point is inside the gray rectangle.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @return true if the point is inside the gray rectangle, false otherwise
     */
    private static boolean isPointInsideRec(int x, int y) {
        return x <= 500 && x >= 50 && y >= 50 && y <= 500;
    }
    /**
     * Initializes the balls with random colors and velocities based on their sizes.
     *
     * @param size         an array containing the sizes of the balls
     * @param ball         an array of Ball objects to be initialized
     * @param centerPoints an array of Point objects representing the center points of the balls
     * @return an array of initialized Ball objects
     */
    private static Ball[] createBallsArrayWithPoints(int[] size, Ball[] ball, Point[] centerPoints) {
        for (int i = 0; i < ball.length; i++) {
            // setting the balls center, direction and size
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            ball[i] = new Ball(centerPoints[i], size[i], randomColor);
            int speedSize = Math.min((size[i]), 50); //2 balls, which sizes are 50, 50+, move at the same speed
            int max = 8 - speedSize / 10;
            int min = 6 - speedSize / 10;
            int negative = rand.nextInt(2) + 1; //randomly picking a number between 1 and 2,
            // signifying the plus / minus sign to the speed
            int dx = rand.nextInt(max - min) + min;
            int dy = rand.nextInt(max - min) + min;
            if (negative == 1) {
                dx *= -1;
            } else {
                dy *= -1;
            }
            ball[i].setVelocity(dx, dy);
        }
        return ball;
    }
    /**
     * Initializes the balls for the animation.
     *
     * @param size    an array of sizes for the balls
     * @param ball    an array to hold the ball objects
     * @param borders an array of lines representing the borders
     * @return an array of initialized balls
     */
    private static Ball[] initialize(int[] size, Ball[] ball, Line[] borders) {
        Point[] centerPoints = new Point[size.length];
        Line[] grayRec = new Line[4];
        Random rand = new Random();
        int centerX;
        int centerY;
        grayRec[0] = new Line(500, 50, 500, 500); // right side of gray
        grayRec[1] = new Line(50, 50, 50, 500); //left side of gray
        grayRec[2] = new Line(50, 50, 500, 50); // top side of gray
        grayRec[3] = new Line(50, 500, 500, 500); //bottom side of gray
        // Initialize balls inside the gray rectangle
        for (int i = 0; i < size.length / 2; i++) {
            do {
                centerX = rand.nextInt(500 - 50) + 50;
                centerY = rand.nextInt(500 - 50) + 50;
            }
            while (collide(centerX, centerY, size[i], grayRec));
            // as long as the points don't fit exactly inside the gray rectangle, keep creating random points
            centerPoints[i] = new Point(centerX, centerY);
        }
        // Initialize balls outside the gray rectangle
        for (int i = ball.length / 2; i < ball.length; i++) {
            // setting the balls outside the gray rectangle - center, direction and size
            do {
                centerX = rand.nextInt(800);
                centerY = rand.nextInt(600);
            }
            while (collide(centerX, centerY, size[i], borders) || isPointInsideRec(centerX, centerY));
            // as long as the points don't fit exactly outside the gray rectangle, keep creating random points
            centerPoints[i] = new Point(centerX, centerY);
        }
        // now, after points are set and done, create suitable balls
        ball = createBallsArrayWithPoints(size, ball, centerPoints);
        for (int i = size.length / 2; i < size.length; i++) {
            ball[i].setInsideRec(false);
        }
        return ball;
    }
    /**
     * The main method to run the animation.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Random Circles Example", 800, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();
        int sized = args.length;
        int[] size = new int[sized];
        Ball[] allBalls = new Ball[sized];
        Line[] borders = new Line[12];
        borders[0] = new Line(500, 50, 500, 500); // right side of gray
        borders[1] = new Line(50, 50, 50, 500); //left side of gray
        borders[2] = new Line(50, 50, 500, 50); // top side of gray
        borders[3] = new Line(50, 500, 500, 500); //bottom side of gray
        borders[4] = new Line(600, 450, 600, 600); // right side of yellow
        borders[5] = new Line(450, 450, 450, 600); //left side of yellow
        borders[6] = new Line(450, 450, 600, 450); // top side of yellow
        borders[7] = new Line(450, 600, 600, 600); //bottom side of yellow
        borders[8] = new Line(800, 0, 800, 600); // right side of screen
        borders[9] = new Line(0, 0, 0, 600); // left side of screen
        borders[10] = new Line(0, 0, 800, 0); // top side of screen
        borders[11] = new Line(0, 600, 800, 600); // bottom side of screen
        if (args.length == 0) { // If no command-line argument is provided, use default
            System.out.println("Command line argument isn't valid, using default.");
            sized = 6;
            size = new int[sized];
            allBalls = new Ball[6];
            for (int i = sized - 1; i >= 0; i--) {
                size[i] = rand.nextInt(50 - 10) + 10;
            }
        } else {
            try {
                for (int i = 0; i < args.length; i++) {
                    int sizeofBall = Integer.parseInt(args[i]);
                    if (sizeofBall >= 100 || sizeofBall <= 0) { //size limit
                        System.out.println("Size is invalid, using default.");
                        sized = 6;
                        size = new int[sized];
                        allBalls = new Ball[6];
                        for (int j = sized - 1; j >= 0; j--) {
                            size[j] = rand.nextInt(50 - 10) + 10;
                        }
                        break;
                    }
                    size[i] = sizeofBall;
                }
            } catch (Exception e) { // in case input is invalid, use default
                System.out.println("Command line argument isn't valid, using default.");
                sized = 6;
                size = new int[sized];
                allBalls = new Ball[6];
                for (int i = sized - 1; i >= 0; i--) {
                    size[i] = rand.nextInt(50 - 10) + 10;
                }
            }
        }
        allBalls = initialize(size, allBalls, borders);
        DrawSurface d;
        while (true) {
            d = gui.getDrawSurface();
            drawRectangles(d);
            for (int i = 0; i < allBalls.length; i++) {
                allBalls[i].moveOneStep(borders);
                allBalls[i].drawOn(d);
            }
            sleeper.sleepFor(10);
            gui.show(d);
        }
    }
}