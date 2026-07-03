package com.foxbank.customer;

import com.foxbank.template.Accounts;

import java.sql.Connection;

public class Account_Services {
    private final Connection conn;

    public Account_Services(Connection conn) {
        this.conn = conn;
    }
}
