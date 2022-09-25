package foobar;

import java.util.HexFormat;

/**
 * Operator interface.
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
abstract class OpBase {
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