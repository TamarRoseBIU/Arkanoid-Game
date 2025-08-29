// Tamar Rosenzweig

package listeners;

/**
 * The Counter class is used to keep track of a count, which can be increased or decreased.
 */
public class Counter {
    private int counter = 0;

    /**
     * Adds the specified number to the current count.
     *
     * @param number the number to be added to the counter
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Subtracts the specified number from the current count.
     *
     * @param number the number to be subtracted from the counter
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return counter;
    }
}