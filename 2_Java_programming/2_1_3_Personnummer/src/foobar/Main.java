package foobar;

import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    /** Mainprogram.
     * 
     * Read in and verify a swedish personal id number.
     * Input format: [YY]YYMMDD[-]NNNN.
     * @param args
     */
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        var pnPattern = Pattern.compile("^(?:19|20)?([0-9]{6})\\-?([0-9]{4})$");
        
        int[] pn = new int[10]; // store pn as ints

        // input loop
        while(true) {
            // get
            System.out.print("Swedish personal id number: ");
            String pnStr = input.nextLine().trim();
            // match
            var mo = pnPattern.matcher(pnStr);
            if(mo.matches()) {
                var p1 = mo.group(1).toCharArray();
                var p2 = mo.group(2).toCharArray();
                // convert to int array
                int pos = 0;
                for (char c : p1) {
                    pn[pos++] =  (int)c-48; // ascii for "0" is 48.
                }
                for (char c : p2) {
                    pn[pos++] =  (int)c-48;
                }
                break;
            }
            else {
                System.out.println("Incorrect format.");
            }
        }

        // Calculate control number by mapping pn_val to pn by multiplying
        // and then summing each digit of the product for each item.
        // The control is then 50-sum.
        // see: https://www.personnummer.nu/verifiera/ (Swedish)
        int[] pn_val = {2,1,2,1,2,1,2,1,2}; // multiplication table
        int sum = 0;
        for (int i = 0; i < pn_val.length; ++i) {
            int prod = pn[i] * pn_val[i];
            sum += prod/10 + prod%10; // sum each digit, eg 12 would give 1+2=3 
 
        }
        int control = 50 - sum;
        // valid if 10th pn number equals control.
        boolean valid = control == pn[pn.length-1];

        System.out.println("Personnumret är " + (valid ? "korrekt." : "inkorrekt."));
    }
}