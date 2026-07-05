package com.foxbank.DBmanage;

import java.sql.*;

public class DatabaseManager {
    public static final String DB_URL = "jdbc:sqlite:bankdb.db";

    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL);
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
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return connection;
    }

    public void createTables() {
        String SQLPrompt_1 = "";
        String SQLPrompt_2 = "";
        String SQLPrompt_3 = "";
    }
}
