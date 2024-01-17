package com.example.demo;

import java.sql.*;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.DatabaseConnector;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {
    @PostMapping("/addAircraft")
    public ResponseEntity<String> addAircraft(@RequestBody Map<String, String> payload) {
        try {
            String modelName = payload.get("name");
			DatabaseConnector databaseConnector = DatabaseConnector.getOnlyInstance();
            try (Connection dbConnection = databaseConnector.startConnection()) {
                String query = "INSERT INTO Aircrafts (ModelName) VALUES (?)";
                try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
                    preparedStatement.setString(1, modelName);
                    preparedStatement.executeUpdate();
                }
            }

            return ResponseEntity.ok("Aircraft added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding aircraft");
        }
    }

	@DeleteMapping("/removeAircraft/{aircraftId}")
	public ResponseEntity<String> removeAircraft(@PathVariable String aircraftId) {
		try {
			DatabaseConnector databaseConnector = DatabaseConnector.getOnlyInstance();
			try (Connection dbConnection = databaseConnector.startConnection()) {
				String query = "DELETE FROM Aircrafts WHERE AircraftID=?";
				try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
					preparedStatement.setString(1, aircraftId);
					preparedStatement.executeUpdate();
				}
			}
	
			return ResponseEntity.ok("Aircraft removed successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing aircraft");
		}
	}	
}
