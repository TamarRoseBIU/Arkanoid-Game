// Tamar Rosenzweig
package collision_detection;

import geometry_primitives.Line;
import geometry_primitives.Rectangle;
import java.util.ArrayList;

/**
 * Represents the game environment containing collidable objects.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableObjects;

    /**
     * Constructs a new GameEnvironment object.
     */
    public GameEnvironment() {
        collidableObjects = new ArrayList<>();
    }

    /**
     * Constructs a new GameEnvironment object.
     *
     * @param collidableObjects is the list to copy
     */
    public GameEnvironment(ArrayList<Collidable> collidableObjects) {
        this.collidableObjects = new ArrayList<>();
        this.collidableObjects.addAll(collidableObjects);
    }

    /**
     * Creates a new {@code GameEnvironment} by copying the collidable objects from another {@code GameEnvironment}.
     *
     * @param other the {@code GameEnvironment} to copy from
     */
    public GameEnvironment(GameEnvironment other) {
        collidableObjects = new ArrayList<>();
        collidableObjects.addAll(other.collidableObjects);
    }

    /**
     * Adds the given collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        if (collidableObjects == null) {
            collidableObjects = new ArrayList<>();
            collidableObjects.add(c);
        } else {
            collidableObjects.add(c);
        }
    }

    /**
     * Determines the closest collision that will occur with any collidable object
     * along the trajectory of the specified line.
     *
     * @param trajectory the trajectory line of the object
     * @return the information about the closest collision, or null if no collision will occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> copy = new ArrayList<>();
        copy.addAll(collidableObjects);
        for (Collidable c : copy) {
            Rectangle rec = new Rectangle(c.getCollisionRectangle());
            if (trajectory.closestIntersectionToStartOfLine(rec) != null) {
                return new CollisionInfo(trajectory.closestIntersectionToStartOfLine(rec), c);
            }
        }
        return null;
    }

    /**
     * Removes the specified Collidable object from the collection of collidables.
     *
     * @param c the Collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        collidableObjects.remove(c);
    }
}