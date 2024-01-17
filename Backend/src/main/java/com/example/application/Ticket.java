package com.example.application;

public class Ticket {
    private int ticketID;
    private int ticketPrice;
    private String firstName;
    private String lastName;
    private Flight flight;
    private int seatNumber;
    private boolean cancellationInsurance;
    private String emailAddress;
    private int userID;

    public Ticket(int ticketID, int ticketPrice, String firstName, String lastName, Flight flight, int seatNumber, boolean cancellationInsurance, String emailAddress) {
        this.ticketID = ticketID;
        this.ticketPrice = ticketPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.cancellationInsurance = cancellationInsurance;
        this.emailAddress = emailAddress;
        this.userID = -1; // for tickets that belong to unregistered users
    }

    public Ticket(int ticketID, int ticketPrice, String firstName, String lastName, Flight flight, int seatNumber, boolean cancellationInsurance, String emailAddress, int userID) {
        this.ticketID = ticketID;
        this.ticketPrice = ticketPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.cancellationInsurance = cancellationInsurance;
        this.emailAddress = emailAddress;
        this.userID = userID;
    }
    
    public int getTicketID() { return this.ticketID; }
    public int getTicketPrice() { return this.ticketPrice; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public Flight getFlight() { return this.flight; }
    public int getSeatNumber() { return this.seatNumber; }
    public boolean getCancellationInsurance() { return this.cancellationInsurance; }
    public String getEmailAddress() { return this.emailAddress; }
    public int getUserID() { return this.userID; }

    public void setTicketID(int ticketID) { this.ticketID = ticketID; }
    public void setTicketPrice(int ticketPrice) { this.ticketPrice = ticketPrice; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public void setCancellationInsurance(boolean cancellationInsurance) { this.cancellationInsurance = cancellationInsurance; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setUserID(int userID) { this.userID = userID; }

    public String toString() {
        return "ticket ID: " + this.ticketID + "\tname: " + this.firstName + " " + this.lastName + "\temail address: " + this.emailAddress + "\tuser ID: " + this.userID + "\n" 
        + this.flight + "\nseat number" + this.seatNumber + "\tincludes cancellation insurance: " + this.cancellationInsurance + "\tPrice: " + this.ticketPrice;
    }
}