package com.example.application;

public class User {
    private int userId;
    private String username;
    private String password;
    private String userType;
    private String email;
    private String phoneNumber;
    private int points;
    private String firstName;
    private String lastName;

    public User(int userId, String username, String password, String userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User(int userId, String email, String password, String firstName, String lastName,
                String phoneNumber, int points) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userType = "registered";
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.points = points;
    }

    public int getId() { return this.userId; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getUserType() { return this.userType; }
    public String getEmail() { return this.email; }
    public String getPhoneNumber() { return this.phoneNumber; }
    public int getPoints() { return this.points; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }

    public void setId(int userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setPoints(int points) { this.points = points; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
