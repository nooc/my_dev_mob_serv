package foobar;

import java.util.HexFormat;

/**
 * Operator interface.
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public abstract class OpBase {

    // regex for splitting expression strings
    String pattern;

    /**
     * Constructor
     * Creates split pattern for op.
     * @param op String representation of op character.
     */
    protected OpBase(String op) {
        pattern = "\\x"+HexFormat.of().toHexDigits(op.charAt(0)).substring(2);
    }
    /**
     * Apply operator as L-VALUE OP R-VALUE.
     * @param left Left as double.
     * @param right Right as double.
     * @return Resulting value.
     */
    public abstract double operate(double left, double right);
}