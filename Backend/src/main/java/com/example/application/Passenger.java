package com.example.application;

public class Passenger {

    private int flightNumber;
    private int userId;
    private int ticketID;
    private String firstName;
    private String lastName;
    private String email;
    private int seatNo;


    public Passenger(int flightNumber, int userId, int ticketID, String firstName, String lastName, String email, int seatNo) {
        this.flightNumber = flightNumber;
        this.userId = userId;
        this.ticketID = ticketID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.seatNo = seatNo;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
}
