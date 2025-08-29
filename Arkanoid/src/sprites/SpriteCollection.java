// Tamar Rosenzweig
package sprites;

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * A collection of Sprite objects that can be managed together.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spCollection = new ArrayList<>();
    /**
     * Constructs an empty SpriteCollection.
     */
    public SpriteCollection() {
    }
    /**
     * Constructs a new SpriteCollection by copying the sprites from another SpriteCollection.
     *
     * @param other the SpriteCollection to copy from
     */
    public SpriteCollection(SpriteCollection other) {
        for (Sprite iter : other.spCollection) {
            spCollection.add(iter);
        }
    }

    /**
     * Adds a Sprite to the collection.
     *
     * @param s the Sprite to add
     */
    public void addSprite(Sprite s) {
        if (spCollection == null) {
            spCollection = new ArrayList<>();
            spCollection.add(s);
        } else {
            spCollection.add(s);
        }
    }

    /**
     * Calls timePassed() on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        SpriteCollection copy = new SpriteCollection(this);
        for (Sprite s : copy.spCollection) {
            s.timePassed();
        }
    }

    /**
     * Calls drawOn(DrawSurface d) on all sprites in the collection.
     *
     * @param d the DrawSurface on which to draw all sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spCollection) {
            s.drawOn(d);
        }
    }

    /**
     * Removes the specified Sprite object from the collection of sprites.
     *
     * @param s the Sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        spCollection.remove(s);
    }
}