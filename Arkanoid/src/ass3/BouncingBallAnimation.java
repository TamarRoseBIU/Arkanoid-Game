// Tamar Rosenzweig
package ass3;
import geometry_primitives.Point;
import geometry_primitives.Line;
import sprites.Ball;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * Class BouncingBallAnimation represents an animation of a bouncing ball.
 */
public class BouncingBallAnimation {
    /**
     * Main method for running the bouncing ball animation.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Point start;
        if (args.length > 0) {
            // If a command-line argument is provided, use it
            try {
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                double dx = Integer.parseInt(args[2]);
                double dy = Integer.parseInt(args[3]);
                if (x >= 200 || x <= 0 || y >= 200 || y <= 0) {
                    // If no command-line argument is valid, use default
                    System.out.println("Command line argument isn't valid, using default: 150, 150, 8, 8.");
                    start = new Point(150, 150);
                    drawAnimation(start, 8, 8);
                }
                start = new Point(x, y);
                drawAnimation(start, (int) dx, (int) dy);
            } catch (Exception e) {
                // If no command-line argument is valid, use default
                System.out.println("Command line argument isn't valid, using default: 150, 150, 8, 8.");
                start = new Point(150, 150);
                drawAnimation(start, 8, 8);
            }
        } else {
            // If no command-line argument is valid, use default
            System.out.println("Command line argument isn't valid, using default: 150, 150, 8, 8.");
            start = new Point(150, 150);
            drawAnimation(start, 8, 8);
        }
    }
    /**
     * Draws the bouncing ball animation starting from a given point and velocity.
     *
     * @param start the starting point of the ball
     * @param dx    the x component of the ball's velocity
     * @param dy    the y component of the ball's velocity
     */
    public static void drawAnimation(Point start, int dx, int dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        Line[] lines = new Line[4];
        lines[0] = new Line(200, 0, 200, 200); // right
        lines[1] = new Line(0, 0, 0, 200); //left
        lines[2] = new Line(0, 0, 200, 0); // top
        lines[3] = new Line(0, 200, 200, 200); //bottom
        while (true) {
            ball.moveOneStep(lines);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(20); // wait for 50 milliseconds.
        }
    }
}