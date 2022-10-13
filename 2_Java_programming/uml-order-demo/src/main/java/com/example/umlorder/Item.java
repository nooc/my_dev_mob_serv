package com.example.umlorder;

import java.math.BigDecimal;

public class Item {

    private double shippingWeight;
    private String description;
    private double price;
    private double tax;

    public Item(double price, double tax, double shippingWeight, String description) {
        this.price = price;
        this.tax = tax;
        this.shippingWeight = shippingWeight;
        this.description = description;
    }

    public BigDecimal getPriceForQuantity(int quantity) {
        return BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(quantity));
    }

    public double getTax() {
        return tax;
    }

    public int inStock() {
        return 1;
    }

    /**
     * @return the shippingWeight
     */
    public double getShippingWeight()
    {
        return shippingWeight;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }
}
