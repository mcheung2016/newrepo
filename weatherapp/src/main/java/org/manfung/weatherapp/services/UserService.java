package org.manfung.weatherapp.services;

import org.manfung.weatherapp.models.User;
import org.manfung.weatherapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Find user by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by ID
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get all users (Admin)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Delete user by username
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
    }

    // Delete user by ID
    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    // Update user details
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword());
                    user.setRoles(userDetails.getRoles());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }
}


