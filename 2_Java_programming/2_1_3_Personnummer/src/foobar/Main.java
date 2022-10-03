package foobar;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class Main {

    /** Mainprogram.
     * 
     * Read in and verify a swedish personal id number.
     * Input format: [YY]YYMMDD[-]NNNN.
     * @param args
     */
    public static void main(String[] args) {
        
        try(Scanner input = new Scanner(System.in)) {
            var pnPattern = Pattern.compile("^(?:19|20)?([0-9]{6})\\-?([0-9]{4})$");
            
            int[] intPnArr = new int[10]; // store pn as ints

            // input loop
            while(true) {
                // get
                System.out.print("Swedish personal id number: ");
                String pnStr = input.nextLine().trim();
                // match
                var matchObj = pnPattern.matcher(pnStr);
                if(matchObj.matches()) {
                    var part1 = matchObj.group(1).toCharArray();
                    var part2 = matchObj.group(2).toCharArray();
                    // convert ascii to int array by subtracting ascii value for "0"
                    // from the char value, ie offset them to 0.
                    int pos = 0;
                    for (char c : part1) {
                        intPnArr[pos++] =  (int)c-48; // ascii for "0" is 48.
                    }
                    for (char c : part2) {
                        intPnArr[pos++] =  (int)c-48;
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
            int[] mulTable = {2,1,2,1,2,1,2,1,2}; // multiplication table
            int sum = 0;
            for (int i = 0; i < mulTable.length; ++i) {
                int product = intPnArr[i] * mulTable[i];
                sum += product / 10 + product % 10; // sum each digit, eg 12 would give 1+2=3 
    
            }
            int control = 50 - sum;
            // valid if 10th pn number equals control.
            boolean valid = control == intPnArr[intPnArr.length-1];

            System.out.println("Personal id number is " + (valid ? "correct." : "incorrect."));
        }
    }
}