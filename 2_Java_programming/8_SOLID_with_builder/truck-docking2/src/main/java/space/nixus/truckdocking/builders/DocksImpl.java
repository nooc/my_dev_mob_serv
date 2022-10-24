package space.nixus.truckdocking.builders;

import java.util.Collections;
import java.util.List;
import space.nixus.truckdocking.models.Dockable;
import space.nixus.truckdocking.models.IDocks;
import space.nixus.truckdocking.models.LoadableVehicle;

/**
 * The Docks represents a palce with one or more {@link Dockable} stations.
 */
final class DocksImpl implements IDocks {
    // dockables
    private final List<? extends Dockable> stations;

    /**
     * Construct the docks
     */
    protected DocksImpl(List<? extends Dockable> stations) {
        this.stations = stations;
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
