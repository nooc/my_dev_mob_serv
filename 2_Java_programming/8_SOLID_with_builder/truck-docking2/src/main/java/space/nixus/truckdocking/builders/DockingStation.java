package space.nixus.truckdocking.builders;

import java.util.function.Function;
import space.nixus.truckdocking.models.IDockable;
import space.nixus.truckdocking.models.ILoadableVehicle;

/**
 * An abstract base class for Dockable stations and implements {@link IDockable}.
 */
abstract class DockingStation implements IDockable {

    // Station id
    private final String name;
    // Docked vehicle
    private ILoadableVehicle vehicle;
    // Special case. When true, may always dock.
    private Function<ILoadableVehicle, Boolean> specialCase;

    /**
     * Constructor
     *
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
    public boolean equals(Object obj) {
        return obj instanceof IDockable && ((IDockable) obj).getName().equals(name);
    }

    /**
     * Name unique.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * @return The station name
     */
    public String getName() {
        return name;
    }

    /**
     * @see IDockable.dockVehicle
     */
    @Override
    public boolean dockVehicle(ILoadableVehicle vehicle) {
        if (this.vehicle == null) {
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    /**
     * @see IDockable.getVehicle
     */
    @Override
    public ILoadableVehicle getVehicle() {
        return vehicle;
    }

    /**
     * Test special case.
     * If special case is true, permit docking.
     *
     * @param vehicle Vehicle to test
     * @return Success or fail
     */
    protected boolean testSpecialCase(ILoadableVehicle vehicle) {
        return specialCase != null && specialCase.apply(vehicle);
    }

    /**
     * Set special case rule.
     * Special case rule returns true if vehicle is a special case.
     *
     * @param specialCase
     */
    public void setSpecialCase(Function<ILoadableVehicle, Boolean> specialCase) {
        this.specialCase = specialCase;
    }
}
