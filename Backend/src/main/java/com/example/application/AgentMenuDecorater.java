package com.example.application;

public class AgentMenuDecorater implements Menu{
    private final Menu menu;
    private final ReadPassengersQuery readPassengersQuery;

    public AgentMenuDecorater(Menu menu) {
        this.menu = menu;
        this.readPassengersQuery = new ReadPassengersQuery();
    }

    @Override
    public String showMenu() {
        return menu.showMenu() + "\nShow Passenger List";
    }
}
