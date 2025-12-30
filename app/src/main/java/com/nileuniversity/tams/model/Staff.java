package com.nileuniversity.tams.model;

/**
 * Staff class extending User
 * Represents staff members who can monitor attendance
 */
public class Staff extends User {

    /**
     * Constructor
     */
    public Staff(String userId, String username, String password, String email) {
        super(userId, username, password, email, UserRole.STAFF);
    }

    /**
     * Monitor real-time attendance
     */
    public void monitorRealTime() {
        // Logic to view real-time attendance data
        System.out.println("Staff " + username + " monitoring real-time attendance.");
    }

    /**
     * View class attendance records
     */
    public void viewClassRecords() {
        // Logic to retrieve class records
        System.out.println("Staff " + username + " viewing class records.");
    }

    /**
     * Manage class schedule
     */
    public void manageSchedule() {
        // Logic to add/update schedule
        System.out.println("Staff " + username + " managing schedule.");
    }
}
