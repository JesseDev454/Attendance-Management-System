package com.nileuniversity.tams.model;

/**
 * Administrator class extending User
 * Represents system administrators with full privileges
 */
public class Administrator extends User {

    /**
     * Constructor
     */
    public Administrator(String userId, String username, String password, String email) {
        super(userId, username, password, email, UserRole.ADMINISTRATOR);
    }

    /**
     * Add a new user to the system
     */
    public void addUser(User newUser) {
        // Logic to add user
        System.out.println("Administrator " + username + " added new user: " + newUser.getUsername());
    }

    /**
     * Configure system policies
     */
    public void configurePolicies() {
        // Logic to set policies
        System.out.println("Administrator " + username + " configuring policies.");
    }

    /**
     * Monitor system-wide trends
     */
    public void monitorTrends() {
        // Logic to analyze trends
        System.out.println("Administrator " + username + " monitoring trends.");
    }
}
