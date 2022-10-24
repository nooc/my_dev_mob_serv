package space.nixus.truckdocking.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A cargo space can hold {@link Cargo Cargo} objects up to maxLoad kg.
 */
public class CargoSpace {
    // Maximum load of cargo space
    private final double maxLoad;
    // List of cargo
    private final List<Cargo> cargo;

    /**
     * Constructor
     *
     * @param maxLoad Maximum load capacity in kg.
     */
    public CargoSpace(double maxLoad) {
        this.maxLoad = maxLoad;
        cargo = new ArrayList<>();
    }

    /**
     * @return weight in kg
     * @see Cargo.getWeight
     */
    public double getWeight() {
        double totalWeight = 0;
        // sdum weights
        for (var item : cargo) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    /**
     * Add a cargo item to this cargo space.
     *
     * @param cargo Cargo
     * @return True if added, false if no more capacity.
     */
    public boolean addCargo(Cargo cargo) {
        if (getWeight() + cargo.getWeight() > maxLoad) {
            return false;
        }
        return this.cargo.add(cargo);
    }
}
