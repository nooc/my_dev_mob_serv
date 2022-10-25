package space.nixus.truckdocking.models;

public interface ILoadableVehicle extends ILoadable,IVehicle {
    /**
     * Get unloaded weight.
     *
     * @return Weight in kg
     */
    int getUnloadedWeight();
}
