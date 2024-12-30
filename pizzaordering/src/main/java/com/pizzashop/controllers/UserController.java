package com.pizzashop.controllers;

import com.pizzashop.models.User;
import com.pizzashop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Map<String, User>> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        Map<String, User> response = new HashMap<>();
        response.put("registerUser", registeredUser);
        return ResponseEntity.ok(response);
    }

    // Login the user
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User loginRequest) {
        User user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful!");
            response.put("userId", user.getId());  // Ensure userId is sent here
            response.put("user", user);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid username or password.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
    // Get user details by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> getUserDetails(@PathVariable Long id) {
        User user = userService.getUserById(id); // Assuming you have a method to fetch user by ID
        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "User not found.");
            return ResponseEntity.status(404).body(errorResponse);
        }
    }




}
