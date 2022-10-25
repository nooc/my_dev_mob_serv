package space.nixus.truckdocking.models;

/**
 * The base class fro all vehicles.
 * Has type and weight.
 */
public interface IVehicle {

    /**
     * Get vehicle type.
     *
     * @return Type
     */
    VehicleType getType();

    /**
     * Get total vehicle weight.
     *
     * @return Weight in kg.
     */
    double getTotalWeight();
}
