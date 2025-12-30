package com.nileuniversity.tams.model;

import java.util.Date;

/**
 * Attendance class
 * Represents a single attendance event (check-in or check-out)
 */
public class Attendance {
    private String attendanceId;
    private Date timestamp;
    private String type; // "CHECK_IN" or "CHECK_OUT"
    private String locationTag;

    /**
     * Constructor
     */
    public Attendance(String attendanceId, String type, String locationTag) {
        this.attendanceId = attendanceId;
        this.timestamp = new Date();
        this.type = type;
        this.locationTag = locationTag;
    }

    /**
     * Capture attendance event
     */
    public void captureEvent() {
        // Logic to capture event
        this.timestamp = new Date();
        System.out.println("Attendance event captured: " + type + " at " + timestamp);
    }

    /**
     * Validate location
     */
    public boolean validateLocation(String expectedLocation) {
        // Check if location matches
        return this.locationTag != null && this.locationTag.equals(expectedLocation);
    }

    // Getters and Setters
    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationTag() {
        return locationTag;
    }

    public void setLocationTag(String locationTag) {
        this.locationTag = locationTag;
    }
}
