package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

/**
 * Unit tests for AttendanceService
 * Tests check-in, check-out, attendance record management
 */
public class AttendanceServiceTest {
    
    private AttendanceService attendanceService;
    private Student testStudent;
    
    /**
     * Set up test fixture before each test
     */
    @Before
    public void setUp() {
        attendanceService = AttendanceService.getInstance();
        testStudent = new Student("TEST001", "teststudent", "test123", "test@nileuniversity.edu.ng");
    }
    
    /**
     * Test Case: TC-ATT-001
     * Test student check-in functionality
     * Expected: Attendance record created with CHECK_IN type
     */
    @Test
    public void testStudentCheckIn() {
        // Arrange
        String location = "Main Campus";
        int initialCount = attendanceService.getTotalAttendanceCount();
        
        // Act
        Attendance attendance = attendanceService.checkIn(testStudent, location);
        
        // Assert
        assertNotNull("Attendance should not be null", attendance);
        assertNotNull("Attendance ID should be generated", attendance.getAttendanceId());
        assertEquals("Attendance type should be CHECK_IN", "CHECK_IN", attendance.getType());
        assertEquals("Location should match", location, attendance.getLocationTag());
        assertEquals("Attendance count should increase by 1", 
                    initialCount + 1, attendanceService.getTotalAttendanceCount());
    }
    
    /**
     * Test Case: TC-ATT-002
     * Test student check-out functionality
     * Expected: Attendance record created with CHECK_OUT type
     */
    @Test
    public void testStudentCheckOut() {
        // Arrange
        String location = "Main Campus";
        int initialCount = attendanceService.getTotalAttendanceCount();
        
        // Act
        Attendance attendance = attendanceService.checkOut(testStudent, location);
        
        // Assert
        assertNotNull("Attendance should not be null", attendance);
        assertNotNull("Attendance ID should be generated", attendance.getAttendanceId());
        assertEquals("Attendance type should be CHECK_OUT", "CHECK_OUT", attendance.getType());
        assertEquals("Attendance count should increase by 1", 
                    initialCount + 1, attendanceService.getTotalAttendanceCount());
    }
    
    /**
     * Test Case: TC-ATT-003
     * Test attendance record is saved after check-in
     * Expected: AttendanceRecord created and saved
     */
    @Test
    public void testAttendanceRecordCreatedOnCheckIn() {
        // Arrange
        int initialRecordCount = attendanceService.getTotalRecordsCount();
        
        // Act
        attendanceService.checkIn(testStudent, "Main Campus");
        
        // Assert
        assertEquals("Record count should increase by 1", 
                    initialRecordCount + 1, attendanceService.getTotalRecordsCount());
        
        List<AttendanceRecord> studentRecords = attendanceService.getAttendanceHistory(testStudent.getUserId());
        assertFalse("Student should have at least one record", studentRecords.isEmpty());
    }
    
    /**
     * Test Case: TC-ATT-004
     * Test retrieving attendance history for a student
     * Expected: Returns list of student's attendance records
     */
    @Test
    public void testGetAttendanceHistory() {
        // Arrange
        attendanceService.checkIn(testStudent, "Main Campus");
        attendanceService.checkOut(testStudent, "Main Campus");
        
        // Act
        List<AttendanceRecord> history = attendanceService.getAttendanceHistory(testStudent.getUserId());
        
        // Assert
        assertNotNull("History should not be null", history);
        assertTrue("History should contain at least 2 records", history.size() >= 2);
        
        // Verify records belong to test student
        for (AttendanceRecord record : history) {
            assertEquals("Record should belong to test student", 
                        testStudent.getUserId(), record.getStudentId());
        }
    }
    
    /**
     * Test Case: TC-ATT-005
     * Test getting all attendance records
     * Expected: Returns all attendance records in system
     */
    @Test
    public void testGetAllAttendanceRecords() {
        // Arrange
        int initialCount = attendanceService.getAllAttendanceRecords().size();
        attendanceService.checkIn(testStudent, "Main Campus");
        
        // Act
        List<AttendanceRecord> allRecords = attendanceService.getAllAttendanceRecords();
        
        // Assert
        assertNotNull("All records list should not be null", allRecords);
        assertEquals("All records count should increase", 
                    initialCount + 1, allRecords.size());
    }
    
