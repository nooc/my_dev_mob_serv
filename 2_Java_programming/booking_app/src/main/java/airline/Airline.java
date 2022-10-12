package airline;

import java.util.*;
import javax.annotation.Nonnull;
/**
 * An airline
 */
public class Airline {
    /** Airline name */
    private final String name;
    /** Airplane fleet with flight number as key */
    private final Map<String, Airplane> airplanes;
    /** Scheduled flights */
    private final List<ScheduledFlight> flights;

    /**
     * Construct an airline
     * @param name Name of airline
     */
    public Airline(@Nonnull String name) {
        this.name = name;
        this.airplanes = new HashMap<>();
        flights = new ArrayList<>();
    }

    /**
     * Aaa a plane to the fleet.
     * @param airplane An airplane
     */
    public void addAirplane(@Nonnull Airplane airplane) {
        airplanes.put(airplane.getFlightNumber(), airplane);
    }

    /**
     * Get airplane.
     * @param flightNumber Flight number
     * @return An airplane
     */
    public Airplane getAirplane(@Nonnull String flightNumber) {
        return airplanes.get(flightNumber);
    }

    /**
     * Get list of flights.
     * @return List of flights
     */
    public List<ScheduledFlight> getFlights() {
        return flights;
    }

    /**
     * Add a flight.
     * @param flight Flight
     */
    void addFlight(@Nonnull ScheduledFlight flight) {
        if(!flights.contains(flight)) {
            flights.add(flight);
        }
    }

    /**
     * Query available flights.
     * @param from From airport
     * @param to To airport
     * @param time Departure time.
     * @return List of flights
     */
    public Collection<ScheduledFlight> queryFlights(@Nonnull String from, @Nonnull String to, @Nonnull Date time) {
        var resultList = new ArrayList<ScheduledFlight>();
        for (var flight : flights) {
            // Check if flight matches and if so, add it to the list.
            if (flight.matches(from, to, time)) {
                if(!flight.isFull()) {
                    resultList.add(flight);
                }
            }
        }
        return resultList;
    }

    /**
     * Print airline info.
     */
    public void print() {
        System.out.format("%s fleet\n", name);
        for (var plane : airplanes.values()) {
            plane.print();
        }
    }
}
