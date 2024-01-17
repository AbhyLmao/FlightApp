package com.example.application;

import java.util.ArrayList;

public class Flight {
    private int flightNumber;
    private DateTime departureTime;
    private DateTime arrivalTime;
    private Location origin;
    private Location destination;
    private Aircraft aircraft;
    private ArrayList<Seat> seats;

    public Flight() {
    }

    /* 
     * Constructor without seats argument
     */
    public Flight(int flightNumber, ArrayList<Integer> departureTime, ArrayList<Integer> arrivalTime, Location origin, Location destination, Aircraft aircraft)  {
        this.flightNumber = flightNumber;
        this.departureTime = new DateTime(departureTime.get(0), departureTime.get(1), departureTime.get(2), departureTime.get(3), departureTime.get(4), departureTime.get(5));
        this.arrivalTime = new DateTime(arrivalTime.get(0), arrivalTime.get(1), arrivalTime.get(2), arrivalTime.get(3), arrivalTime.get(4), arrivalTime.get(5));
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.seats = new ArrayList<Seat>();
    }

    /* 
     * Constructor with seats argument
     */
    public Flight(int flightNumber, ArrayList<Integer> departureTime, ArrayList<Integer> arrivalTime, Location origin, Location destination, Aircraft aircraft, ArrayList<Seat> seats)  {
        this.flightNumber = flightNumber;
        this.departureTime = new DateTime(departureTime.get(0), departureTime.get(1), departureTime.get(2), departureTime.get(3), departureTime.get(4), departureTime.get(5));
        this.arrivalTime = new DateTime(arrivalTime.get(0), arrivalTime.get(1), arrivalTime.get(2), arrivalTime.get(3), arrivalTime.get(4), arrivalTime.get(5));
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.seats = seats;
    }

    public int getFlightnumber() { return this.flightNumber; }
    public DateTime getDepartureTime() { return this.departureTime; }   
    public DateTime getArrivalTime() { return this.arrivalTime; }
    public Location getOrigin() { return this.origin; }   
    public Location getDestination() { return this.destination; }
    public Aircraft getAircraft() { return this.aircraft; }
    public ArrayList<Seat> getSeats() { return this.seats; }

    public void setFlightnumber(int flightNumber) { this.flightNumber = flightNumber; }
    public void setDepartureTime(ArrayList<Integer> departureTime) {
        this.departureTime = new DateTime(departureTime.get(0), departureTime.get(1), departureTime.get(2), departureTime.get(3), departureTime.get(4), departureTime.get(5));
    }
    public void setArrivalTime(ArrayList<Integer> arrivalTime) {
        this.arrivalTime = new DateTime(arrivalTime.get(0), arrivalTime.get(1), arrivalTime.get(2), arrivalTime.get(3), arrivalTime.get(4), arrivalTime.get(5));
    }
    public void setOrigin(Location origin) { this.origin = origin; }
    public void setDestination(Location destination) { this.destination = destination; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }
    public void setSeats(ArrayList<Seat> seats) { this.seats = seats; }

    public void bookSeat(int seatNumber) {
        seats.get(seatNumber - 1).setAvailability(false); // seat is now occupied
    }

    public void cancelSeat(int seatNumber) {
        seats.get(seatNumber - 1).setAvailability(true); // seat is now occupied
    }

    public String toString() {
        return flightNumber + "\t" + aircraft + "\nDeparture Info: " + origin + "\t" + departureTime + "\nArrival Info: " + destination + "\t" + arrivalTime;
    }


}