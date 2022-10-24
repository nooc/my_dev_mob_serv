package space.nixus.truckdocking.models;

/**
 * Base class for all truck type {@link LoadableVehicle}s.
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
