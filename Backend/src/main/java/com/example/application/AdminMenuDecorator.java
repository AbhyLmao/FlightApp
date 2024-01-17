package com.example.application;

import java.util.List;

public class AdminMenuDecorator implements Menu {
    private final Menu menu;
    private final ReadUsersQuery readUsersQuery;
    private final ModifyAircraftsQuery aircraftQuery;
    private final ModifyDestinationsQuery destinationsQuery;

    public AdminMenuDecorator(Menu menu) {
        this.menu = menu;
        this.readUsersQuery = new ReadUsersQuery();
        this.aircraftQuery = new ModifyAircraftsQuery();
        this.destinationsQuery = new ModifyDestinationsQuery();
    }

    @Override
    public String showMenu() {
        return menu.showMenu() + 
        "\nPrint Registered Users\nBrowse Aircraft Owned\nModify Possible Destinations";
    }

    public List<User> getRegisteredUsers() {
        return readUsersQuery.getAllRegisteredUsers();
    }

    public List<Aircraft> getAircraftOwned() {
        return aircraftQuery.getAllAircraft();
    }

    public List<Location> getFlightDestinations() {
        return destinationsQuery.getAllDestinations();
    }
}
