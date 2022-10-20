package space.nixus.truckdocking;

/**
 * Van loading station extension of {@link DockingStation}.
 */
public class VanStation extends DockingStation {

    /**
     * Constructor
     *
     * @param id Station id
     */
    public VanStation(String id) {
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
