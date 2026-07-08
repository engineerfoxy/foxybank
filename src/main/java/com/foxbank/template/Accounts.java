package com.foxbank.template;

import java.math.BigDecimal;

public class Accounts {
    public int ID; //for database not anything else
    public String Account_ID;
    public int Customer_ID;
    public String Account_Type;
    public BigDecimal Balance;
    public String Status;

    public Accounts() {}

    public Accounts(String account_ID) {
        this.Account_ID = account_ID;
        this.Status = "ACTIVE";
    }

    public Accounts(int customer_ID, String account_ID, String account_Type) {
        this.Customer_ID = customer_ID;
        this.Account_ID = account_ID;
        this.Account_Type = account_Type;
        this.Balance = BigDecimal.ZERO;
        this.Status = "ACTIVE";
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(String account_ID) {
        Account_ID = account_ID;
    }

    public String getAccount_Type() {
        return Account_Type;
    }

    public void setAccount_Type(String account_Type) {
        Account_Type = account_Type;
    }

    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Accounts {" +
                "ID=" + ID +
                ", Account_ID='" + Account_ID + '\'' +
                ", Account_Type='" + Account_Type + '\'' +
                ", Balance=" + Balance +
                ", Status='" + Status + '\'' +
                '}';
    }
}
