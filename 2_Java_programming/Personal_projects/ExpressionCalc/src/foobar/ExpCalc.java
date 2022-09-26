package foobar;

import java.util.HashMap;
import java.util.Scanner;

import foobar.ops.*;

/**
 * Evaluate simple expressions.
 * Supported binary operators in priority order: * / - +
 * 
 * Example:
 * 
 * Input expression: 8 * 2 + 9 - 10
 * Result: 15.0
 * 
 * The above expression is evaluated in operator priority order:
 * 1: (8 * 2) + (9 - 10)
 * 2:      16 + (9 - 10)
 * 3:      16 + -1
 * 4:      15
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class ExpCalc {

      /** Operators in reverse priority order. */
      private static final String[] OP_LIST = {"+","-","%","/","*"};

 
    /** Map of operators. */
    private final HashMap<String,OpBase> opMap;
    
    private ExpCalc() {
        /* Initialize operator map. */
        opMap = new HashMap<>();
        opMap.put("*", new MulOp("*"));
        opMap.put("/", new DivOp("/"));
        opMap.put("%", new DivOp("%"));
        opMap.put("-", new SubOp("-"));
        opMap.put("+", new AddOp("+"));

    }
    
    /**
     * Get first lowest priority operator. 
     * @param exp Explession
     * @return Op or null
     */
    private OpBase getOp(String exp) {
        for(String op : OP_LIST) {
            if(exp.contains(op)) {
                return opMap.get(op);
            }
        }
        return null;
    }

    /**
     * Evaluate an expression down to a double.
     * @param expression String
     * @return result as double
     */
    private double evaluate(String expression)
    {
        var expr = expression.strip(); // remove spaces
        var op = getOp(expr); // get first lowest priority op
        if(op == null) {
            // no op: treat as value
            return Double.parseDouble(expr);
        }
        else {
            // op found: apply operator on list of expressions
            // by splitting expression string using the current op.
            var parts = expr.split(op.pattern);
            assert parts.length > 1: "Operation is missing a value.";
            double res = evaluate(parts[0]); // Starting L-VALUE
            // perform operations
            for(int idx = 1; idx < parts.length; ++idx) {
                res = op.operate(res, evaluate(parts[idx]));
            }
            return res;
        }
    }

    /**
     * Application main.
     * @param args
     */
    public static void main(String[] args) {
        
        var expCalc = new ExpCalc();

        // With scanner...
        try(Scanner in = new Scanner(System.in)) {

            // do calculator loop.
            while(true) {
                // Get expression.
                System.out.print("Input expression: ");
                var exp = in.nextLine();

                // Evaluate and print.
                System.out.println("Result: " + expCalc.evaluate(exp));
            }
        }
        catch(Exception ex) {
            // On fail...
            System.out.println(ex.getMessage());
        }
    }
}
