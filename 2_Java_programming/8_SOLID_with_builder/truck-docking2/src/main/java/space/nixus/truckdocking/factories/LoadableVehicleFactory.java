package space.nixus.truckdocking.factories;

import space.nixus.truckdocking.models.VehicleType;
import space.nixus.truckdocking.models.ILoadableVehicle;

public final class LoadableVehicleFactory {
    
    /**
     * Create a LoadableVehicle.
     * A vehicle's weight must me equal to or greater than UNLOADED_WEIGHT.
     * @param type VehicleType
     * @param weight Weight
     * @return LoadableVehicle or null
     */
    public static ILoadableVehicle createVehicle(VehicleType type, double weight) {
        if(weight >= type.WEIGHT) {
            return switch (type) {
                case Van -> new Van(weight);
                case LightTruck -> new LightTruck(weight);
                case HeavyTruck -> new HeavyTruck(weight);
            };
        }
        return null;
    }
}
