package com.example.application;

public class GuestMenuDecorator implements Menu {
    private final Menu menu;

    public GuestMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String showMenu() {
        return menu.showMenu() + "\nPurchase Ticket\nRegister for Membership\nFind Ticket";
    }
}