package foobar;

import java.util.HashMap;
import java.util.HexFormat;
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
 */
public class ExpCalc {
    
    /**
     * Operator interface.
     */
    static abstract class OpBase {
        String pattern; // regex

        OpBase(String op) {
            pattern = "\\x"+HexFormat.of().toHexDigits(op.charAt(0)).substring(2);
        }
        /**
         * Apply operator as L-VALUE OP R-VALUE.
         * @param left Left as double.
         * @param right Right as double.
         * @return Resulting value.
         */
        abstract double operate(double left, double right);
    }

    /** Multiplication */
    static class MulOp extends OpBase {
        MulOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left * right;
        }
    }

    /** Division */
    static class DivOp extends OpBase {
        DivOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left / right;
        }
    }

    /** Subtraction */
    static class SubOp extends OpBase {
        SubOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left - right;
        }
    }

    /** Addition */
    static class AddOp extends OpBase {
        AddOp(String op) { super(op); }

        @Override
        public double operate(double left, double right)
        {
            return left + right;
        }
    }

    /** Operators in reverse priority order. */
    private static final String[] OP_LIST = {"+","-","/","*"};
    /** Map of operator. */
    private static final HashMap<String,OpBase> OP_MAP;
    
    /* Initialize operator map. */
    static {
        OP_MAP = new HashMap<>();
        OP_MAP.put("*", new MulOp("*"));
        OP_MAP.put("/", new DivOp("/"));
        OP_MAP.put("-", new SubOp("-"));
        OP_MAP.put("+", new AddOp("+"));
    }
    
    /**
     * Program main.
     * @param args
     */
    public static void main(String[] args) {
        
        // With scanner...
        try(Scanner in = new Scanner(System.in)) {

            // Calculator loop.
            while(true) {
                // Get expression.
                System.out.print("Input expression: ");
                var exp = in.nextLine();

                // Evaluate and print.
                System.out.println("Result: " + evaluate(exp));
            }
        }
        catch(Exception ex) {
            // On fail...
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Get first lowest priority operator. 
     * @param exp Explession
     * @return Op or null
     */
    private static OpBase getOp(String exp) {
        for(String op : OP_LIST) {
            if(exp.contains(op)) {
                return OP_MAP.get(op);
            }
        }
        return null;
    }

    /**
     * Evaluate an expression down to a double.
     * @param exp String
     * @return result as double
     */
    private static double evaluate(String exp)
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
}
