package space.nixus.truckdocking.builders;

import space.nixus.truckdocking.models.ILoadableVehicle;
import space.nixus.truckdocking.models.VehicleType;

/**
 * Van loading station extension of {@link DockingStation}.
 */
class VanStation extends DockingStation {

    /**
     * Constructor
     *
     * @param id Station id
     */
    VanStation(String id) {
        super(id);
    }

    /**
     * @see IDockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(ILoadableVehicle vehicle) {
        if (testSpecialCase(vehicle) || vehicle.getType().equals(VehicleType.Van)) {
            return super.dockVehicle(vehicle);
        }
        return false;
    }
}
