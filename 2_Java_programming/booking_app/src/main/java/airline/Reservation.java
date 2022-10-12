package airline;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class Reservation {
    private final List<Passenger> passengers;
    private ScheduledFlight flight;

    public Reservation() {
        this.flight = null;
        this.passengers = new ArrayList<>();
    }

    public ScheduledFlight getFlight() {
        return flight;
    }

    void setFlight(@Nonnull ScheduledFlight flight) {
        this.flight = flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public double getLuggageWeight() {
        double totalKg = 0;
        for (var passenger : passengers) {
            totalKg += passenger.getLuggageWeight();
        }
        return totalKg;
    }

    public boolean addPassenger(@Nonnull Passenger passenger) {
        if (!isReserved()) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    public boolean isReserved() {
        return flight != null;
    }

    public void print() {
        flight.print();
        for (var passenger : passengers) {
            passenger.print();
        }
    }

    public boolean hasPassenger(String passportId) {
        for (var passenger : passengers) {
            passenger.getPerson().getPassportNumber();
        }
        return false;
    }
}
