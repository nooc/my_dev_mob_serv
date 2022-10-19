package space.nixus.truckdocking;

import java.util.function.Function;

public abstract class DockingStation implements Dockable {

    // Station id
    protected final String name;
    // Docked vehicle
    private LoadableVehicle vehicle;
    // Special case. When true, may always dock.
    private Function<LoadableVehicle, Boolean> specialCase;

    /**
     * Constructor
     * @param name Dock name
     */
    protected DockingStation(String name) {
        this.name = name;
        this.vehicle = null;
    }

    /**
     * Name unique.
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Dockable && ((Dockable)obj).getName().equals(name);
    }

    /**
     * Name unique.
     */
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    /**
     * @return the id
     */
    public String getName()
    {
        return name;
    }

    /**
     * @see Dockable.printStationStatus
     */
    @Override
    public void printStationStatus() {
        if(vehicle != null) {
            System.out.format("%s: ", name);
            vehicle.printStatus();
        }
    }

    /**
     * @see Dockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(LoadableVehicle vehicle) {
        if(this.vehicle == null) {
            this.vehicle = vehicle;
            return true;
        }   
        return false;
    }

    /**
     * Test special case dock.
     * @param vehicle Vehicle to test
     * @return Success or fail
     */
    protected boolean testSpecialCase(LoadableVehicle vehicle) {
        return specialCase != null && specialCase.apply(vehicle);
    }

    /**
     * Set special case rule.
     * @param specialCase
     */
    public void setSpecialCase(Function<LoadableVehicle, Boolean> specialCase) {
        this.specialCase = specialCase;
    }
}
