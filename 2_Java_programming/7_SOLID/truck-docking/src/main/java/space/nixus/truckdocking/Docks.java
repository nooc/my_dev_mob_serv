package space.nixus.truckdocking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Docks represents a palce with one or more {@link Dockable} stations.
 */
public class Docks {
    // dockables
    private final List<Dockable> stations;

    /**
     * Construct the docks
     */
    public Docks() {
        stations = new ArrayList<>();
    }

    /**
     * Add a dockable station to docks.
     * Fails with false if name is not unique.
     *
     * @param dockable Dockable station
     * @return True if success, else false
     */
    public boolean addStation(Dockable dockable) {
        if (stations.contains(dockable)) {
            return false;
        }
        return stations.add(dockable);
    }

    /**
     * Dock a LoadableVehicle vehicle.
     *
     * @param vehicle A LoadableVehicle
     * @return True if success, else false
     */
    public boolean dockVehicle(LoadableVehicle vehicle) {
        for (var station : stations) {
            if (station.dockVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get list of occupied stations.
     *
     * @return
     */
    public List<Dockable> getOccupiedStations() {
        return Collections.unmodifiableList(stations);
    }
}
