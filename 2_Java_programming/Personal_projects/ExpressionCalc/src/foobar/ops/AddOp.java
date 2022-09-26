package foobar.ops;

import foobar.OpBase;

/** Addition */
public class AddOp extends OpBase {
    public AddOp(String op) { super(op); }

    @Override
    public double operate(double left, double right)
    {
        return left + right;
    }
}