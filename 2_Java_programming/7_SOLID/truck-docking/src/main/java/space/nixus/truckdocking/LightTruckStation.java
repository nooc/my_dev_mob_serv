package space.nixus.truckdocking;

/**
 * Light loading station extension of {@link DockingStation}.
 */
public class LightTruckStation extends DockingStation {

    /**
     * Constructor
     *
     * @param id Ststion id
     */
    public LightTruckStation(String id) {
        super(id);
    }

    /**
     * @see Dockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(LoadableVehicle vehicle) {
        if (testSpecialCase(vehicle) || vehicle instanceof LightTruck) {
            return super.dockVehicle(vehicle);
        }
        return false;
    }
}
