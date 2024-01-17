package com.example.application;

import java.lang.Math;

public class Location {
    private String cityName;
    private double latitude; // positive value denotes North and negative value denotes South
    private double longitude; // positive value denotes East and negative value denotes West

    public Location(String name, double latitude, double longitude) {
        this.cityName = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String cityName) {
        this.cityName = cityName;
        initializeCoordinatesFromDatabase();
    }

    private void initializeCoordinatesFromDatabase() {
        ModifyDestinationsQuery destinationsQuery = new ModifyDestinationsQuery();
        Location foundLocation = destinationsQuery.getLocationByName(cityName);
    
        if (foundLocation != null) {
            latitude = foundLocation.getLatitude();
            longitude = foundLocation.getLongitude();
        }
    }

    public String getCityName() { return this.cityName; }
    public double getLatitude() { return this.latitude; }
    public double getLongitude() { return this.longitude; }

    public void setCityName(String name) { this.cityName = name; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String toString() {
        char latitudeCardinalDirection;
        char longitudeCardinalDirection;
        
        if (latitude < 0) { latitudeCardinalDirection = 'S'; }
        else { latitudeCardinalDirection = 'N'; }

        if (longitude < 0) { longitudeCardinalDirection = 'W'; }
        else { longitudeCardinalDirection = 'E'; }
        return cityName + "  \t\tLatitude: " + Math.abs(latitude) + "° " + latitudeCardinalDirection + ", Longitude: " + Math.abs(longitude) + "° " + longitudeCardinalDirection;
    }
}

