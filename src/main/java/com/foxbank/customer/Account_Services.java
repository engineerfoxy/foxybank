package com.foxbank.customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foxbank.DBmanage.DatabaseManager;
import com.foxbank.template.Accounts;
import com.foxbank.template.Customers;
import com.foxbank.template.gen_rand_numb;

public class Account_Services {
    private final Connection conn;

    public Account_Services() {
        this.conn = DatabaseManager.getInstance().getConnection();
    }

    public Accounts save(Accounts accounts) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO bank_accounts (bank_accountID,bank_customerID,bank_accountType,bank_balance,bank_accountStatus) VALUES (?, ?, ?, ?, ?);")
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

    public Accounts active_disable_account(Accounts accounts) {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT bank_accountStatus FROM bank_accounts;"
        )) {
            ps.setString(1, accounts.getAccount_ID());
            ps.execute();
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Accounts find_by_id(int account_id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM bank_accounts WHERE bank_accountID = ?;")) {
            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapAccounts(rs);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Accounts> Listing_all() {
        List<Accounts> list_all = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM bank_accounts")) {
            while (rs.next())
                list_all.add(mapAccounts(rs));
            return list_all;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Accounts mapAccounts(ResultSet rs) throws SQLException {
        Accounts a = new Accounts();
        a.setID(rs.getInt("ID"));
        a.setAccount_ID(rs.getString("bank_accountID"));
        a.setAccount_Type(rs.getString("bank_customerID"));
        a.setBalance(rs.getBigDecimal("bank_balance"));
        a.setStatus(rs.getString("bank_accountStatus"));
        return a;
    }

    public void updateBalance(Accounts accounts) {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE bank_accounts SET bank_balance =? , bank_accountStatus = ? WHERE ID = ?"
        )) {
            ps.setBigDecimal(1, accounts.getBalance());
            ps.setString(2, accounts.getStatus());
            ps.setInt(3, accounts.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
