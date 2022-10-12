package airline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;

public class ScheduledFlight {
    private final BookingSystem system;
    private final Airplane airplane;
    private final String from;
    private final String to;
    private final Date departure;
    private List<Reservation> reservations;
    private double cargo;

    /**
     * Construvt a scheduled flight
     * @param system Booking system
     * @param airplane airplane to schedule
     * @param from source airport
     * @param to destination airport
     * @param departure departure time
     */
    public ScheduledFlight(@Nonnull BookingSystem system, @Nonnull Airplane airplane, @Nonnull String from, @Nonnull String to, @Nonnull Date departure) {
        this.system = system;
        this.airplane = airplane;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.cargo = 0;
        this.reservations = new ArrayList<>();
    }

    /**
     * Actualize a reservation if possible.
     *
     * @param reservation reservation to actualize.
     * @return success status
     */
    public boolean makeReservation(@Nonnull Reservation reservation) {
        boolean hasSeats = (getPassengerCount() + reservation.getPassengerCount()) <= airplane.getPassengerCapacity();
        boolean hasCargoSpace = (cargo + reservation.getLuggageWeight()) <= airplane.getCargoCapacity();
        if (hasSeats && hasCargoSpace) {
            reservation.setFlight(this);
            reservations.add(reservation);
            return true;
        }
        return false;
    }

    /**
     * Match if from and to match and time is on the same day.
     *
     * @param from
     * @param to
     * @param targetDate
     * @return matches
     */
    public boolean matches(@Nonnull String from, @Nonnull String to, @Nonnull Date targetDate) {
        if (this.from.equals(from) && this.to.equals(to)) {
            var cal = Calendar.getInstance();
            cal.setTime(targetDate);
            cal.add(Calendar.DAY_OF_MONTH, 2);
            var maxDate = cal.getTime();
            return departure.after(targetDate) && departure.before(maxDate);
        }
        return false;
    }

    /**
     * Get current passenger count for flight.
     * @return passengers.
     */
    private int getPassengerCount() {
        int count = 0;
        for(var reservation : reservations) {
            count += reservation.getPassengerCount();
        }
        return count;
    }

    /**
     * Print flight information.
     */
    public void print() {
        System.out.format("Flight %s from %s to %s at %s.\n",
                airplane.getFlightNumber(),
                system.getAirportName(from),
                system.getAirportName(to),
                departure
        );
    }

    /**
     * Get list of reservations for flight.
     * @return
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Test if flight is fully booked.
     * @return true if full else false
     */
    public boolean isFull() {
        return getPassengerCount() == airplane.getPassengerCapacity();
    }

    /**
     * Get 'from' airport
     * @return airport code
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get 'to' airport.
     * @return airport code
     */
    public String getTo() {
        return to;
    }
}
