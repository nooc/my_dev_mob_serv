package foobar.ops;

import foobar.OpBase;

/** Multiplication */
public class MulOp extends OpBase {
    public MulOp(String op) { super(op); }

    @Override
    public double operate(double left, double right)
    {
        return left * right;
    }
}