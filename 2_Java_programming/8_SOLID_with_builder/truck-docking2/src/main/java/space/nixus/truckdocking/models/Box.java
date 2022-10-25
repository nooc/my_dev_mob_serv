package space.nixus.truckdocking.models;

/**
 * Box is an implementation of {@link ICargo Cargo}.
 */
public class Box implements ICargo {
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
     * @see ICargo#getWeight()
     */
    @Override
    public double getWeight() {
        return weight;
    }
}
