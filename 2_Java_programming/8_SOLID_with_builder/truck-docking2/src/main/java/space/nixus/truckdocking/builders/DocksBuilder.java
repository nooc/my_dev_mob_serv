package space.nixus.truckdocking.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import space.nixus.truckdocking.models.IDocks;
import space.nixus.truckdocking.models.ILoadableVehicle;

public class DocksBuilder {

    private final List<DockingStation> stations;

    /**
     * Constructor
     */
    public DocksBuilder() {
        stations = new ArrayList<>();
    }

    /**
     * Add if Dockable name is unique.
     * @param station
     */
    private void addStation(DockingStation station) {
        if(!stations.contains(station)) {
            stations.add(station);
        }
    }

    /**
     * Add a VanStation with a unique name.
     * @param name Station name
     * @return this
     */
    public DocksBuilder addVanStation(String name) {
        addStation(new VanStation(name));
        return this;
    }

    /**
     * Add a LightTruckStation with a unique name.
     * @param name Station name
     * @return this
     */
    public DocksBuilder addLightTruckStation(String name) {
        addStation(new LightTruckStation(name));
        return this;
    }

    /**
     * Add a HeavyTruckStation with a unique name.
     * @param name Station name
     * @return this
     */
    public DocksBuilder addHeavyTruckStation(String name) {
        addStation(new HeavyTruckStation(name));
        return this;
    }

    /**
     * Build instance of an IDocks implementation.
     * @return IDocks
     */
    public IDocks build() {
        return new DocksImpl(stations);
    }

    /**
     * Set special case for existing station.
     * @param name Station name
     * @param specialCase Special case test
     * @return this
     */
    public DocksBuilder addSpecialCase(String name, Function<ILoadableVehicle, Boolean> specialCase) {
        for(var station : stations) {
            if(station.getName().equals(name)) {
                station.setSpecialCase(specialCase);
                break;
            }
        }
        return this;
    }
}
