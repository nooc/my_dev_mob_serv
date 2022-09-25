package foobar;

import java.util.Scanner;


/** Scanner exercise 1. */
public class ScannerOvning1 {

    /** Scanner exercise method.
     * 
     * @param input Scanner to use.
     */
    public void Main(Scanner input) {

        // Get name.

        System.out.print("Input name: ");
        String name = input.nextLine();

        // Output results.

        System.out.format("Hey %s!\n", name);
        System.out.format("%s is a beautiful name.\n", name);
    }
}