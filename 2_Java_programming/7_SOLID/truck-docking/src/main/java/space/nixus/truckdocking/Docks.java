package space.nixus.truckdocking;

import java.util.ArrayList;
import java.util.List;

public class Docks {
    private List<Dockable> stations;

    public Docks() {
        stations = new ArrayList<>();
    }

    public boolean addStation(Dockable dockable) {
        if(stations.contains(dockable)) {
            return false;
        }
        return stations.add(dockable);
    }

    public boolean dockVehicle(LoadableVehicle vehicle) {
        for(var station : stations) {
            if(station.dockVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void printdockStatus() {
        stations.forEach(station -> station.printStationStatus());
    }
}
