package com.example.application;

import java.sql.*;

public class DatabaseConnector {
    private static DatabaseConnector onlyInstance;
   
    private DatabaseConnector() {

    }

    public static DatabaseConnector getOnlyInstance() {
        if (onlyInstance == null) {
            onlyInstance = new DatabaseConnector();
        }
        return onlyInstance;
    }

    public Connection startConnection() {
        Connection dbConnection = null;
        try {
            // This connection is going to be different for every user.
            // Make sure to change the url, user, and password.
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "password"); // Change this line as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
