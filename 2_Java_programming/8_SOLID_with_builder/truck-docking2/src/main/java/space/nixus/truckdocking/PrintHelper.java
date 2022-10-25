package space.nixus.truckdocking;

import space.nixus.truckdocking.models.IDockable;
import space.nixus.truckdocking.models.IDocks;
import space.nixus.truckdocking.models.IVehicle;

/**
 * Print helper contains console printing functions.
 */
public final class PrintHelper {

    /**
     * Print Docks status.
     *
     * @param docks
     */
    public static void printDocksStatus(IDocks docks) {
        docks.getOccupiedStations().forEach(dockable -> dockableprintDockableStatus(dockable));
        System.out.println();
    }

    /**
     * Print Dockable status.
     *
     * @param dockable
     */
    private static void dockableprintDockableStatus(IDockable dockable) {
        var vehicle = dockable.getVehicle();
        if (vehicle != null) {
            System.out.format("%s: ", dockable.getName());
            printVehicle(vehicle);
        }
    }

    /**
     * Print LoadableVehicle
     *
     * @param vehicle
     */
    private static void printVehicle(IVehicle vehicle) {
        System.out.format("%s with a total weight of %.0f kg.\n",
                vehicle.getType(),
                Math.ceil(vehicle.getTotalWeight())
        );
    }
}
