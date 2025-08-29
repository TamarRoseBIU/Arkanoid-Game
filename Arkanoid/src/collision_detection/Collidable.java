// Tamar Rosenzweig
package collision_detection;

import sprites.Ball;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;

/**
 * This interface represents objects that can collide with each other.
 * Objects implementing this interface can provide information about their collision shape
 * and react to collisions with other objects by calculating new velocities.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision rectangle representing the shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that it was collided with at the specified collision point with a given velocity.
     * Returns the new velocity expected after the hit.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the colliding object
     * @param hitter          is the ball that hits the object
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}