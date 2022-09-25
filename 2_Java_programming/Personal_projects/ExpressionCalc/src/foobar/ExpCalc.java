package foobar;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Evaluate simple expressions.
 * Supported binary operators in priority order: * / - +
 * Can not handle unary negative operator, ie. -1.
 * 
 * Example:
 * 
 * Input expression: 8 * 2 + 9 - 10
 * Result: 15.0
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class ExpCalc {

      /** Operators in reverse priority order. */
      private static final String[] OP_LIST = {"+","-","/","*"};

 
    /** Multiplication */
    private class MulOp extends OpBase {
        MulOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left * right;
        }
    }

    /** Division */
    private class DivOp extends OpBase {
        DivOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left / right;
        }
    }

    /** Subtraction */
    private class SubOp extends OpBase {
        SubOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left - right;
        }
    }

    /** Addition */
    private class AddOp extends OpBase {
        AddOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left + right;
        }
    }

    /** Map of operator. */
    private final HashMap<String,OpBase> opMap;
    
    private ExpCalc() {
        /* Initialize operator map. */
        opMap = new HashMap<>();
        opMap.put("*", new MulOp("*"));
        opMap.put("/", new DivOp("/"));
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
     * @param exp String
     * @return result as double
     */
    private double evaluate(String exp)
    {
        var _exp = exp.strip(); // remove spaces
        var op = getOp(_exp); 
        if(op == null) {
            // no op: treat as value
            return Double.parseDouble(_exp);
        }
        else {
            // op found: apply operator on list of expressions
            var parts = _exp.split(op.pattern);
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
        
        var calc = new ExpCalc();

        // With scanner...
        try(Scanner in = new Scanner(System.in)) {

            // do calculator loop.
            while(true) {
                // Get expression.
                System.out.print("Input expression: ");
                var exp = in.nextLine();

                // Evaluate and print.
                System.out.println("Result: " + calc.evaluate(exp));
            }
        }
        catch(Exception ex) {
            // On fail...
            System.out.println(ex.getMessage());
        }
    }
}
