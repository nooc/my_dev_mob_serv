package com.example.umlorder;


public class Check extends Payment {
    private String name;
    private String bankId;
    
    public Check(double amount, String name, String bankId) {
        super(amount);

        this.name = name;
        this.bankId = bankId;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the bankId
     */
    public String getBankId()
    {
        return bankId;
    }
}
