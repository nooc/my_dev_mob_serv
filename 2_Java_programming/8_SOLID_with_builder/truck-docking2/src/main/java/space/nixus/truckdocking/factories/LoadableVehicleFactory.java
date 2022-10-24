package space.nixus.truckdocking.factories;

import space.nixus.truckdocking.models.VehicleType;
import space.nixus.truckdocking.models.LoadableVehicle;

public final class LoadableVehicleFactory {
    
    public static LoadableVehicle createVehicle(VehicleType type, double weight) {
        return switch(type) {
            case Van -> new Van(weight);
            case LightTruck -> new LightTruck(weight);
            case HeavyTruck -> new HeavyTruck(weight);
            default -> null;
        };
    }
}
