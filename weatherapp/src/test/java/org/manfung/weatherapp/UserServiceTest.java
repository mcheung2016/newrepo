package org.manfung.weatherapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.manfung.weatherapp.models.User;
import org.manfung.weatherapp.repositories.UserRepository;
import org.manfung.weatherapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Start the entire application context for testing
@ActiveProfiles("test")  // Using a test profile with H2 database for testing
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Clear the repository and set up initial data before each test
        userRepository.deleteAll();

        // Create and save initial users
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setPassword("password123");
        user1.setRoles(Set.of("USER"));  // Storing roles as Set<String>

        User user2 = new User();
        user2.setUsername("jane_doe");
        user2.setPassword("password456");
        user2.setRoles(Set.of("ADMIN"));  // Storing roles as Set<String>

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    public void testFindUserByUsername() {
        // Test finding a user by username
        User foundUser = userService.findUserByUsername("john_doe");
        assertNotNull(foundUser);
        assertEquals("john_doe", foundUser.getUsername());
        assertTrue(foundUser.getRoles().contains("USER"));  // Check the role using Set
    }

    @Test
    public void testCreateUser() {
        // Create a new user and set the details
        User newUser = new User();
        newUser.setUsername("mike_doe");
        newUser.setPassword("password789");

        // Set roles using a Set<String>
        newUser.setRoles(Set.of("USER"));  // Using Set.of() to set the role(s)

        // Call the createUser method
        User savedUser = userService.createUser(newUser);  // Assuming you have a createUser method
        assertNotNull(savedUser);
        assertEquals("mike_doe", savedUser.getUsername());

        // Assert the role (comparing with the first and only role in the set)
        assertTrue(savedUser.getRoles().contains("USER"));
    }

    @Test
    public void testDeleteUser() {
        // Delete a user by username
        userService.deleteUserByUsername("john_doe");  // Assuming you have a deleteUserByUsername method
        User deletedUser = userService.findUserByUsername("john_doe");
        assertNull(deletedUser);  // The user should no longer exist
    }

    @Test
    public void testUpdateUser() {
        // Update an existing user's role
        User existingUser = userService.findUserByUsername("jane_doe");
        assertNotNull(existingUser);

        // Update roles to a new value
        existingUser.setRoles(Set.of("SUPER_ADMIN"));
        User updatedUser = userService.updateUser(existingUser.getId(), existingUser);  // Passing user ID

        assertNotNull(updatedUser);
        assertTrue(updatedUser.getRoles().contains("SUPER_ADMIN"));  // Verify new role
    }
}

