package space.nixus.truckdocking;

public class Van extends LoadableVehicle {

    public static final String TYPE = "Van";
    public static final double UNLOADED_WEIGHT = 1500;

    /**
     * Constructor
     * @param totalWeight Total weight in kg.
     */
    public Van(double totalWeight) {
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
     * Load box cargo into vehicle cargospace.
     * @param cargo Box cargo
     */
    @Override
    public boolean loadCargo(Cargo cargo)
    {
        if(cargo instanceof Box) {
            return super.loadCargo(cargo);
        }
        return false;
    }

    @Override
    public double getUnloadedWeight()
    {
        return UNLOADED_WEIGHT;
    }
}
