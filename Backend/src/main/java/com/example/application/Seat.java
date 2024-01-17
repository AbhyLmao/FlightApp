package com.example.application;

public class Seat {
    private int seatID; // unique across every seat object. Primary key in database
	private int seatNumber; // unique only within each flight
    private int flightNumber; // the flight that the seat is associated with
    private boolean isAvailable; // true for vacant and false for occupied

    public Seat(int seatID, int seatNumber, int flightNumber, boolean isAvailable) {
        this.seatID = seatID;
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.isAvailable = isAvailable;
    }

    public int getSeatID() { return this.seatID; }
    public int getSeatNumber() { return this.seatNumber; }
    public int getFlightNumber() { return this.flightNumber; }
    public boolean getAvailability() { return this.isAvailable; }

    public void setSeatID(int seatID) { this.seatID = seatID; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public void setFlightNumber(int flightNumber) { this.flightNumber = flightNumber; }
    public void setAvailability(boolean isAvailable) { this.isAvailable = isAvailable; }

    public String toString() {
        return "seat ID: " + this.seatID + "\tseat number: " + this.seatNumber + "\t\tflight number: " + this.flightNumber + "\tis available: " + this.isAvailable;
    };
}