package foobar;

import java.util.Scanner;

/** Main */
public class Main {
    public static void main(String[] args) {

        // Create scanner for System.in.
        
        Scanner input = new Scanner(System.in);

        // Execute exercises.

        System.out.println("BEGIN ScannerOvning1");
        new ScannerOvning1().Main(input);
        System.out.println("END ScannerOvning1");

        System.out.println("BEGIN ScannerOvning2");
        new ScannerOvning2().Main(input);
        System.out.println("END ScannerOvning2");
    }
}
