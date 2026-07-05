package com.foxbank.customer;

import com.foxbank.DBmanage.DatabaseManager;
import com.foxbank.template.Accounts;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Account_Services {
    private final Connection conn;
    PreparedStatement ps;
    Statement stmt;

    DatabaseManager databaseManager = DatabaseManager.getInstance();

    public Account_Services(Connection conn) {
        this.conn = DatabaseManager.getInstance().getConnection();
    }

    public Accounts add_to_db() {

        return null;
    }

    public Accounts del_from_db() {

        return null;
    }

    public Accounts find_by_id(int account_id) {

        return null;
    }

    public List<Accounts> Listing_all() {

        return null;
    }
}
