package airline;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class Passenger {
    /** Person connected to this passenger. */
    private final Person person;
    /** Bags for this passenger */
    private final List<Bag> bags;

    /**
     * Construct passenger
     * @param person Person
     */
    public Passenger(@Nonnull Person person) {
        this.person = person;
        this.bags = new ArrayList<>();
    }

    /**
     * Add a bag for this passenger.
     * @param bag A bag
     */
    public void addBag(@Nonnull Bag bag) {
        bags.add(bag);
    }

    /**
     * Get total luggage weight.
     * @return Kg
     */
    public double getLuggageWeight() {
        double sumKg = 0;
        for (var bag : bags) {
            sumKg += bag.weight();
        }
        return sumKg;
    }

    /**
     * Print passenger info.
     */
    public void print() {
        System.out.format("""
                        Name: %s
                        Bags: %d, %.1f kg\n""",
                person,
                bags.size(),
                getLuggageWeight()
        );
    }

    /**
     * Get passenger bags.
     * @return list of bags
     */
    public List<Bag> getBags() {
        return bags;
    }

    /**
     * Get the person
     * @return A person
     */
    public Person getPerson() {
        return person;
    }
}
