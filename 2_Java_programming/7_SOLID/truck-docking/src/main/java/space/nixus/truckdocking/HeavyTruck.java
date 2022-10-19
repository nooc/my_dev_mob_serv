package space.nixus.truckdocking;

public class HeavyTruck extends Truck {

    public static final String TYPE = "Heavy truck";
    public static final double UNLOADED_WEIGHT = 6000;


    /**
     * Constructor
     * @param totalWeight Total weight in kg
     */
    public HeavyTruck(double totalWeight) {
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

    @Override
    public double getUnloadedWeight()
    {
        return UNLOADED_WEIGHT;
    }
}
