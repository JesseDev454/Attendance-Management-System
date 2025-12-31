package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * AttendanceService
 * Manages attendance check-in/out and attendance records
 * Uses in-memory storage
 */
public class AttendanceService {
    private static AttendanceService instance;
    private List<Attendance> attendanceList;
    private List<AttendanceRecord> attendanceRecords;
    private int attendanceCounter = 1;
    private int recordCounter = 1;

    /**
     * Private constructor for Singleton pattern
     */
    private AttendanceService() {
        attendanceList = new ArrayList<>();
        attendanceRecords = new ArrayList<>();
    }

    /**
     * Get singleton instance
     */
    public static AttendanceService getInstance() {
        if (instance == null) {
            instance = new AttendanceService();
        }
        return instance;
    }

    /**
     * Student check-in (default manual method)
     */
    public Attendance checkIn(Student student, String location) {
        return checkIn(student, location, AttendanceMethod.MANUAL);
    }

    /**
     * Student check-in with specified method
     */
    public Attendance checkIn(Student student, String location, AttendanceMethod method) {
        String attendanceId = "ATT" + String.format("%04d", attendanceCounter++);
        Attendance attendance = new Attendance(attendanceId, "CHECK_IN", location, method);
        attendance.captureEvent();
        attendanceList.add(attendance);

        // Create attendance record with method
        createAttendanceRecord(student.getUserId(), Status.PRESENT, method);

        return attendance;
    }

    /**
     * Student check-out (default manual method)
     */
    public Attendance checkOut(Student student, String location) {
        return checkOut(student, location, AttendanceMethod.MANUAL);
    }

    /**
     * Student check-out with specified method
     */
    public Attendance checkOut(Student student, String location, AttendanceMethod method) {
        String attendanceId = "ATT" + String.format("%04d", attendanceCounter++);
        Attendance attendance = new Attendance(attendanceId, "CHECK_OUT", location, method);
        attendance.captureEvent();
        attendanceList.add(attendance);

        return attendance;
    }

    /**
     * Create attendance record (default manual method)
     */
    private void createAttendanceRecord(String studentId, Status status) {
        createAttendanceRecord(studentId, status, AttendanceMethod.MANUAL);
    }

    /**
     * Create attendance record with method
     */
    private void createAttendanceRecord(String studentId, Status status, AttendanceMethod method) {
        String recordId = "REC" + String.format("%04d", recordCounter++);
        AttendanceRecord record = new AttendanceRecord(recordId, studentId, status, method);
        record.saveRecord();
        attendanceRecords.add(record);
    }

    /**
     * Mark student absent
     */
    public void markAbsent(String studentId, String remarks) {
        String recordId = "REC" + String.format("%04d", recordCounter++);
        AttendanceRecord record = new AttendanceRecord(recordId, studentId, Status.ABSENT);
        record.setRemarks(remarks);
        record.saveRecord();
        attendanceRecords.add(record);
    }

    /**
     * Mark student late
     */
    public void markLate(String studentId, String remarks) {
        String recordId = "REC" + String.format("%04d", recordCounter++);
        AttendanceRecord record = new AttendanceRecord(recordId, studentId, Status.LATE);
        record.setRemarks(remarks);
        record.saveRecord();
        attendanceRecords.add(record);
    }

    /**
     * Get attendance history for a student
     */
    public List<AttendanceRecord> getAttendanceHistory(String studentId) {
        List<AttendanceRecord> studentRecords = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getStudentId().equals(studentId)) {
                studentRecords.add(record);
            }
        }
        return studentRecords;
    }

    /**
     * Get all attendance records (Staff/Admin)
     */
    public List<AttendanceRecord> getAllAttendanceRecords() {
        return new ArrayList<>(attendanceRecords);
    }

    /**
     * Get attendance records by date
     */
    public List<AttendanceRecord> getRecordsByDate(Date date) {
        List<AttendanceRecord> dateRecords = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            // Simple date comparison (same day)
            if (isSameDay(record.getDate(), date)) {
                dateRecords.add(record);
            }
        }
        return dateRecords;
    }

    /**
     * Get attendance records by status
     */
    public List<AttendanceRecord> getRecordsByStatus(Status status) {
        List<AttendanceRecord> statusRecords = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getStatus() == status) {
                statusRecords.add(record);
            }
        }
        return statusRecords;
    }

    /**
     * Helper method to check if two dates are the same day
     */
    private boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) return false;
        return date1.getYear() == date2.getYear() &&
               date1.getMonth() == date2.getMonth() &&
               date1.getDate() == date2.getDate();
    }

    /**
     * Get total attendance count
     */
    public int getTotalAttendanceCount() {
        return attendanceList.size();
    }

    /**
     * Get total records count
     */
    public int getTotalRecordsCount() {
        return attendanceRecords.size();
    }

    /**
     * Get currently checked-in students
     * Returns list of attendance records for students who checked in today
     */
    public List<AttendanceRecord> getCurrentlyCheckedInStudents() {
        List<AttendanceRecord> checkedInStudents = new ArrayList<>();
        Date today = new Date();
        
        for (AttendanceRecord record : attendanceRecords) {
            if (isSameDay(record.getDate(), today) && 
                (record.getStatus() == Status.PRESENT || record.getStatus() == Status.LATE)) {
                checkedInStudents.add(record);
            }
        }
        return checkedInStudents;
    }

    /**
     * Get attendance records for a specific class
     * For now, returns all records since we don't have class-specific tracking yet
     */
    public List<AttendanceRecord> getRecordsByClass(String classId) {
        // Currently returns all records
        // In a full implementation, AttendanceRecord would have a classId field
        return new ArrayList<>(attendanceRecords);
    }

    /**
     * Get student name from student ID
     * This is a helper method - in real implementation, would query user service
     */
    public String getStudentNameById(String studentId) {
        // Simple mapping - in real app would query AuthenticationService
        return "Student " + studentId;
    }
}
