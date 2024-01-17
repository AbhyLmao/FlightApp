package com.example.application;

import java.sql.*;
import java.util.ArrayList;

public class InsertTicketQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public InsertTicketQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public void performDatabaseQuery(int price, String firstName, String lastName, int flightNumber, int seatNumber, String emailAddress, int userID) {
        String query;
        if (userID < 1) {
            query = "insert into TICKETS (Price, FirstName, LastName, FlightNumber, SeatNumber, EmailAddress, UserID) " +
                    "values (" + price + ", \"" + firstName + "\", \"" + lastName + "\", " + flightNumber + ", " + seatNumber +
                    ", \"" + emailAddress + "\", null);";
        }
        else {
            query = "insert into TICKETS (Price, FirstName, LastName, FlightNumber, SeatNumber, CancellationInsurance, EmailAddress, UserID) " +
                    "values (" + price + ", \"" + firstName + "\", \"" + lastName + "\", " + flightNumber + ", " + seatNumber + ", \"" +
                    emailAddress + "\", "+ userID + ");";
        }
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Added tickets");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}