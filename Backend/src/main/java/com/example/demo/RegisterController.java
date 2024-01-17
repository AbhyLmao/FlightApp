package com.example.demo;

import com.example.application.ReadUsersQuery;
import com.example.application.UpdateSeatQuery;
import com.example.application.UpdateUserQuery;
import com.example.application.User;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private final ReadUsersQuery readUsersQuery;

    public RegisterController(ReadUsersQuery readUsersQuery) {
        this.readUsersQuery = readUsersQuery;
    }

    @PostMapping("/register")
    public void registerUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            @RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("phoneNumber") String phoneNumber) {
        try {
            List<User> allUsers = readUsersQuery.getAllRegisteredUsers();
            boolean emailAlreadyExists = false;

            for (User user: allUsers) {
                if (email.equals(user.getEmail())) {
                    emailAlreadyExists = true;
                    break;
                }
            }

            if (emailAlreadyExists == false) {
                UpdateUserQuery updateUserQuery = new UpdateUserQuery();
                updateUserQuery.addRegisteredUser(email, password, firstName, lastName, phoneNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

}