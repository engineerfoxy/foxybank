package com.foxbank.customer;

import com.foxbank.DBmanage.DatabaseManager;
import com.foxbank.template.Customers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServices {
    private final Connection conn;

    public CustomerServices() {
        this.conn = DatabaseManager.getInstance().getConnection();
    }

    public Customers Save(Customers customers) {
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

    public Customers Remove(Customers customers) {
        try (PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM bank_customers WHERE Name = ?;"
        )) {
            ps.setString(1, customers.getName());
            ps.execute();
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customers findAllInDbById(int ID) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM bank_customers WHERE ID = ?;")) {
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapCustomer(rs);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Customers> findAllInDb() {
        List<Customers> list_all = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM bank_customers")) {
                while (rs.next())
                    list_all.add(mapCustomer(rs));
                return list_all;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Customers mapCustomer(ResultSet rs) throws SQLException {
        Customers c = new Customers();
        c.setID(rs.getInt("ID"));
        c.setName(rs.getString("Name"));
        c.setAge(rs.getInt("Age"));
        c.setAddress(rs.getString("Address"));
        c.setPhone(rs.getString("Phone"));
        return c;
    }
}
