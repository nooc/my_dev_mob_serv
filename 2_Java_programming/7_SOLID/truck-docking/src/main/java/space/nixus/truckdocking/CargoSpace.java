package space.nixus.truckdocking;

import java.util.ArrayList;
import java.util.List;

public class CargoSpace {
    private double maxLoad;
    private List<Cargo> cargo;

    /**
     * Constructor
     * @param maxLoad Maximum load capacity in kg.
     */
    public CargoSpace(double maxLoad) {
        this.maxLoad = maxLoad;
        cargo = new ArrayList<>();
    }

    public double getWeight() {
        double totalWeight = 0;
        for(var item : cargo) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public boolean addCargo(Cargo cargo) {
        if(getWeight() + cargo.getWeight() > maxLoad) {
            return false;
        }
        return this.cargo.add(cargo);
    }
}
