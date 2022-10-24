package space.nixus.truckdocking.models;

/**
 * The base class fro all vehicles.
 * Has type and weight.
 */
public interface Vehicle {

    /**
     * Get vehicle type.
     *
     * @return Type string
     */
    String getType();

    /**
     * Get total vehicle weight.
     *
     * @return Weight in kg.
     */
    double getTotalWeight();
}
