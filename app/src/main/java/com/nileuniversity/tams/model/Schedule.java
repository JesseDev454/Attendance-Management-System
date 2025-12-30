package com.nileuniversity.tams.model;

/**
 * Schedule class
 * Represents a class schedule entry
 */
public class Schedule {
    private String classId;
    private String startTime;
    private String endTime;
    private String room;
    private String courseName;

    /**
     * Constructor
     */
    public Schedule(String classId, String courseName, String startTime, String endTime, String room) {
        this.classId = classId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    /**
     * Add a schedule entry
     */
    public void addEntry() {
        // Logic to add schedule

    }

    /**
     * Check for scheduling conflicts
     */
    public boolean checkConflict(Schedule otherSchedule) {
        // Logic to detect conflicts
        return this.room.equals(otherSchedule.getRoom()) && 
               this.startTime.equals(otherSchedule.getStartTime());
    }

    // Getters and Setters
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
