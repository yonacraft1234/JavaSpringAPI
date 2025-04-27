package org.example.idfpostgresql.service;

import org.example.idfpostgresql.exception.ResourceNotFoundException;
import org.example.idfpostgresql.model.User;
import org.example.idfpostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Get user by Serial
    public Optional<User> getUserBySerial(String serial) {
        return userRepository.findBySerial(serial);
    }

    // Create a new user
    public User createUser(User user) {
        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Update user
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setSerial(userDetails.getSerial());

        // Only update password if it's provided
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        user.setClassification(userDetails.getClassification());

        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    // Find users by classification
    public List<User> getUsersByClassification(Long classification) {
        return userRepository.findByClassification(classification);
    }

    // Search users by last name
    public List<User> searchUsersByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    // Authenticate user
    public boolean authenticateUser(String serial, String password) {
        Optional<User> userOpt = userRepository.findBySerial(serial);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}