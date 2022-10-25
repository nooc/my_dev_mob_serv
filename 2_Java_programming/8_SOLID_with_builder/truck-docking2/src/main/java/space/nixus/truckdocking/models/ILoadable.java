package space.nixus.truckdocking.models;

public interface ILoadable {
    /**
     * Load cargo into loadable.
     * Will fail if weight exceeds capacity.
     *
     * @param cargo Cargo to load.
     * @return True if successful, else false.
     */
    boolean loadCargo(ICargo cargo);

    /**
     * Get weight of cargo in kg.
     *
     * @return Weight in kg
     */
    double getCargoWeight();
}
