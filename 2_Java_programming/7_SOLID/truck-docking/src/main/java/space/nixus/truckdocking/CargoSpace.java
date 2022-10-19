package space.nixus.truckdocking;

import java.util.ArrayList;
import java.util.List;

public class CargoSpace {
    // Maximum load of cargo space
    private double maxLoad;
    // List of cargo
    private List<Cargo> cargo;

    /**
     * Constructor
     * @param maxLoad Maximum load capacity in kg.
     */
    public CargoSpace(double maxLoad) {
        this.maxLoad = maxLoad;
        cargo = new ArrayList<>();
    }

    /**
     * @see Cargo.getWeight
     * @return weight in kg
     */
    public double getWeight() {
        double totalWeight = 0;
        // sdum weights
        for(var item : cargo) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    /**
     * Add a cargo item to this cargo space.
     * @param cargo Cargo
     * @return True if added, false if no more capacity.
     */
    public boolean addCargo(Cargo cargo) {
        if(getWeight() + cargo.getWeight() > maxLoad) {
            return false;
        }
        return this.cargo.add(cargo);
    }
}
