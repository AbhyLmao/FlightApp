package com.example.application;

import java.sql.*;
import java.util.ArrayList;

public class ReadSeatsQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public ReadSeatsQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public ArrayList<Seat> performDatabaseQuery(int flightNumber) {
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        try {
            String query = "select SEATS.* from SEATS where SEATS.FlightNumber = " + flightNumber + ";";
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int seatID = Integer.parseInt(results.getString("SeatID"));
                int seatNumber = Integer.parseInt(results.getString("SeatNumber"));
                boolean isAvailable = (1 == Integer.parseInt(results.getString("isAvailable")));
                Seat seat = new Seat(seatID, seatNumber, flightNumber, isAvailable);
                seatList.add(seat);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return seatList;
    }

}