package space.nixus.truckdocking.builders;

import space.nixus.truckdocking.factories.Van;
import space.nixus.truckdocking.models.DockingStation;
import space.nixus.truckdocking.models.LoadableVehicle;

/**
 * Van loading station extension of {@link DockingStation}.
 */
public class VanStation extends DockingStation {

    /**
     * Constructor
     *
     * @param id Station id
     */
    VanStation(String id) {
        super(id);
    }

    /**
     * @see Dockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(LoadableVehicle vehicle) {
        if (testSpecialCase(vehicle) || vehicle instanceof Van) {
            return super.dockVehicle(vehicle);
        }
        return false;
    }
}
