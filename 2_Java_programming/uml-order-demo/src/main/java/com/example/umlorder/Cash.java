package com.example.umlorder;

public class Cash extends Payment {
    private double cashTendered;

    public Cash(double amount, double cashTendered) {
        super(amount);
        this.cashTendered = cashTendered;
    }

    /**
     * @return the cashTendered
     */
    public double getCashTendered()
    {
        return cashTendered;
    }
}
