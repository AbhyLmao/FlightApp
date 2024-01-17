package com.example.application;

import java.util.List;

public class Aircraft {
    private String aircraftID;
    private String modelName;
    private List<Integer> aircraftList;

    public Aircraft(int id, String name) {
        this.aircraftID = String.format("%03d", id);
        this.modelName = name;
    }

    public Aircraft(int id) {
        this.aircraftID = String.format("%03d", id);
        initializeAircraftFromDatabase();
    }

    public Aircraft(String modelName, List<Integer> aircraftIDs) {
        this.modelName = modelName;
        this.aircraftList = aircraftIDs;
    }

    private void initializeAircraftFromDatabase() {
        ModifyAircraftsQuery aircraftsQuery = new ModifyAircraftsQuery();
        Aircraft foundAircraft = aircraftsQuery.getAircraftByID(this.getNumericID());
    
        if (foundAircraft != null) {
            modelName = foundAircraft.getName();
        }
    }

    public String getID() {
        return this.aircraftID;
    }

    public int getNumericID() {
        return Integer.parseInt(this.aircraftID);
    }

    public String getName() {
        return this.modelName;
    }

    public String getFormattedID() {
        return String.format("%03d", getNumericID());
    }

    public List<Integer> getAircraftIDs() {
        return aircraftList;
    }

    public void setID(int id) {
        this.aircraftID = String.format("%03d", id);
    }

    public void setName(String name) {
        this.modelName = name;
    }

    public void setAircraftIDs(List<Integer> aircraftIDs) {
        this.aircraftList = aircraftIDs;
    }

    public String toString() {
        return "Aircraft ID: " + this.aircraftID + "\t\tModel Name: " + this.modelName;
    }
}
