package space.nixus.truckdocking;

public interface Dockable {

    /**
     * Name of the dockable.
     * @return name
     */
    String getName();

    /**
     * Get docked vehicle.
     * @return LoadableVehicle or null
     */
    LoadableVehicle getVehicle();

    /**
     * Dock a vehicle to this dockable.
     * Fails if already occupied.
     * @param vehicle Vehicle to dock.
     * @return True if success, else false.
     */
    boolean dockVehicle(LoadableVehicle vehicle);
}
