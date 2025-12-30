package com.nileuniversity.tams.model;

import java.util.Date;

/**
 * AttendanceRecord class
 * Represents a complete attendance record for a student for a specific date
 */
public class AttendanceRecord {
    private String recordId;
    private Date date;
    private Status status;
    private String remarks;
    private String studentId;

    /**
     * Constructor
     */
    public AttendanceRecord(String recordId, String studentId, Status status) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.date = new Date();
        this.status = status;
        this.remarks = "";
    }

    /**
     * Save the attendance record
     */
    public void saveRecord() {
        // Logic to persist record (in-memory for now)
    }

    /**
     * Retrieve attendance history
     */
    public AttendanceRecord retrieveHistory(String recordId) {
        // Logic to fetch record
        return this;
    }

    // Getters and Setters
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
