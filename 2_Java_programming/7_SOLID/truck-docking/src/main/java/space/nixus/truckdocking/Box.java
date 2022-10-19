package space.nixus.truckdocking;

public class Box implements Cargo {

    private double weight;

    public Box(double weight)
    {
        this.weight = weight;
    }

    @Override
    public double getWeight()
    {
        return weight;
    }
}
