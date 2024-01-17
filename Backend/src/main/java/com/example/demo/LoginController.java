package com.example.demo;

import com.example.application.ReadUsersQuery;
import com.example.application.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.application.Login;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final ReadUsersQuery readUsersQuery;

    public LoginController(ReadUsersQuery readUsersQuery) {
        this.readUsersQuery = readUsersQuery;
    }

    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Login loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user;
        if (username.contains("@")) {
            user = readUsersQuery.getRegisteredUser(username);
        } else {
            user = readUsersQuery.getUserByUsername(username);
        }

        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
