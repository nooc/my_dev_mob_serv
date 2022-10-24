package space.nixus.truckdocking.builders;

import space.nixus.truckdocking.factories.HeavyTruck;
import space.nixus.truckdocking.models.DockingStation;
import space.nixus.truckdocking.models.LoadableVehicle;

/**
 * Heavy loading station extension of {@link DockingStation}.
 */
public class HeavyTruckStation extends DockingStation {

    /**
     * Constructor
     *
     * @param name Station name
     */
    HeavyTruckStation(String name) {
        super(name);
    }

    /**
     * @see Dockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(LoadableVehicle vehicle) {
        if (testSpecialCase(vehicle) || vehicle instanceof HeavyTruck) {
            return super.dockVehicle(vehicle);
        }
        return false;
    }
}
