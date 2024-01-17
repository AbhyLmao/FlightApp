package com.example.demo;

import com.example.application.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final Menu basicMenu = new BasicMenu();

    @GetMapping("/show")
    public ResponseEntity<List<String>> showMenu(@RequestParam String userType) {
        Menu decoratedMenu = getDecoratedMenu(userType);
        List<String> menuOptions = Arrays.asList(decoratedMenu.showMenu().split("\n"));
        return ResponseEntity.ok(menuOptions);
    }

    @GetMapping("/printRegisteredUsers")
    public ResponseEntity<List<User>> printRegisteredUsers() {
        AdminMenuDecorator adminMenu = new AdminMenuDecorator(basicMenu);
        List<User> registeredUsers = adminMenu.getRegisteredUsers();
        return ResponseEntity.ok(registeredUsers);
    }

    @GetMapping("/browseAircraftOwned")
    public ResponseEntity<List<Aircraft>> browseAircraftOwned() {
        AdminMenuDecorator adminMenu = new AdminMenuDecorator(basicMenu);
        List<Aircraft> aircraftOwned = adminMenu.getAircraftOwned();
        return ResponseEntity.ok(aircraftOwned);
    }

    @GetMapping("/browseFlightDestinations")
    public ResponseEntity<List<Location>> browseFlightDestinations() {
        AdminMenuDecorator adminMenu = new AdminMenuDecorator(basicMenu);
        List<Location> destinations = adminMenu.getFlightDestinations();
        return ResponseEntity.ok(destinations);
    }

    private Menu getDecoratedMenu(String userType) {
        switch (userType.toLowerCase()) {
            case "admin":
                return new AdminMenuDecorator(basicMenu);
            case "guest":
                return new GuestMenuDecorator(basicMenu);
            case "registered":
                return new RegisteredUserMenuDecorator(basicMenu);
            case "attendant":
                return new AttendantMenuDecorater();
            case "agent":
                return new AgentMenuDecorater(basicMenu);
            default:
                return basicMenu;
        }
    }
}
