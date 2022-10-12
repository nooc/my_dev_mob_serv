package airline;

import javax.annotation.Nonnull;

public class Person {
    private String name;
    private String surname;
    private String passportNumber;

    public Person(@Nonnull String name, @Nonnull String surname, @Nonnull String passportNumber) {
        this.name = name;
        this.surname = surname;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", name, surname, passportNumber);
    }
}
