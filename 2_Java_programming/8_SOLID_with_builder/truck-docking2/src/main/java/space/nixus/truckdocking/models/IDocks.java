package space.nixus.truckdocking.models;

import java.util.List;

public interface IDocks {
    /**
     * Dock a LoadableVehicle vehicle.
     *
     * @param vehicle A LoadableVehicle
     * @return True if success, else false
     */
    boolean dockVehicle(ILoadableVehicle vehicle);

    /**
     * Get list of occupied stations.
     *
     * @return
     */
    List<IDockable> getOccupiedStations();
}
