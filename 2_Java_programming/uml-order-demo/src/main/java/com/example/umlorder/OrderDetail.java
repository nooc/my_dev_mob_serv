package com.example.umlorder;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class OrderDetail {
    private int quantity;
    private String taxStatus;
    private List<Item> items;

    public OrderDetail(int quantity, String taxStatus) {
        this.quantity = quantity;
        this.taxStatus = taxStatus;
        this.items = new LinkedList<>();
    }

    /**
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    public BigDecimal calcSubTotal() {
        BigDecimal sub = BigDecimal.ZERO;
        for(var item : items) {
            sub = sub.add(BigDecimal.valueOf(item.getPrice()));
        }
        return sub.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal calcTax() {
        BigDecimal tax = BigDecimal.ZERO;
        for(var item : items) {
            tax = tax.add(
                BigDecimal.valueOf(item.getTax()).multiply(BigDecimal.valueOf(item.getPrice()))
                );
        }
        return tax.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal calcTotal() {
        return calcSubTotal().add(calcTax());
    }

    public double calcWeight() {
        double weight = 0;
        for(var item : items) {
            weight += item.getShippingWeight();
        }
        return weight;
    }

    /**
     * @return the taxStatus
     */
    public String getTaxStatus()
    {
        return taxStatus;
    }

    /**
     * @return the items
     */
    public List<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
