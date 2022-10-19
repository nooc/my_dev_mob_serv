package space.nixus.truckdocking;

public class LightTruck extends Truck {

    public static final String TYPE = "Light truck";
    public static final double UNLOADED_WEIGHT = 3000;

    /**
     * Constructor
     * @param totalWeight Total weight in kg
     */
    public LightTruck(double totalWeight) {
        super(totalWeight-UNLOADED_WEIGHT);
    }

    /**
     * @see Vecicle.getType
     */
    @Override
    public String getType()
    {
        return TYPE;
    }

    /**
     * @see Vecicle.getType
     */
    @Override
    public double getUnloadedWeight()
    {
        return UNLOADED_WEIGHT;
    }

    
}   
