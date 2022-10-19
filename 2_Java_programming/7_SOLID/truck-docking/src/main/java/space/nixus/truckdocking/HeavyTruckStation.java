package space.nixus.truckdocking;

public class HeavyTruckStation extends DockingStation {

    /**
     * Constructor
     * @param name Ststion id
     */
    public HeavyTruckStation(String name)
    {
        super(name);
    }

    /**
     * @see Dockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(LoadableVehicle vehicle) {
        if(testSpecialCase(vehicle) || vehicle instanceof HeavyTruck) {
            return super.dockVehicle(vehicle);
        }
        return false;
    }
}
