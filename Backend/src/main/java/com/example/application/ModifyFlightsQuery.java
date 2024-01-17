package com.example.application;

import java.sql.*;
import java.util.ArrayList;

public class ModifyFlightsQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public ModifyFlightsQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public ArrayList<Flight> performDatabaseQuery() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery("SELECT FLIGHTS.*, " +
                                                    "L1.Latitude AS OriginLatitude, L1.Longitude AS OriginLongitude, " +
                                                    "L2.Latitude AS DestinationLatitude, L2.Longitude AS DestinationLongitude, " +
                                                    "AIRCRAFTS.ModelName " +
                                                    "FROM FLIGHTS " +
                                                    "JOIN AIRCRAFTS ON FLIGHTS.AircraftID = AIRCRAFTS.AircraftID " +
                                                    "JOIN LOCATIONS L1 ON FLIGHTS.Origin = L1.CityName " +
                                                    "JOIN LOCATIONS L2 ON FLIGHTS.Destination = L2.CityName;");

            while (results.next()) {
                int flightNumber = Integer.parseInt(results.getString("FlightNumber"));
                String departureTime = results.getString("DepartureTime");
                String arrivalTime = results.getString("ArrivalTime");
                String originName = results.getString("Origin");
                double originLatitude = Double.parseDouble(results.getString("OriginLatitude"));
                double originLongitude = Double.parseDouble(results.getString("OriginLongitude"));
                String destinationName = results.getString("Destination");
                double destinationLatitude = Double.parseDouble(results.getString("DestinationLatitude"));
                double destinationLongitude = Double.parseDouble(results.getString("DestinationLongitude"));
                int aircraftID = Integer.parseInt(results.getString("AircraftID"));
                String modelName = results.getString("ModelName");

                ArrayList<Integer> departureInfo = parseTime(departureTime);
                ArrayList<Integer> arrivalInfo = parseTime(arrivalTime);
                Aircraft aircraft = new Aircraft(aircraftID, modelName);
                Location origin = new Location(originName, originLatitude, originLongitude);
                Location destination = new Location(destinationName, destinationLatitude, destinationLongitude);
                
                Flight flight = new Flight(flightNumber, departureInfo, arrivalInfo, origin, destination, aircraft);
                flightList.add(flight);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flightList;
    }

    public Flight getFlightDetails(int flightNumber) {
        Flight flightDetails = null;
        try {
            String query = "SELECT FLIGHTS.*, " +
                    "L1.Latitude AS OriginLatitude, L1.Longitude AS OriginLongitude, " +
                    "L2.Latitude AS DestinationLatitude, L2.Longitude AS DestinationLongitude, " +
                    "AIRCRAFTS.ModelName " +
                    "FROM FLIGHTS " +
                    "JOIN AIRCRAFTS ON FLIGHTS.AircraftID = AIRCRAFTS.AircraftID " +
                    "JOIN LOCATIONS L1 ON FLIGHTS.Origin = L1.CityName " +
                    "JOIN LOCATIONS L2 ON FLIGHTS.Destination = L2.CityName " +
                    "WHERE FLIGHTS.FlightNumber = ?";

            try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
                statement.setInt(1, flightNumber);

                ResultSet results = statement.executeQuery();

                if (results.next()) {
                    String departureTime = results.getString("DepartureTime");
                    String arrivalTime = results.getString("ArrivalTime");
                    String originName = results.getString("Origin");
                    double originLatitude = Double.parseDouble(results.getString("OriginLatitude"));
                    double originLongitude = Double.parseDouble(results.getString("OriginLongitude"));
                    String destinationName = results.getString("Destination");
                    double destinationLatitude = Double.parseDouble(results.getString("DestinationLatitude"));
                    double destinationLongitude = Double.parseDouble(results.getString("DestinationLongitude"));
                    int aircraftID = Integer.parseInt(results.getString("AircraftID"));
                    String modelName = results.getString("ModelName");

                    ArrayList<Integer> departureInfo = parseTime(departureTime);
                    ArrayList<Integer> arrivalInfo = parseTime(arrivalTime);
                    Aircraft aircraft = new Aircraft(aircraftID, modelName);
                    Location origin = new Location(originName, originLatitude, originLongitude);
                    Location destination = new Location(destinationName, destinationLatitude, destinationLongitude);

                    flightDetails = new Flight(flightNumber, departureInfo, arrivalInfo, origin, destination, aircraft);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightDetails;
    }

    public void addFlight(Flight newFlight) {
        try {
            String query = "INSERT INTO FLIGHTS (Origin, Destination, DepartureTime, ArrivalTime, AircraftID) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
                statement.setString(1, newFlight.getOrigin().getCityName());
                statement.setString(2, newFlight.getDestination().getCityName());
                statement.setTimestamp(3, Timestamp.valueOf(newFlight.getDepartureTime().toString()));
                statement.setTimestamp(4, Timestamp.valueOf(newFlight.getArrivalTime().toString()));
                statement.setString(5, newFlight.getAircraft().getID());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFlight(int flightNumber) {
        try {
            String query = "DELETE FROM FLIGHTS WHERE FlightNumber = ?";

            try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
                statement.setInt(1, flightNumber);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyFlight(Flight modifiedFlight) {
        try {
            String query = "UPDATE FLIGHTS SET Origin = ?, Destination = ?, DepartureTime = ?, ArrivalTime = ?, AircraftID = ? WHERE FlightNumber = ?";

            try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
                statement.setString(1, modifiedFlight.getOrigin().getCityName());
                statement.setString(2, modifiedFlight.getDestination().getCityName());
                statement.setTimestamp(3, Timestamp.valueOf(modifiedFlight.getDepartureTime().toString()));
                statement.setTimestamp(4, Timestamp.valueOf(modifiedFlight.getArrivalTime().toString()));
                statement.setString(5, modifiedFlight.getAircraft().getID());
                statement.setInt(6, modifiedFlight.getFlightnumber());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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