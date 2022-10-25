package space.nixus.truckdocking.factories;

import space.nixus.truckdocking.models.VehicleType;

/**
 * Light truck extension of {@link LoadableVehicle}.
 */
class LightTruck extends LoadableVehicle {

    /**
     * Constructor
     *
     * @param totalWeight Total weight in kg
     */
    LightTruck(double totalWeight) {
        super(totalWeight - VehicleType.LightTruck.WEIGHT);
    }

    /**
     * @see IVehicle.getType
     */
    @Override
    public VehicleType getType() {
        return VehicleType.LightTruck;
    }
}   
