package space.nixus.truckdocking;

/**
 * Box is an implementation of {@link Cargo Cargo}.
 */
public class Box implements Cargo {
    /**
     * Box weight.
     */
    private final double weight;

    /**
     * Constructor
     *
     * @param weight Weight of box in kg.
     */
    public Box(double weight) {
        this.weight = weight;
    }

    /**
     * @see Cargo#getWeight()
     */
    @Override
    public double getWeight() {
        return weight;
    }
}
