package com.example.umlorder;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private String name;
    private String address;
    private List<Order> orders;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.orders = new LinkedList<>();
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param order
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * @return the orders
     */
    public List<Order> getOrders()
    {
        return orders;
    }
}
