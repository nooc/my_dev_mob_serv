package airline;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

public class App {
    /**
     * Regex to parse flight queries.
     */
    private final Pattern flightQuery;
    /**
     * Booking system
     */
    private final BookingSystem system;
    /**
     * Our scanner
     */
    private final Scanner input;

    /**
     * Construct app.
     */
    private App() {
        system = new BookingSystem();
        input = new Scanner(System.in);
        flightQuery = Pattern.compile("^([A-Z]{3})\\s+([A-Z]{3})\\s+(202[0-9])-([0-9]{2})-([0-9]{2})$");
    }

    /**
     * Application main.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        new App().run();
    }

    /**
     * Run the booking app.
     */
    private void run() {
        System.out.println("Welcome to My Flight Booking System(tm)");
        // Reservation loop.
        while (true) {
            System.out.println("Reservations");
            // Print reservations
            for (var reservation : system.getReservations()) {
                reservation.print();
            }
            // Console interface
            System.out.println("""
                    1. Book Flight
                    2. Quit
                    """);
            var option = prompt("Choice");
            if (option.equals("1")) {
                bookFlight();
            } else if (option.equals("2")) {
                System.out.println("Bye.");
                System.exit(0);
            }
        }
    }

    /**
     * Make a reservation / book a flight.
     */
    private void bookFlight() {
        // print available airports
        system.listAirports();
        System.out.println("""
                Please enter search parameters in the format below (use airport codes).
                    FROM TO YYYY-MM-DD
                """);
        // get query
        var matcher = flightQuery.matcher(prompt("Query"));
        if (!matcher.matches()) {
            System.out.println("Input format error,");
            return;
        }
        // extract query parameters
        var from = matcher.group(1);
        var to = matcher.group(2);
        var year = Integer.parseInt(matcher.group(3));
        var month = Integer.parseInt(matcher.group(4)) - 1;
        var day = Integer.parseInt(matcher.group(5));
        var cal = Calendar.getInstance();
        cal.set(year, month, day, 0, 0);
        var date = cal.getTime();
        // list flights
        if(from==null || to == null || date == null) {
            System.out.println("Input error,");
            return;
        }
        var flights = system.queryFlights(from, to, date);
        if (flights.size() == 0) {
            System.out.println("No flights found.");
            return;
        }
        System.out.println("Found flights:");
        int menuId = 0;
        for (var flight : flights) {
            System.out.format("%d. ", ++menuId);
            flight.print();
        }
        // select flight
        var flightChoice = Integer.parseInt(prompt("Select"));
        if (flightChoice > 0 && flightChoice <= menuId) {
            createReservation(flights.get(flightChoice - 1));
        }
    }

    /**
     * Create a reservation.
     * @param scheduledFlight selected flight
     */
    private void createReservation(ScheduledFlight scheduledFlight) {
        var reservation = new Reservation();
        while (true) {
            System.out.println("Passengers");
            // print current passengers
            for (var pas : reservation.getPassengers()) {
                pas.print();
            }
            // print options
            System.out.println("""
                    1. Add passenger
                    2. Finish
                    3. Cancel""");
            // get choice
            var choice = prompt("Choice");
            if (choice.equals("1")) {
                addPassenger(reservation);
            } else if (choice.equals("2")) {
                if (reservation.getPassengerCount() > 0) {
                    if (scheduledFlight.makeReservation(reservation)) {
                        System.out.println("Reservation created.");
                        break;
                    }
                } else {
                    System.out.println("Please add passengers.");
                }
            } else if (choice.equals("3")) {
                System.out.println("Cancelling reservation.");
                break;
            }
        }
    }

    /**
     * Add a passenger.
     * @param reservation
     */
    private void addPassenger(Reservation reservation) {
        var passportId = prompt("Passport number");
        if(reservation.hasPassenger(passportId)) {
            System.out.println("Passenger exists.");
            return;
        }
        var name = prompt("Name");
        var surname = prompt("Surname");
        var passenger = new Passenger(new Person(name, surname, passportId));
        addBags(passenger);
        reservation.addPassenger(passenger);
    }

    /**
     * Add bags for a passenger
     * @param passenger
     */
    private void addBags(Passenger passenger) {
        while (true) {
            System.out.println("Bags");
            for (var bag : passenger.getBags()) {
                bag.printBag();
            }
            System.out.println("""
                    1. Add bag
                    2. Finish""");
            var choice = prompt("Choice");
            if (choice.equals("1")) {
                System.out.println("""
                        Please enter bag parameters in the format below.
                            TYPE WEIGHT
                        (type is C for checked and U for unchecked)
                        """);
                var bagString = prompt("Bag").split("\\s+");
                Bag.Type type;
                if (bagString[0].equals("C")) {
                    type = Bag.Type.Checked;
                } else if (bagString[0].equals("U")) {
                    type = Bag.Type.Unchecked;
                } else {
                    System.out.println("Input error.");
                    continue;
                }
                passenger.addBag(new Bag(
                        Double.parseDouble(bagString[1]),
                        type
                ));
            } else if (choice.equals("2")) {
                break;
            }
        }
    }

    /**
     * Prompt helper.
     * @param prompt
     * @return user input
     */
    public @Nonnull String prompt(String prompt) {
        while (true) {
            System.out.format("%s: ", prompt);
            var result = input.nextLine().strip();
            if (!result.isEmpty()) return result;
        }
    }
}
