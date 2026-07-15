package com.foxbank.template;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transaction {
    public int ID;
    public String customer_ID;
    public int Account_number;
    public String Type;
    public int from_account;
    public int to_account;
    public Time Timestamp;
    public BigDecimal Amount;
    public String Description; //this field is written by customer
    public int RelatedAccountNumber; //for transfer, we use this

    private final LocalDateTime time = LocalDateTime.now();

    public Transaction() {}

    public Transaction(String customer_ID, String type, BigDecimal amount, String description) {
        this.customer_ID = customer_ID;
        this.Type = type;
        this.Amount = amount;
        this.Description = description;
    }

    public Transaction(String customer_ID, int account_number, String type, int from_account, int to_account, BigDecimal amount, String description) {
        this.customer_ID = customer_ID;
        this.Account_number = account_number;
        this.Type = type;
        this.from_account = from_account;
        this.to_account = to_account;
        this.Timestamp = Time.valueOf(String.valueOf(time));
        this.Amount = amount;
        this.Description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getAccount_number() {
        return Account_number;
    }

    public void setAccount_number(int account_number) {
        Account_number = account_number;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getFrom_account() {
        return from_account;
    }

    public void setFrom_account(int from_account) {
        this.from_account = from_account;
    }

    public int getTo_account() {
        return to_account;
    }

    public void setTo_account(int to_account) {
        this.to_account = to_account;
    }

    public Time getTimestamp() { // read only not set, set on
        return Timestamp;
    }

    public void setTimestamp(Time timestamp) {
        Timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getRelatedAccountNumber() {
        return RelatedAccountNumber;
    }

    public void setRelatedAccountNumber(int relatedAccountNumber) {
        RelatedAccountNumber = relatedAccountNumber;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "ID=" + ID +
                ", customer_ID='" + customer_ID + '\'' +
                ", Account_number=" + Account_number +
                ", Type='" + Type + '\'' +
                ", from_account=" + from_account +
                ", to_account=" + to_account +
                ", Timestamp=" + Timestamp +
                ", Amount=" + Amount +
                ", Description='" + Description + '\'' +
                ", time=" + time +
                '}';
    }
}
