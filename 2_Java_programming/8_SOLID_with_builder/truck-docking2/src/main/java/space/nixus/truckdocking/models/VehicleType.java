package space.nixus.truckdocking.models;

public enum VehicleType {
    Van("Van", 1500),
    LightTruck("Light truck", 3000),
    HeavyTruck("Heavy truck", 6000);

    public final String LABEL;
    public final int WEIGHT;

    private VehicleType(String label, int unloadedWeight) {
        this.LABEL = label;
        this.WEIGHT = unloadedWeight;
    }
}
