package com.example.application;

public class RegisteredUserMenuDecorator implements Menu {
    private final Menu menu;

    public RegisteredUserMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String showMenu() {
        return menu.showMenu() + "\nPurchase Ticket\nFind Ticket";
    }
}