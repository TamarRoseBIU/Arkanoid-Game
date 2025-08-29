// Tamar Rosenzweig
package ass3;
import geometry_primitives.Point;
import geometry_primitives.Line;
import sprites.Ball;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;
/**
 * Class MultipleBouncingBallAnimation.
 * The MultipleBouncingBallsAnimation class is responsible for creating an animation
 * of multiple balls bouncing within a defined rectangular area.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * class main.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Random rand = new Random();
        int[] size;
        Ball[] ball;
        GUI gui = new GUI("Random Circles Example", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        if (args.length != 0) {
            try {
                size = new int[args.length];
                ball = new Ball[args.length];
                for (int i = 0; i < args.length; i++) {
                    int sizeofBall = Integer.parseInt(args[i]);
                    if (sizeofBall >= 100 || sizeofBall <= 0) { //size limit
                        System.out.println("Size is too big, using default.");
                        size = new int[6];
                        ball = new Ball[6];
                        for (int j = 0; j < 6; j++) {
                            size[j] = j * 9 + 10;
                        }
                        break;
                    }
                    size[i] = sizeofBall;
                }
            } catch (Exception e) { // in case input is invalid
                // If no command-line argument is valid, use default
                System.out.println("Command line argument isn't valid, using default.");
                size = new int[6];
                ball = new Ball[6];
                for (int i = 0; i < 6; i++) {
                    size[i] = i * 9 + 10;
                }
            }
        } else { // If no command-line argument is provided, use default
            size = new int[6];
            ball = new Ball[6];
            for (int i = 0; i < 6; i++) {
                size[i] = i * 9 + 10;
            }
        }
        Line[] lines = new Line[4];
        lines[0] = new Line(200, 0, 200, 200); // right
        lines[1] = new Line(0, 0, 0, 200); //left
        lines[2] = new Line(0, 0, 200, 0); // top
        lines[3] = new Line(0, 200, 200, 200); //bottom
        int centerX;
        int centerY;
        Point[] centerPoints = new Point[size.length];
        for (int i = 0; i < size.length; i++) {
            do {
                centerX = rand.nextInt(200) + size[i]; // - 2 * size[i]
                centerY = rand.nextInt(200) + size[i]; //- 2 * size[i]
            }
            while (collide(centerX, centerY, size[i], lines));
            centerPoints[i] = new Point(centerX, centerY);
        }
        ball = initialize(size, ball, centerPoints);
        DrawSurface d;
        while (true) {
            d = gui.getDrawSurface();
            for (int i = 0; i < size.length; i++) {
                ball[i].moveOneStepInsideRec(lines);
                ball[i].drawOn(d);
                sleeper.sleepFor(4); // wait for 4 milliseconds.
            }
            gui.show(d);
        }
    }
    /**
     * Initializes the balls with random colors and velocities based on their sizes.
     *
     * @param size         an array containing the sizes of the balls
     * @param ball         an array of Ball objects to be initialized
     * @param centerPoints an array of Point objects representing the center points of the balls
     * @return an array of initialized Ball objects
     */
    public static Ball[] initialize(int[] size, Ball[] ball, Point[] centerPoints) {
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
     * Checks whether a ball (defined by its center coordinates and radius) collides with any of the borders.
     *
     * @param x       the x-coordinate of the ball center
     * @param y       the y-coordinate of the ball center
     * @param size    the radius of the ball
     * @param borders an array of Line objects representing the borders
     * @return true if the ball collides with any of the borders, false otherwise
     */
    public static boolean collide(int x, int y, int size, Line[] borders) {
        // the points are already within the frame of the
        for (int i = 0; i < borders.length; i++) {
            if (i == 0 || i == 1 || i == 4 || i == 5) { // meaning the line is vertical
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
}