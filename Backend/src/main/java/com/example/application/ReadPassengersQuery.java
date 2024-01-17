package com.example.application;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadPassengersQuery {

    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public ReadPassengersQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public List<Passenger> getListByFlight(int flightId) {
        List<Passenger> passengers = new ArrayList<>();

        try {
            String query = "SELECT FlightNumber,TicketID,UserID,FirstName,LastName,EmailAddress,SeatNumber FROM TICKETS WHERE FlightNumber = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, flightId);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                int rFlightID = results.getInt("FlightNumber");
                int rTicketID = results.getInt("TicketID");
                int rUserID = results.getInt("UserID");
                String rFname = results.getString("FirstName");
                String rLname = results.getString("LastName");
                String rEmail = results.getString("EmailAddress");
                int rSeat = results.getInt("SeatNumber");

                Passenger tempassenger = new Passenger(rFlightID,rUserID,rTicketID,rFname,rLname,rEmail,rSeat);
                passengers.add(tempassenger);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }


}
