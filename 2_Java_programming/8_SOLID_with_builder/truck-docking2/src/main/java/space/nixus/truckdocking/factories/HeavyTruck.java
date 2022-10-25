package space.nixus.truckdocking.factories;

import space.nixus.truckdocking.models.VehicleType;

/**
 * Heavy truck extension of {@link LoadableVehicle}.
 */
class HeavyTruck extends LoadableVehicle {

    /**
     * Constructor
     *
     * @param totalWeight Total weight in kg
     */
    HeavyTruck(double totalWeight) {
        super(totalWeight - VehicleType.HeavyTruck.WEIGHT);
    }

    /**
     * @see IVehicle.getType
     */
    @Override
    public VehicleType getType() {
        return VehicleType.HeavyTruck;
    }
}
