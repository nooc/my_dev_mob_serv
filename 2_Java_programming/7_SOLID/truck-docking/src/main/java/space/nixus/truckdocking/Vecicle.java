package space.nixus.truckdocking;

public interface Vecicle {

    /**
     * Get vehicle type.
     * @return Type string
     */
    String getType();

    /**
     * Get total vehicle weight.
     * @return Weight in kg.
     */
    double getTotalWeight();
}
