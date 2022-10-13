package com.example.umlorder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // create items

        var whey = new Item(149.9, 0.12, 1.1, "Whey Proteine Deluxe");
        var dumbell = new Item(659.9, 0.25, 5.2, "Dumbells, 15kg");
        var keyboard = new Item(709.0, 0.25, 0.8, "Keyboard, VPRO V700 RGB");

        // create a customer

        Customer customer = new Customer("Customer Customersson", "Customer Alley 42, 12345 Customer Town");

        // create an order

        Order order = new Order(new Date());

        // add order details

        OrderDetail detail = new OrderDetail(1, "normal");
        detail.addItem(whey);
        detail.addItem(dumbell);
        order.addOrderDetail(detail);

        detail = new OrderDetail(2, "normal");
        detail.addItem(keyboard);
        order.addOrderDetail(detail);

        // attach to customer

        customer.addOrder(order);

        // add payments

        order.pay(new Cash(500, 500));
        order.pay(new Check(1000, "Gift cheque", "HANDSESS"));

        // print customer with order (status should be unpayed)

        printCustomer(customer);

        // add appitional payment

        order.pay(new Credit(4000, "My credit card", "card-credit",
            new Date(new Date().getTime()+432000000))
        );

        // print customer with order (status should be payed)
        printCustomer(customer);
    }

    /**
     * Print customer.
     * @param customer
     */
    private static void printCustomer(Customer customer)
    {
        System.out.format("""
            --------------------
            CUSTOMER
                Name: %s
             Address: %s

            ORDERS
            """,
            customer.getName(),
            customer.getAddress()
        );
        for(var order : customer.getOrders()) {
            BigDecimal orderTotal = BigDecimal.ZERO;
            System.out.format("""
                      Date: %s
                    Status: %s

                    """,
                    order.getDate().toString(),
                    order.getStatus() == 0 ? "Unpayed" : "Payed"
            );
            for(var detail : order.getDetails()) {
                for(var item : detail.getItems()) {
                    System.out.format("|%dx %s - %s kr\n", detail.getQuantity(), item.getDescription(), item.getPrice());
                }
                System.out.format("""
                    |Sub-Total: %s
                    |      Tax: %s

                    """,
                        detail.calcSubTotal(),
                        detail.calcTax()
                );
                orderTotal = orderTotal.add(detail.calcTotal());
            }
            System.out.format("""

                    Order-Total: %s
                    --------------------
                    """, orderTotal);
        }
    }
}
