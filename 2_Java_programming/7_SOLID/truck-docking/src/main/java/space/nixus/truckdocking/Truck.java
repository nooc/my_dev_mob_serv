package space.nixus.truckdocking;

/**
 * Superclass for all trucks
 */
public abstract class Truck extends LoadableVehicle {

    /**
     * Constructor
     *
     * @param carryLoad Load capacity in kg
     */
    protected Truck(double carryLoad) {
        super(carryLoad);
    }
}
