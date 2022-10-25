package space.nixus.truckdocking.builders;

import space.nixus.truckdocking.models.ILoadableVehicle;
import space.nixus.truckdocking.models.VehicleType;

/**
 * Heavy loading station extension of {@link DockingStation}.
 */
class HeavyTruckStation extends DockingStation {

    /**
     * Constructor
     *
     * @param name Station name
     */
    HeavyTruckStation(String name) {
        super(name);
    }

    /**
     * @see IDockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(ILoadableVehicle vehicle) {
        if (testSpecialCase(vehicle) || vehicle.getType().equals(VehicleType.HeavyTruck)) {
            return super.dockVehicle(vehicle);
        }
        return false;
    }
}
