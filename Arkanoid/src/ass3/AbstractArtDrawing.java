// Tamar Rosenzweig
package ass3;
import geometry_primitives.Point;
import geometry_primitives.Line;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Class SimpleGuiExample.
 */
public class AbstractArtDrawing {
    /**
     * this method generates a random line.
     *
     * @return a new line
     */
    public static Line generateRandomLines() {
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(299) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(299) + 1; // get integer in range 1-300
        return new Line(x1, y1, x2, y2);
    }
    /**
     * this method colors a given point.
     *
     * @param p     is the point to color
     * @param color is the color to use
     * @param d     is the screen to color on
     */
    public static void colorPoints(Point p, Color color, DrawSurface d) {
        if (p == null) {
            return;
        }
        d.setColor(color);
        int r = 5;
        d.fillCircle((int) p.getX(), (int) p.getY(), r);
    }
    /**
     * this method colors a given line.
     *
     * @param l     is the line to color
     * @param color is the color to use
     * @param d     is the screen to color on
     */
    public static void colorLine(Line l, Color color, DrawSurface d) {
        if (l == null) {
            return;
        }
        d.setColor(color);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }
    /**
     * Class Main.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        AbstractArtDrawing example = new AbstractArtDrawing();
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++) {
            lines[i] = generateRandomLines();
            // if a line is actually a point, we'll avoid drawing it
            colorLine(lines[i], Color.BLACK, d);
            colorPoints(lines[i].middle(), Color.BLUE, d);
        }
        Line subLine1, subLine2, subLine3;
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                for (int k = j + 1; k < 10; k++) {
                    if (lines[i].isIntersecting(lines[j], lines[k])) {
                        subLine1 = new Line(lines[i].intersectionWith(lines[j]), lines[i].intersectionWith(lines[k]));
                        subLine2 = new Line(lines[i].intersectionWith(lines[j]), lines[j].intersectionWith(lines[k]));
                        subLine3 = new Line(lines[i].intersectionWith(lines[k]), lines[j].intersectionWith(lines[k]));
                        colorLine(subLine1, Color.GREEN, d);
                        colorLine(subLine2, Color.GREEN, d);
                        colorLine(subLine3, Color.GREEN, d);
                    }
                    //going over each 3 lines
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                //going over each 2 lines
                if (lines[i].isIntersecting(lines[j])) {
                    colorPoints(lines[i].intersectionWith(lines[j]), Color.RED, d);
                    //color the intersection point
                }
            }
        }
        gui.show(d);
    }
}