package com.example.application;

import java.sql.*;
import java.util.ArrayList;

public class UpdateSeatQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public UpdateSeatQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public void performDatabaseQuery(int flightNumber, int seatNumber, boolean setAvailable) {
        int availabilityInput = setAvailable? 1 : 0; // 0 for unavailable and 1 for available
        String query = "update SEATS " +
                    "set isAvailable = " + availabilityInput + " " + 
                    "where SEATS.FlightNumber = " + flightNumber + " and SEATS.SeatNumber = " + seatNumber + ";";
        
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updated seats");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}