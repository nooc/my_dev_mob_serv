package space.nixus.truckdocking.builders;

import space.nixus.truckdocking.models.ILoadableVehicle;
import space.nixus.truckdocking.models.VehicleType;

/**
 * Light loading station extension of {@link DockingStation}.
 */
class LightTruckStation extends DockingStation {

    /**
     * Constructor
     *
     * @param id Ststion id
     */
    LightTruckStation(String id) {
        super(id);
    }

    /**
     * @see IDockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(ILoadableVehicle vehicle) {
        if (testSpecialCase(vehicle) || vehicle.getType().equals(VehicleType.LightTruck)) {
            return super.dockVehicle(vehicle);
        }
        return false;
    }
}
