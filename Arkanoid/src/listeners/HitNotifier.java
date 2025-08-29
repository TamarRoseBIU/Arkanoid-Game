// Tamar Rosenzweig
package listeners;

/**
 * The HitNotifier interface allows objects to register and remove listeners
 * for hit events. Implementing classes can notify registered HitListeners
 * when a hit event occurs.
 */
public interface HitNotifier {

    /**
     * Adds the specified HitListener as a listener to hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the specified HitListener from the list of listeners to hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}