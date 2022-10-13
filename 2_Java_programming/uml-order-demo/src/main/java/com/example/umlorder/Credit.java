package com.example.umlorder;

import java.math.BigDecimal;
import java.util.Date;

public class Credit extends Payment {
    private String name;
    private String type;
    private long expDate;

    protected Credit(double amount, String name, String type, Date expDate)
    {
        super(amount);
        this.name = name;
        this.type = type;
        this.expDate = expDate.getTime();
    }
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return the expDate
     */
    public Date getExpDate()
    {
        return new Date(expDate);
    }

    @Override
    public double getPayment()
    {
        return expDate >= new Date().getTime() ? super.getPayment() : 0;
    }
}
