package com.nileuniversity.tams.model;

/**
 * Abstract User class representing all users in the system
 * This is the base class for Student, Staff, and Administrator
 */
public abstract class User {
    protected String userId;
    protected String username;
    protected String password;
    protected String email;
    protected UserRole role;

    /**
     * Constructor
     */
    public User(String userId, String username, String password, String email, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Login method - to be implemented by authentication service
     */
    public boolean login() {
        // Logic handled by AuthenticationService
        return false;
    }

    /**
     * Logout method
     */
    public void logout() {
        // Clear session or user data
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
