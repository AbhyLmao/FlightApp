package com.example.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModifyAircraftsQuery {
    private DatabaseConnector databaseConnector;
    private Connection dbConnection;

	public ModifyAircraftsQuery() {
		databaseConnector = DatabaseConnector.getOnlyInstance();
		dbConnection = databaseConnector.startConnection();
	}

	public List<Aircraft> getAllAircraft() {
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		try {
			String query = "SELECT * FROM Aircrafts";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
	
			ResultSet results = preparedStatement.executeQuery();
	
			while (results.next()) {
				Integer aircraftID = Integer.parseInt(results.getString("AircraftID"));
				String modelName = results.getString("ModelName");
				Aircraft aircraft = new Aircraft(aircraftID, modelName);
				aircrafts.add(aircraft);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aircrafts;
	}

	public Aircraft getAircraftByID(int aircraftID) {
		try {
				String query = "SELECT * FROM Aircrafts WHERE AircraftID=?";
				PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
				preparedStatement.setInt(1, aircraftID);

				ResultSet results = preparedStatement.executeQuery();

				if (results.next()) {
						String modelName = results.getString("ModelName");
						return new Aircraft(aircraftID, modelName);
				}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}

	public void addAircraft(String modelName) {
		try {
				String query = "INSERT INTO Aircrafts (ModelName) VALUES (?)";
				PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
				preparedStatement.setString(1, modelName);
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	public void removeAircraft(String modelName) {
		try {
				String query = "DELETE FROM Aircrafts WHERE ModelName=?";
				PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
				preparedStatement.setString(1, modelName);
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
}