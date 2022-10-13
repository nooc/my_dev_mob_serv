package com.example.umlorder;


public abstract class Payment {
    protected final double payment;

    protected Payment(double amount) {
        this.payment = amount;
    }

    /**
     * @return the payment
     */
    public double getPayment() {
        return payment;
    }
}
