package foobar.ops;

import foobar.OpBase;

/** Modulo */
public class ModOp extends OpBase {
    public ModOp(String op) { super(op); }

    @Override
    public double operate(double left, double right)
    {
        return left % right;
    }
}