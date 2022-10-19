package space.nixus.truckdocking;

public interface Dockable {

    /**
     * Name of the dockable.
     * @return name
     */
    String getName();

    /**
     * Print satus of dockable.
     */
    void printStationStatus();

    /**
     * Dock a vehicle to this dockable.
     * Fails if already occupied.
     * @param vehicle Vehicle to dock.
     * @return True if success, else false.
     */
    boolean dockVehicle(LoadableVehicle vehicle);
}
