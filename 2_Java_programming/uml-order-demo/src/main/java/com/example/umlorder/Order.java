package com.example.umlorder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {

    public static final int INVALID = 0;
    public static final int UNPAYED = 0;
    public static final int PAYED = 0;

    private List<OrderDetail> detalis;

    private long date;
    private long status;
    private List<Payment> payments;

    public Order(Date date) {
        this.date = date.getTime();
        this.status = INVALID;
        this.detalis = new LinkedList<>();
        this.payments = new LinkedList<>();
    }

    public BigDecimal calcSubTotal() {
        BigDecimal sub = BigDecimal.ZERO;
        for(var detail : detalis) {
            sub = sub.add(detail.calcSubTotal());
        }
        return sub;
    }

    public BigDecimal calcTax() {
        BigDecimal tax = BigDecimal.ZERO;
        for(var detail : detalis) {
            tax = tax.add(detail.calcTax());
        }
        return tax;
    }

    public BigDecimal calcTotal() {
        return  calcSubTotal().add(calcTax());
    }

    public double calcTotalWeight() {
        double weight = 0;
        for(var detail : detalis) {
            weight += detail.calcWeight();
        }
        return weight;
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return new Date(date);
    }

    /**
     * @return the status
     */
    public long getStatus()
    {
        var total = calcTotal();
        for(var payment : payments) {
            total = total.subtract(BigDecimal.valueOf(payment.getPayment()));
            if(total.signum() <= 0) {
                status = PAYED;
                break;
            }
        }
        return UNPAYED;
    }

    public void addOrderDetail(OrderDetail detail)
    {
        detalis.add(detail);
        status = getStatus();
    }

    public void pay(Payment payment) {

        payments.add(payment);
    }

    public List<OrderDetail> getDetails()
    {
        return detalis;
    }
}