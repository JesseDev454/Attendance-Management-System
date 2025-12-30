package com.nileuniversity.tams.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Student class extending User
 * Represents students in the TAMS system
 */
public class Student extends User {
    private List<AttendanceRecord> attendanceHistory;

    /**
     * Constructor
     */
    public Student(String userId, String username, String password, String email) {
        super(userId, username, password, email, UserRole.STUDENT);
        this.attendanceHistory = new ArrayList<>();
    }

    /**
     * Student checks in for a class/session
     */
    public void checkIn() {
        // Logic to record check-in
    }

    /**
     * Student checks out from a class/session
     */
    public void checkOut() {
        // Logic to record check-out
    }

    /**
     * View attendance history
     */
    public List<AttendanceRecord> viewAttendanceHistory() {
        return attendanceHistory;
    }

    public void setAttendanceHistory(List<AttendanceRecord> attendanceHistory) {
        this.attendanceHistory = attendanceHistory;
    }
}
