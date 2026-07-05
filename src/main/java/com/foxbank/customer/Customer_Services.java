package com.foxbank.customer;

import com.foxbank.DBmanage.DatabaseManager;
import com.foxbank.template.Customers;

import java.sql.*;

public class Customer_Services {
    private final Connection conn;

    public Customer_Services() {
        this.conn = DatabaseManager.getInstance().getConnection();
    }

    public Customers save(Customers customers) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO bank_customers (Name,Age,address,phone) VALUES (?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customers.getName());
            ps.setInt(2, customers.getAge());
            ps.setString(3, customers.getAddress());
            ps.setString(4, customers.getPhone());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) customers.setID(rs.getInt(1));
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException("failed to save customer: "+e.getMessage());
        }
    }
}
