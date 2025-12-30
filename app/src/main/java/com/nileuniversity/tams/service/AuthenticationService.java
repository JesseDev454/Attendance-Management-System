package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AuthenticationService
 * Handles user authentication, login, logout, and user management
 * Uses in-memory storage for users
 */
public class AuthenticationService {
    private static AuthenticationService instance;
    private List<User> users;
    private User currentUser;
    private Authentication authentication;

    /**
     * Private constructor for Singleton pattern
     */
    private AuthenticationService() {
        users = new ArrayList<>();
        authentication = new Authentication();
        initializeSampleUsers();
    }

    /**
     * Get singleton instance
     */
    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    /**
     * Initialize sample users for testing
     * 1 Student, 1 Staff, 1 Administrator
     */
    private void initializeSampleUsers() {
        // Sample Student
        users.add(new Student("S001", "student1", "pass123", "student1@nileuniversity.edu.ng"));
        
        // Sample Staff
        users.add(new Staff("T001", "staff1", "pass123", "staff1@nileuniversity.edu.ng"));
        
        // Sample Administrator
        users.add(new Administrator("A001", "admin1", "pass123", "admin1@nileuniversity.edu.ng"));
    }

    /**
     * Login user with username and password
     */
    public User login(String username, String password) {
        // Validate credentials format
        if (!authentication.validateCredentials(username, password)) {
            return null;
        }

        // Find user by username and password
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                authentication.generateToken(user);
                return user;
            }
        }

        return null; // Login failed
    }

    /**
     * Logout current user
     */
    public void logout() {
        if (currentUser != null) {
            currentUser.logout();
            currentUser = null;
        }
    }

    /**
     * Get currently logged in user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Add a new user (Admin only)
     */
    public boolean addUser(User newUser) {
        if (currentUser != null && currentUser.getRole() == UserRole.ADMINISTRATOR) {
            users.add(newUser);
            return true;
        }
        return false;
    }

    /**
     * Get all users (Admin only)
     */
    public List<User> getAllUsers() {
        if (currentUser != null && currentUser.getRole() == UserRole.ADMINISTRATOR) {
            return new ArrayList<>(users);
        }
        return new ArrayList<>();
    }

    /**
     * Reset password
     */
    public boolean resetPassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return authentication.resetPassword(user, newPassword);
            }
        }
        return false;
    }

    /**
     * Check if user has required role
     */
    public boolean hasRole(UserRole role) {
        return currentUser != null && authentication.enforceRoleAccess(currentUser, role);
    }
}
