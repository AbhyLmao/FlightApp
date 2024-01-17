package com.example.demo;

import java.sql.*;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.application.DatabaseConnector;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @PostMapping("/addDestination")
    public ResponseEntity<String> addDestination(@RequestBody Map<String, String> payload) {
        try {
            String cityName = payload.get("cityName");
            double latitude = Double.parseDouble(payload.get("latitude"));
            double longitude = Double.parseDouble(payload.get("longitude"));

            DatabaseConnector databaseConnector = DatabaseConnector.getOnlyInstance();
            try (Connection dbConnection = databaseConnector.startConnection()) {
                String query = "INSERT INTO Locations (CityName, Latitude, Longitude) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
                    preparedStatement.setString(1, cityName);
                    preparedStatement.setDouble(2, latitude);
                    preparedStatement.setDouble(3, longitude);
                    preparedStatement.executeUpdate();
                }
            }

            return ResponseEntity.ok("Destination added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding destination");
        }
    }

    @DeleteMapping("/removeDestination/{cityName}")
    public ResponseEntity<String> removeDestination(@PathVariable String cityName) {
        try {
            DatabaseConnector databaseConnector = DatabaseConnector.getOnlyInstance();
            try (Connection dbConnection = databaseConnector.startConnection()) {
                String query = "DELETE FROM Locations WHERE CityName=?";
                try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
                    preparedStatement.setString(1, cityName);
                    preparedStatement.executeUpdate();
                }
            }

            return ResponseEntity.ok("Destination removed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing destination");
        }
    }
}
