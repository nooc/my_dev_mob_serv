package space.nixus.truckdocking.factories;

import space.nixus.truckdocking.models.CargoSpace;
import space.nixus.truckdocking.models.ICargo;
import space.nixus.truckdocking.models.ILoadableVehicle;
import space.nixus.truckdocking.models.IVehicle;

/**
 * A LoadableVehicle is a {@link IVehicle} that can be loaded with {@link ICargo}. 
 */
abstract class LoadableVehicle implements ILoadableVehicle {

    /**
     * Vehicle cargo space.
     */
    private final CargoSpace cargoSpace;

    /**
     * Constructor
     *
     * @param maxLoad Maximum capacity in kg.
     */
    protected LoadableVehicle(double maxLoad) {
        cargoSpace = new CargoSpace(maxLoad);
    }

    /**
     * Load cargo into vehicle cargo hold.
     * Will fail if weight exceeds cargo hold capacity.
     *
     * @param cargo Cargo to load.
     * @return True if successful, else false.
     */
    @Override
    public boolean loadCargo(ICargo cargo) {
        return cargoSpace.addCargo(cargo);
    }

    /**
     * Get weight of cargo in kg.
     *
     * @return Weight in kg
     */
    @Override
    public double getCargoWeight() {
        return cargoSpace.getWeight();
    }

    /**
     * @return Weight in kg
     * @see IVehicle.getTotalWeight
     */
    @Override
    public double getTotalWeight() {
        return getUnloadedWeight() + getCargoWeight();
    }

    /**
     * Get unloaded weight.
     *
     * @return Weight in kg
     */
    @Override
    public int getUnloadedWeight() {
        return getType().WEIGHT;
    }
}
