package com.example.application;

import java.sql.*;
import java.util.ArrayList;

public class TicketsQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public TicketsQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public ArrayList<Ticket> getTickets(String email) {
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        try {
            String query = "select TICKETS.*, FLIGHTS.*, AIRCRAFTS.*, L1.Latitude AS OriginLatitude, L1.Longitude AS OriginLongitude, " +
                            "L2.Latitude AS DestinationLatitude, L2.Longitude AS DestinationLongitude " +
                            "from TICKETS " +
                            "join FLIGHTS on TICKETS.FlightNumber = FLIGHTS.FlightNumber " +
                            "join AIRCRAFTS on FLIGHTS.AircraftID = AIRCRAFTS.AircraftID " +
                            "JOIN LOCATIONS L1 ON FLIGHTS.Origin = L1.CityName " +
                            "JOIN LOCATIONS L2 ON FLIGHTS.Destination = L2.CityName " +
                            "where TICKETS.EmailAddress = \"" + email + "\";";
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int aircraftID = Integer.parseInt(results.getString("AircraftID"));
                String modelName = results.getString("ModelName");
                Aircraft aircraft = new Aircraft(aircraftID, modelName);


                String originName = results.getString("Origin");
                double originLatitude = Double.parseDouble(results.getString("OriginLatitude"));
                double originLongitude = Double.parseDouble(results.getString("OriginLongitude"));
                Location origin = new Location(originName, originLatitude, originLongitude);

                String destinationName = results.getString("Destination");
                double destinationLatitude = Double.parseDouble(results.getString("DestinationLatitude"));
                double destinationLongitude = Double.parseDouble(results.getString("DestinationLongitude"));
                Location destination = new Location(destinationName, destinationLatitude, destinationLongitude);

                String departureTime = results.getString("DepartureTime");
                String arrivalTime = results.getString("ArrivalTime");
                ArrayList<Integer> departureInfo = parseTime(departureTime);
                ArrayList<Integer> arrivalInfo = parseTime(arrivalTime);

                int flightNumber = Integer.parseInt(results.getString("FlightNumber"));
                Flight flightDetails = new Flight(flightNumber, departureInfo, arrivalInfo, origin, destination, aircraft);

                int ticketID = Integer.parseInt(results.getString("TicketID"));
                int price = Integer.parseInt(results.getString("Price"));
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                int seatNumber = Integer.parseInt(results.getString("SeatNumber"));
                String emailAddress = results.getString("EmailAddress");

                Ticket ticket = new Ticket(ticketID, price, firstName, lastName, flightDetails, seatNumber, false, emailAddress);
                ticketList.add(ticket);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    public void removeTicket(int id) {
        try {
            String query = "DELETE FROM TICKETS WHERE TICKETS.TicketID = " + id + ";";
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE FROM table_name WHERE condition;

    private ArrayList<Integer> parseTime(String stringToParse) {
        String[] result = stringToParse.split("\\s");
        String[] date = result[0].split("-");
        String[] time = result[1].split(":");
        ArrayList<Integer> parsedTime = new ArrayList<Integer>();
        
        for (String item: date) {
            parsedTime.add(Integer.parseInt(item));
        }
        for (String item: time) {
            parsedTime.add(Integer.parseInt(item));
        }
        return parsedTime;
    }

}