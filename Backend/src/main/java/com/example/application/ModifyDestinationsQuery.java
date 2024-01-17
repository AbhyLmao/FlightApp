package com.example.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModifyDestinationsQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

    public ModifyDestinationsQuery() {
        databaseConnector = DatabaseConnector.getOnlyInstance();
        dbConnection = databaseConnector.startConnection();
    }

    public List<Location> getAllDestinations() {
        List<Location> destinations = new ArrayList<>();
        try {
            String query = "SELECT * FROM Locations";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                String cityName = results.getString("CityName");
                double latitude = results.getDouble("Latitude");
                double longitude = results.getDouble("Longitude");

                Location location = new Location(cityName, latitude, longitude);
                destinations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    public Location getLocationByName(String cityName) {
        try {
            String query = "SELECT * FROM Locations WHERE CityName=?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, cityName);

            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                double latitude = results.getDouble("Latitude");
                double longitude = results.getDouble("Longitude");

                return new Location(cityName, latitude, longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDestination(Location location) {
        try {
            String query = "INSERT INTO Locations (CityName, Latitude, Longitude) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, location.getCityName());
            preparedStatement.setDouble(2, location.getLatitude());
            preparedStatement.setDouble(3, location.getLongitude());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDestination(String cityName) {
        try {
            String query = "DELETE FROM Locations WHERE CityName=?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, cityName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
