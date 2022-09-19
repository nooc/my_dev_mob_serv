package foobar;

import java.util.Scanner;


/** Scanner exercise 2. */
public class ScannerOvning2 {

    /** Scanner exercise method.
     * 
     * @param input Scanner to use.
     */
    public void Main(Scanner input) {

        // Get nane, address and phone.

        System.out.print("Input name: ");
        String name = input.nextLine();

        System.out.print("Input addressd: ");
        String address = input.nextLine();

        System.out.print("Input phone: ");
        String tel = input.nextLine();

        // Oupput results.

        System.out.format("   Name: %s\n", name);
        System.out.format("Address: %s\n", address);
        System.out.format("  Phone: %s\n", tel);
    }
}