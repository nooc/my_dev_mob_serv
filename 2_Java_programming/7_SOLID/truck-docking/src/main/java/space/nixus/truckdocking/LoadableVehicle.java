package space.nixus.truckdocking;

public abstract class LoadableVehicle implements Vecicle {

    /**
     * Vehicle cargo space.
     */
    private final CargoSpace cargoSpace;

    /**
     * Constructor
     * @param maxLoad Maximum capacity in kg.
     */
    protected LoadableVehicle(double maxLoad) {
        cargoSpace = new CargoSpace(maxLoad);
    }

    /**
     * Load cargo into vehicle cargo hold.
     * Will fail if weight exceeds cargo hold capacity.
     * @param cargo Cargo to load.
     * @return True if successful, else false.
     */
    public boolean loadCargo(Cargo cargo) {
        return cargoSpace.addCargo(cargo);
    }

    /**
     * Print vehicle type and cargo weight in kg. 
     */
    public void printStatus() {
        System.out.format("%s with a total weight of %d kg.\n",
            getType(),
            (int)Math.ceil(cargoSpace.getWeight() + getUnloadedWeight())
        );
    }

    /**
     * Get weight of cargo in kg.
     * @return Weight in kg
     */
    public double getCargoWeight() {
        return cargoSpace.getWeight();
    }

    /**
     * @see Vehicle.getTotalWeight
     * @return Weight in kg
     */
    @Override
    public double getTotalWeight()
    {
        return getUnloadedWeight() + getCargoWeight();
    }

    /**
     * Get unloaded weight.
     * @return Weight in kg
     */
    public abstract double getUnloadedWeight();

}
