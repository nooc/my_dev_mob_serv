package space.nixus.truckdocking;

public class HeavyTruck extends Truck {

    // name
    public static final String TYPE = "Heavy truck";
    // unloaded weight
    public static final double UNLOADED_WEIGHT = 6000;


    /**
     * Constructor
     *
     * @param totalWeight Total weight in kg
     */
    public HeavyTruck(double totalWeight) {
        super(totalWeight - UNLOADED_WEIGHT);
    }

    /**
     * @see Vehicle.getType
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * @see LoadableVehicle.getUnloadedWeight
     */
    @Override
    public double getUnloadedWeight() {
        return UNLOADED_WEIGHT;
    }
}
