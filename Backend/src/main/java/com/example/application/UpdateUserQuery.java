package com.example.application;

import java.sql.*;
import java.util.ArrayList;

public class UpdateUserQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public UpdateUserQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public void addRegisteredUser(String emailAddress, String password, String firstName, String lastName, String phoneNumber) {
        String query = "INSERT INTO REGISTEREDUSERS(EmailAddress, ThePassWord, FirstName, LastName, PhoneNumber, Points) VALUES\n" +
                "(?, ?, ?, ?, ?, 0);";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, emailAddress);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}