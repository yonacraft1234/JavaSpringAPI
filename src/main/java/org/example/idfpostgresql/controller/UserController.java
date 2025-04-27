package org.example.idfpostgresql.controller;

import org.example.idfpostgresql.model.User;
import org.example.idfpostgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Get user by Serial
    @GetMapping("/serial/{serial}")
    public ResponseEntity<User> getUserBySerial(@PathVariable String serial) {
        Optional<User> user = userService.getUserBySerial(serial);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        // Don't return the password in the response
        newUser.setPassword(null);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        // Don't return the password in the response
        updatedUser.setPassword(null);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get users by classification
    @GetMapping("/classification/{classification}")
    public ResponseEntity<List<User>> getUsersByClassification(@PathVariable Long classification) {
        List<User> users = userService.getUsersByClassification(classification);
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Search users by last name
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByLastName(@RequestParam String lastName) {
        List<User> users = userService.searchUsersByLastName(lastName);
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Authenticate user
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody Map<String, String> credentials) {
        String serial = credentials.get("serial");
        String password = credentials.get("password");

        boolean isAuthenticated = userService.authenticateUser(serial, password);

        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", isAuthenticated);

        if (isAuthenticated) {
            Optional<User> userOpt = userService.getUserBySerial(serial);
            userOpt.ifPresent(user -> {
                Map<String, Object> userData = new HashMap<>();
                userData.put("id", user.getId());
                userData.put("firstName", user.getFirstName());
                userData.put("lastName", user.getLastName());
                userData.put("serial", user.getSerial());
                userData.put("classification", user.getClassification());
                response.put("user", userData);
            });
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}