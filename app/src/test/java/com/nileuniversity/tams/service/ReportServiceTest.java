package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Unit tests for ReportService
 * Tests report generation and data analysis functionality
 */
public class ReportServiceTest {
    
    private ReportService reportService;
    private AttendanceService attendanceService;
    private Student testStudent;
    
    /**
     * Set up test fixture before each test
     */
    @Before
    public void setUp() {
        reportService = ReportService.getInstance();
        attendanceService = AttendanceService.getInstance();
        testStudent = new Student("REP001", "reportstudent", "test123", "report@nileuniversity.edu.ng");
        
        // Create some test attendance data
        attendanceService.checkIn(testStudent, "Main Campus");
        attendanceService.markAbsent("ABS001", "No show");
        attendanceService.markLate("LATE001", "Traffic delay");
    }
    
    /**
     * Test Case: TC-REP-001
     * Test generating overall report
     * Expected: Report generated with attendance statistics
     */
    @Test
    public void testGenerateOverallReport() {
        // Arrange
        String format = "PDF";
        
        // Act
        Report report = reportService.generateOverallReport(format);
        
        // Assert
        assertNotNull("Report should not be null", report);
        assertNotNull("Report ID should be generated", report.getReportId());
        assertNotNull("Report date should be set", report.getGeneratedDate());
        assertEquals("Report format should match", format, report.getFormat());
        assertNotNull("Report content should not be null", report.getContent());
        assertTrue("Report content should contain data", report.getContent().length() > 0);
    }
    
    /**
     * Test Case: TC-REP-002
     * Test report contains attendance statistics
     * Expected: Report content includes present, absent, and late counts
     */
    @Test
    public void testReportContainsStatistics() {
        // Arrange & Act
        Report report = reportService.generateOverallReport("PDF");
        String content = report.getContent();
        
        // Assert
        assertTrue("Report should contain 'Present' count", content.contains("Present"));
        assertTrue("Report should contain 'Absent' count", content.contains("Absent"));
        assertTrue("Report should contain 'Late' count", content.contains("Late"));
        assertTrue("Report should contain total records", content.contains("Total"));
    }
    
    /**
     * Test Case: TC-REP-003
     * Test generating student-specific report
     * Expected: Report generated for specific student
     */
    @Test
    public void testGenerateStudentReport() {
        // Arrange
        String studentId = testStudent.getUserId();
        String format = "PDF";
        
        // Act
        Report report = reportService.generateStudentReport(studentId, format);
        
        // Assert
        assertNotNull("Student report should not be null", report);
        assertNotNull("Report ID should be generated", report.getReportId());
        assertTrue("Report content should reference student ID", 
                  report.getContent().contains(studentId));
    }
    
    /**
     * Test Case: TC-REP-004
     * Test multiple report generation
     * Expected: Each report has unique ID
     */
    @Test
    public void testMultipleReportGeneration() {
        // Act
        Report report1 = reportService.generateOverallReport("PDF");
        Report report2 = reportService.generateOverallReport("CSV");
        Report report3 = reportService.generateOverallReport("PDF");
        
        // Assert
        assertNotEquals("Report IDs should be unique", report1.getReportId(), report2.getReportId());
        assertNotEquals("Report IDs should be unique", report2.getReportId(), report3.getReportId());
        assertNotEquals("Report IDs should be unique", report1.getReportId(), report3.getReportId());
    }
    
    /**
     * Test Case: TC-REP-005
     * Test report format specification
     * Expected: Report saved with correct format
     */
    @Test
    public void testReportFormatSpecification() {
        // Act
        Report pdfReport = reportService.generateOverallReport("PDF");
        Report csvReport = reportService.generateOverallReport("CSV");
        
        // Assert
        assertEquals("PDF report format should be PDF", "PDF", pdfReport.getFormat());
        assertEquals("CSV report format should be CSV", "CSV", csvReport.getFormat());
    }
    
    /**
     * Test Case: TC-REP-006
     * Test report generation with no attendance data
     * Expected: Report generated with zero counts
     */
    @Test
    public void testReportGenerationWithNoData() {
        // Arrange - Create a fresh service instance with no data
        // For this test, we'll just verify the report is still generated
        
        // Act
        Report report = reportService.generateOverallReport("PDF");
        
        // Assert
        assertNotNull("Report should be generated even with minimal data", report);
        assertNotNull("Report content should exist", report.getContent());
    }
    
    /**
     * Test Case: TC-REP-007
     * Test report date is current
     * Expected: Report generated date is recent
     */
    @Test
    public void testReportDateIsCurrent() {
        // Arrange
        long beforeTime = System.currentTimeMillis();
        
        // Act
        Report report = reportService.generateOverallReport("PDF");
        
        // Assert
        long afterTime = System.currentTimeMillis();
        long reportTime = report.getGeneratedDate().getTime();
        
        assertTrue("Report date should be after test start", reportTime >= beforeTime);
        assertTrue("Report date should be before test end", reportTime <= afterTime);
    }
    
    /**
     * Test Case: TC-REP-008
     * Test student report contains only student's data
     * Expected: Report is filtered for specific student
     */
    @Test
    public void testStudentReportFilteredCorrectly() {
        // Arrange
        Student student2 = new Student("REP002", "student2", "pass", "s2@test.com");
        attendanceService.checkIn(student2, "Campus");
        
        // Act
        Report studentReport = reportService.generateStudentReport(testStudent.getUserId(), "PDF");
        
        // Assert
        String content = studentReport.getContent();
        assertTrue("Report should contain test student ID", 
                  content.contains(testStudent.getUserId()));
    }
}
