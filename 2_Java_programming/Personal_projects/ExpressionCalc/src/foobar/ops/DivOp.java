package foobar.ops;

import foobar.OpBase;

/** Division */
public class DivOp extends OpBase {
    public DivOp(String op) { super(op); }

    @Override
    public double operate(double left, double right)
    {
        return left / right;
    }
}