package airline;

import java.util.*;

import javax.annotation.Nonnull;

public class BookingSystem {
    private final HashMap<String, Airport> airports;
    private final List<Airline> airlines;

    public BookingSystem() {

        System.out.println("--- INITIALIZING DATA ---");
        // Airports

        this.airports = new HashMap<>();
        var airports = Arrays.asList(
                new Airport("ARN", "Stockholm, Arlanda"),
                new Airport("GOT", "Gothenburg, Landvetter"),
                new Airport("CPH", "Copenhagen, Kastrup"),
                new Airport("MPX", "Milan, Malpensa"),
                new Airport("LHR", "London, Heathrow")
        );
        for (var airport : airports) {
            this.airports.put(airport.code(), airport);
        }

        // Airlines

        airlines = new ArrayList<>();
        Airline airline = new Airline("Scandinavian Airlines");
        airlines.add(airline);
        airline.addAirplane(new Airplane("Airbus 320", "SK070", 150));
        airline.addAirplane(new Airplane("Airbus 320", "SK071", 150));
        airline.addAirplane(new Airplane("Airbus 320", "SK072", 150));
        airline.print();
        System.out.println("    Generating flights...");
        generateFlights(airline, "ARN", "GOT", "SK070");
        generateFlights(airline, "ARN", "CPH", "SK071");
        generateFlights(airline, "GOT", "LHR", "SK072");

        airline = new Airline("Finnair");
        airlines.add(airline);
        airline.addAirplane(new Airplane("Airbus A350", "AY820", 297));
        airline.addAirplane(new Airplane("Airbus A330", "AY821", 289));
        airline.addAirplane(new Airplane("Airbus A330", "AY822", 289));
        airline.print();
        System.out.println("    Generating flights...");
        generateFlights(airline, "ARN", "MPX", "AY820");
        generateFlights(airline, "CPH", "MPX", "AY821");
        generateFlights(airline, "LHR", "CPH", "AY822");

        System.out.println("--- DONE INITIALIZING DATA ---");
        System.out.println();

    }

    String getAirportName(String code) {
        return airports.get(code).name();
    }

    /**
     * Generate round-trip flights between to and from for 10 consecutive days.
     *
     * @param airline
     * @param from
     * @param to
     * @param planeId
     */
    private void generateFlights(@Nonnull Airline airline, @Nonnull String from, @Nonnull String to, @Nonnull String planeId) {
        var cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        for (int i = 0; i < 10; ++i) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 30);
            var airplane = airline.getAirplane(planeId);
            var date = cal.getTime();
            if(airplane == null || date == null) { continue; }
            var flight = new ScheduledFlight(
                    this,
                    airplane,
                    from,
                    to,
                    date
            );
            airline.addFlight(flight);
            flight.print();
            cal.set(Calendar.HOUR_OF_DAY, 15);
            cal.set(Calendar.MINUTE, 0);
            date = cal.getTime();
            if(date == null) { continue; }
            flight = new ScheduledFlight(
                    this,
                    airplane,
                    to,
                    from,
                    date
            );
            airline.addFlight(flight);
            flight.print();
        }
    }

    /**
     * Output list of airports and connections.
     */
    public void listAirports() {
        // build list into map of connections
        HashMap<String,ArrayList<String>> ports = new HashMap<>();
        // get from all airlines and scheduled flights...
        for(var airline : airlines) {
            for(var flight : airline.getFlights()) {
                var from = flight.getFrom();
                var to = flight.getTo();
                ArrayList<String> toList;
                // add to connections array. create if needed
                if(ports.containsKey(from)) {
                    toList = ports.get(from);
                    if(!toList.contains(to)) { toList.add(to); }
                } else {
                    toList = new ArrayList<>();
                    toList.add(to);
                    ports.put(from, toList);
                }
            }
        }
        // output results
        System.out.println("Airports");
        for (var ap : ports.entrySet()) {
            var code = ap.getKey();
            System.out.format("%s (%s) <-> %s\n",
                    airports.get(code).name(),
                    code,
                    String.join(", ", ap.getValue()) // list of connections
            );
        }
        System.out.println();
    }

    /**
     * Query list of flights.
     * @param from From airport
     * @param to To airport
     * @param time Departure time
     * @return List of available flights
     */
    public List<ScheduledFlight> queryFlights(@Nonnull String from, @Nonnull String to, @Nonnull Date time) {
        var flights = new ArrayList<ScheduledFlight>();
        for (var airline : airlines) {
            flights.addAll(airline.queryFlights(from, to, time));
        }
        return flights;
    }

    /**
     * Get all made reservations.
     * @return list of reservations.
     */
    public List<Reservation> getReservations() {
        var reservations = new ArrayList<Reservation>();
        for (var airline : airlines) {
            var flights = airline.getFlights();
            for (var flight : flights) {
                reservations.addAll(flight.getReservations());
            }
        }
        return reservations;
    }
}
