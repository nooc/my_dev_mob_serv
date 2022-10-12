package airline;

import javax.annotation.Nonnull;

/**
 * A bag record with weight and type.
 * @param weight Weight in kg
 * @param type Bag type
 */
public record Bag(double weight, @Nonnull airline.Bag.Type type) {
    public enum Type {
        Unchecked,
        Checked
    }

    /**
     * Print bag info.
     */
    public void printBag() {
        System.out.format("%s (%.1f kg)\n", type.toString(), weight);
    }
}
