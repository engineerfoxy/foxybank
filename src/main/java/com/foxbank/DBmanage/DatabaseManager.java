package com.foxbank.DBmanage;

import java.sql.*;

public class DatabaseManager {
    public static final String DB_URL = "jdbc:sqlite:bankdb.db";
    public static final String DB_USERNAME = "myusername";
    public static final String DB_PASSWORD = "mypassword";

    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return connection;
    }

    public void createTables() {

    }
}
