package space.nixus.truckdocking.factories;

import space.nixus.truckdocking.models.Box;
import space.nixus.truckdocking.models.ICargo;
import space.nixus.truckdocking.models.VehicleType;

/**
 * Van extension of {@link LoadableVehicle}.
 */
public class Van extends LoadableVehicle {

    /**
     * Constructor
     *
     * @param totalWeight Total weight in kg.
     */
    Van(double totalWeight) {
        super(totalWeight - VehicleType.Van.WEIGHT);
    }

    /**
     * @see IVehicle.getType
     */
    @Override
    public VehicleType getType() {
        return VehicleType.Van;
    }

    /**
     * Load box cargo into vehicle cargospace.
     *
     * @param cargo Box cargo
     */
    @Override
    public boolean loadCargo(ICargo cargo) {
        if (cargo instanceof Box) {
            return super.loadCargo(cargo);
        }
        return false;
    }
}
