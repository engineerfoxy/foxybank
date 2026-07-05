package com.foxbank.customer;

import java.sql.*;
import java.util.List;

import com.foxbank.DBmanage.DatabaseManager;
import com.foxbank.template.Accounts;
import com.foxbank.DBmanage.gen_rand_numb;
import com.foxbank.template.Customers;

public class Account_Services {
    private final Connection conn;
    PreparedStatement ps;
    Statement stmt;

    gen_rand_numb ID_generator;

    DatabaseManager databaseManager = DatabaseManager.getInstance();

    public Account_Services() {
        this.conn = DatabaseManager.getInstance().getConnection();
    }

    public Accounts save(Accounts accounts) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO bank_accounts (bank_accountID,bank_customerID,bank_accountType,bank_balance,bank_accountStatus) VALUES (?, ?, ?, ?, ?, ?);")
        ) {
            ps.setString(1, accounts.getAccount_ID());
            ps.setInt(2, accounts.getCustomer_ID());
            ps.setString(3,accounts.getAccount_Type());
            ps.setBigDecimal(4, accounts.getBalance());
            ps.setString(5, accounts.getStatus());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) accounts.setID(rs.getInt(1));
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Accounts remove() {

        return null;
    }

    public Accounts find_by_id(int account_id) {

        return null;
    }

    public List<Accounts> Listing_all() {

        return null;
    }
}
