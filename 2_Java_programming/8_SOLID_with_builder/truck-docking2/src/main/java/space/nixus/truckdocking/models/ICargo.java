package space.nixus.truckdocking.models;

/**
 * The Cargo interface represents cargo with a weight in kg. 
 */
public interface ICargo {
    /**
     * Get weight of this cargo in kg.
     *
     * @return Weight in kg
     */
    double getWeight();
}
