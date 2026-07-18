package com.foxbank.customer;

import com.foxbank.DBmanage.DatabaseManager;
import com.foxbank.template.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Transaction_service {
    private final Connection conn;

    public Transaction_service() {
        this.conn = DatabaseManager.getInstance().getConnection();
    }

    public Transaction writeIntoHistory(Transaction transaction) {
        try (PreparedStatement ps = conn.prepareStatement(
        "INSERT INTO history_transactions (customer_ID,account_number,from_account,to_account,timestamp,amount,description)" +
                " VALUES (?,?,?,?,?,?,?);",
        Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, transaction.getCustomer_ID());
            ps.setInt(2, transaction.getAccount_number());
            ps.setInt(3, transaction.getFrom_account());
            ps.setInt(4, transaction.getTo_account());
            ps.setTime(5, transaction.getTimestamp());
            ps.setBigDecimal(4, transaction.getAmount());
            ps.setString(5, transaction.getDescription());
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) transaction.setID(rs.getInt(1));
            return transaction;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Transaction> listAll() {
        List<Transaction> transactionList = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM history_transactions;")) {
            while (rs.next())
                transactionList.add(mapTransactions(rs));
            return transactionList;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Transaction> findByAccountId(int accountID) {
        List<Transaction> transactionList = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM history_transactions WHERE accountId = ? ORDER BY timestamp DESC;")) {
            while (rs.next())
                transactionList.add(mapTransactions(rs));
            return transactionList;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Transaction mapTransactions(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setID(rs.getInt("ID"));
        t.setCustomer_ID(rs.getString("customer_ID"));
        t.setAccount_number(rs.getInt("Account_ID"));
        t.setType(rs.getString("Type"));
        t.setFrom_account(rs.getInt("from_account"));
        t.setTo_account(rs.getInt("to_account"));
        t.setTimestamp(rs.getTime("timestamp"));
        t.setAmount(rs.getBigDecimal("Amount"));
        t.setDescription(rs.getString("description"));
        t.setRelatedAccountNumber(rs.getInt("relatedAccountNumber"));
        return t;
    }
}
