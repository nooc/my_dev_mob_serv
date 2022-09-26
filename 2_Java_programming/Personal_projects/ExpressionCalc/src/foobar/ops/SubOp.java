package foobar.ops;

import foobar.OpBase;

/** Subtraction */
public class SubOp extends OpBase {
    public SubOp(String op) { super(op); }

    @Override
    public double operate(double left, double right)
    {
        return left - right;
    }
}