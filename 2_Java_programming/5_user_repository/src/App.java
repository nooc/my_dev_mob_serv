/**
 * Very simple OOP excersize.
 * Do some simple user repository manipulations.
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class App {

    private final UserRepository repository;

    private App() {
        repository = new UserRepository();
    }

    private void run() {

        // Add two users, FooMan and BarMan.

        System.out.println("Adding 2 users.");
        System.out.println();
        repository.add(new User("FooMan","Key42"));
        repository.add(new User("BarMan","Card42"));

        // List user/pass.
        listUsers();

        // Update BarMan password.

        System.out.println("Updating BarMan.");
        repository.find("BarMan").setPassword("KeyCard84");
        System.out.println();

        System.out.println("Authenticating users with 'Key42'.");
        for(var user : repository.getAllUsers()) {
            user.authenticate("Key42");
        }
        System.out.println();

        // List user/pass.
        listUsers();

        // Removing user FooMan.

        System.out.println("Deleting FooMan.");
        repository.delete(repository.find("FooMan"));
        System.out.println();

        // List user/pass.
        listUsers();

        // Exit.
        System.out.println("Bye.");
    }

    /**
     * List users with password.
     */
    private void listUsers() {
        System.out.println("Listing all users:");
        var allUsers = repository.getAllUsers();
        for(var user : allUsers) {
            System.out.format("%s:%s\n", user.getUsername(),user.getPassword());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new App().run();
    }
}
