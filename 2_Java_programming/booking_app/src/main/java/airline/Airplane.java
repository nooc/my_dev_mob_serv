package airline;

import javax.annotation.Nonnull;

public class Airplane {
    private static final double AVERAGE_PASSENGER_WEIGHT = 70; // used for estimating cargo capacity
    private static final double AVERAGE_CHECKED_WEIGHT = 15; // used for estimating cargo capacity
    private static final double AVERAGE_UNCHECKED_WEIGHT = 5; // used for estimating cargo capacity

    /** Airplane model */
    private final String model;
    /** Assigned flight number */
    private final String flightNumber;
    /** Airplane passenger capacity */
    private final int passengerCapacity;
    /** Airplane cargo capacity */
    private final double cargoCapacity;

    /**
     * Construct airplane
     * @param model Airplane model
     * @param flightNumber Assigned flight number
     * @param passengerCapacity Airplane passenger capacity
     */
    public Airplane(@Nonnull String model, @Nonnull String flightNumber, int passengerCapacity) {
        this.model = model;
        this.flightNumber = flightNumber;
        this.passengerCapacity = passengerCapacity;
        // Calculate max cargo weight from passenger count.
        this.cargoCapacity = passengerCapacity * (AVERAGE_PASSENGER_WEIGHT + AVERAGE_CHECKED_WEIGHT + AVERAGE_UNCHECKED_WEIGHT);
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Print airplane info.
     */
    public void print() {
        System.out.format("%s (%s); %d seats.\n", model, flightNumber, passengerCapacity);
    }
}
