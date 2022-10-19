package space.nixus.truckdocking;

public class LightTruck extends Truck {

    // name
    public static final String TYPE = "Light truck";
    // unloaded weight
    public static final double UNLOADED_WEIGHT = 3000;

    /**
     * Constructor
     * @param totalWeight Total weight in kg
     */
    public LightTruck(double totalWeight) {
        super(totalWeight-UNLOADED_WEIGHT);
    }

    /**
     * @see Vehicle.getType
     */
    @Override
    public String getType()
    {
        return TYPE;
    }

    /**
     * @see LoadableVehicle.getUnloadedWeight
     */
    @Override
    public double getUnloadedWeight()
    {
        return UNLOADED_WEIGHT;
    }

    
}   