    /**
     * Test Case: TC-ATT-006
     * Test marking student absent
     * Expected: Absence record created with ABSENT status
     */
    @Test
    public void testMarkStudentAbsent() {
        // Arrange
        String remarks = "No show";
        int initialCount = attendanceService.getTotalRecordsCount();
        
        // Act
        attendanceService.markAbsent(testStudent.getUserId(), remarks);
        
        // Assert
        List<AttendanceRecord> records = attendanceService.getAttendanceHistory(testStudent.getUserId());
        assertEquals("Record count should increase", initialCount + 1, attendanceService.getTotalRecordsCount());
        
        boolean hasAbsentRecord = false;
        for (AttendanceRecord record : records) {
            if (record.getStatus() == Status.ABSENT && record.getRemarks().equals(remarks)) {
                hasAbsentRecord = true;
                break;
            }
        }
        assertTrue("Should have an absent record with correct remarks", hasAbsentRecord);
    }
    
    /**
     * Test Case: TC-ATT-007
     * Test marking student late
     * Expected: Late record created with LATE status
     */
    @Test
    public void testMarkStudentLate() {
        // Arrange
        String remarks = "Arrived 20 minutes late";
        
        // Act
        attendanceService.markLate(testStudent.getUserId(), remarks);
        
        // Assert
        List<AttendanceRecord> records = attendanceService.getAttendanceHistory(testStudent.getUserId());
        
        boolean hasLateRecord = false;
        for (AttendanceRecord record : records) {
            if (record.getStatus() == Status.LATE && record.getRemarks().equals(remarks)) {
                hasLateRecord = true;
                break;
            }
        }
        assertTrue("Should have a late record with correct remarks", hasLateRecord);
    }
    
    /**
     * Test Case: TC-ATT-008
     * Test getting currently checked-in students
     * Expected: Returns list of students who checked in today
     */
    @Test
    public void testGetCurrentlyCheckedInStudents() {
        // Arrange
        attendanceService.checkIn(testStudent, "Main Campus");
        
        // Act
        List<AttendanceRecord> checkedInStudents = attendanceService.getCurrentlyCheckedInStudents();
        
        // Assert
        assertNotNull("Checked-in students list should not be null", checkedInStudents);
        assertTrue("Should have at least one checked-in student", checkedInStudents.size() > 0);
    }
    
    /**
     * Test Case: TC-ATT-009
     * Test getting records by status
     * Expected: Returns only records with specified status
     */
    @Test
    public void testGetRecordsByStatus() {
        // Arrange
        attendanceService.checkIn(testStudent, "Main Campus");
        attendanceService.markAbsent("ABSENT001", "Did not attend");
        
        // Act
        List<AttendanceRecord> presentRecords = attendanceService.getRecordsByStatus(Status.PRESENT);
        List<AttendanceRecord> absentRecords = attendanceService.getRecordsByStatus(Status.ABSENT);
        
        // Assert
        assertNotNull("Present records should not be null", presentRecords);
        assertNotNull("Absent records should not be null", absentRecords);
        
        // Verify all records have correct status
        for (AttendanceRecord record : presentRecords) {
            assertEquals("Should be PRESENT status", Status.PRESENT, record.getStatus());
        }
        for (AttendanceRecord record : absentRecords) {
            assertEquals("Should be ABSENT status", Status.ABSENT, record.getStatus());
        }
    }
    
    /**
     * Test Case: TC-ATT-010
     * Test multiple check-ins for same student
     * Expected: Multiple attendance records created
     */
    @Test
    public void testMultipleCheckInsForSameStudent() {
        // Arrange
        int initialCount = attendanceService.getAttendanceHistory(testStudent.getUserId()).size();
        
        // Act
        attendanceService.checkIn(testStudent, "Main Campus");
        attendanceService.checkIn(testStudent, "Library");
        attendanceService.checkIn(testStudent, "Lab");
        
        // Assert
        List<AttendanceRecord> history = attendanceService.getAttendanceHistory(testStudent.getUserId());
        assertEquals("Should have 3 more records", initialCount + 3, history.size());
    }
}
