package space.nixus.truckdocking.models;

/**
 * A Dockable represents a dockable station where {@link ILoadableVehicle}s can dock.
 */
public interface IDockable {

    /**
     * Name of the dockable.
     *
     * @return name
     */
    String getName();

    /**
     * Get docked vehicle.
     *
     * @return LoadableVehicle or null
     */
    ILoadableVehicle getVehicle();

    /**
     * Dock a vehicle to this dockable.
     * Fails if already occupied.
     *
     * @param vehicle Vehicle to dock.
     * @return True if success, else false.
     */
    boolean dockVehicle(ILoadableVehicle vehicle);
}
