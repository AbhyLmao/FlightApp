package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.application.ReadUsersQuery;
import com.example.application.User;

@RestController
@RequestMapping("/api")
public class UserController {

    private final ReadUsersQuery readUsersQuery;
    private static String loggedInUsername;

    public UserController(ReadUsersQuery readUsersQuery) {
        this.readUsersQuery = readUsersQuery;
    }

    @GetMapping("/user/details")
    public ResponseEntity<User> getUserDetails(@RequestParam(required = false) String username) {
        if (username == null) {
            return ResponseEntity.status(401).build();
        }
    
        loggedInUsername = username;
    
        User user;
        // Check if the username contains an "@" symbol (indicating it's an email)
        if (loggedInUsername.contains("@")) {
            user = readUsersQuery.getRegisteredUser(loggedInUsername);
        } else {
            User loggedInUser = readUsersQuery.getUserByUsername(loggedInUsername);
            if (loggedInUser != null && "registered".equals(loggedInUser.getUserType())) {
                user = readUsersQuery.getRegisteredUser(loggedInUsername);
            } else {
                user = loggedInUser;
            }
        }
    
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestParam String username) {
        loggedInUsername = username;
        return ResponseEntity.ok("Logged in successfully");
    }

    @PostMapping("/user/logout")
    public ResponseEntity<String> logout() {
        loggedInUsername = null;
        return ResponseEntity.ok("Logged out successfully");
    }
}
