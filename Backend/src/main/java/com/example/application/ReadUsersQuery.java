package com.example.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ReadUsersQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public ReadUsersQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public User getUserByUsername(String username) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE Username = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                int userID = results.getInt("UserID");
                String retrievedUsername = results.getString("Username");
                String password = results.getString("Password");
                String userType = results.getString("UserType");

                user = new User(userID, retrievedUsername, password, userType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getRegisteredUser(String emailAddress) {
        User registeredUser = null;
        try {
            String query = "SELECT * FROM REGISTEREDUSERS WHERE EmailAddress = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, emailAddress);

            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                int userId = results.getInt("UserId");
                String email = results.getString("EmailAddress");
                String password = results.getString("ThePassWord");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String phoneNumber = results.getString("PhoneNumber");
                int points = results.getInt("Points");

                registeredUser = new User(userId, email, password, firstName, lastName, phoneNumber, points);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registeredUser;
    }

    public List<User> getAllRegisteredUsers() {
        List<User> registeredUsers = new ArrayList<>();
        try {
            String query = "SELECT * FROM REGISTEREDUSERS";
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int userId = results.getInt("UserId");
                String email = results.getString("EmailAddress");
                String password = results.getString("ThePassWord");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String phoneNumber = results.getString("PhoneNumber");
                int points = results.getInt("Points");

                User registeredUser = new User(userId, email, password, firstName, lastName, phoneNumber, points);
                registeredUsers.add(registeredUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registeredUsers;
    }
}
