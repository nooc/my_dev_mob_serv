package space.nixus.truckdocking;

/**
 * A Pallet is a type of {@link Cargo} that can be loaded with {@link Box} cargo.
 */
public class Pallet implements Cargo {

    public static final double PALLET_WEIGHT = 25; // kg
    public static final double MAX_PALLET_WEIGHT = 1500; // kg

    // cargo space of this pallet
    private final CargoSpace boxes;

    /**
     * Constructor
     * Initialize pallet cargo space with standard pallet values.
     */
    public Pallet() {
        this.boxes = new CargoSpace(MAX_PALLET_WEIGHT - PALLET_WEIGHT);
    }

    /**
     * @see Cargo.getWeight
     */
    @Override
    public double getWeight() {
        return boxes.getWeight() + PALLET_WEIGHT;
    }

    /**
     * Add a box cargo to this pallet.
     * Fails if max weight is exceeded.
     *
     * @param box Box cargo
     * @return True on success, else false.
     */
    public boolean addBox(Box box) {
        if (getWeight() + box.getWeight() > MAX_PALLET_WEIGHT) {
            return false;
        }
        return boxes.addCargo(box);
    }
}
