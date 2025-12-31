package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * ReportService
 * Generates attendance reports and analyzes trends
 * Uses in-memory data
 */
public class ReportService {
    private static ReportService instance;
    private List<Report> reports;
    private int reportCounter = 1;
    private AttendanceService attendanceService;

    /**
     * Private constructor for Singleton pattern
     */
    private ReportService() {
        reports = new ArrayList<>();
        attendanceService = AttendanceService.getInstance();
    }

    /**
     * Get singleton instance
     */
    public static ReportService getInstance() {
        if (instance == null) {
            instance = new ReportService();
        }
        return instance;
    }

    /**
     * Generate attendance report for a student
     */
    public Report generateStudentReport(String studentId, String format) {
        String reportId = "RPT" + String.format("%04d", reportCounter++);
        Report report = new Report(reportId, format);
        
        // Get student attendance records
        List<AttendanceRecord> records = attendanceService.getAttendanceHistory(studentId);
        
        // Build report content
        StringBuilder content = new StringBuilder();
        content.append("ATTENDANCE REPORT - STUDENT ID: ").append(studentId).append("\n");
        content.append("Generated: ").append(new Date()).append("\n");
        content.append("Total Records: ").append(records.size()).append("\n\n");
        
        // Calculate statistics
        int present = 0, absent = 0, late = 0, excused = 0;
        for (AttendanceRecord record : records) {
            switch (record.getStatus()) {
                case PRESENT: present++; break;
                case ABSENT: absent++; break;
                case LATE: late++; break;
                case EXCUSED: excused++; break;
            }
        }
        
        content.append("Statistics:\n");
        content.append("Present: ").append(present).append("\n");
        content.append("Absent: ").append(absent).append("\n");
        content.append("Late: ").append(late).append("\n");
        content.append("Excused: ").append(excused).append("\n");
        
        // Calculate method breakdown
        int manual = 0, biometric = 0, qrCode = 0;
        for (AttendanceRecord record : records) {
            switch (record.getMethod()) {
                case MANUAL: manual++; break;
                case BIOMETRIC: biometric++; break;
                case QR_CODE: qrCode++; break;
            }
        }
        
        content.append("\nAttendance Method Breakdown:\n");
        content.append("Manual: ").append(manual).append("\n");
        content.append("Biometric: ").append(biometric).append("\n");
        content.append("QR Code: ").append(qrCode).append("\n");
        
        if (records.size() > 0) {
            double attendanceRate = (present + late) * 100.0 / records.size();
            content.append("\nAttendance Rate: ").append(String.format("%.2f", attendanceRate)).append("%\n");
        }
        
        report.setContent(content.toString());
        report.generateReport();
        reports.add(report);
        
        return report;
    }

    /**
     * Generate overall attendance report
     */
    public Report generateOverallReport(String format) {
        String reportId = "RPT" + String.format("%04d", reportCounter++);
        Report report = new Report(reportId, format);
        
        // Get all attendance records
        List<AttendanceRecord> allRecords = attendanceService.getAllAttendanceRecords();
        
        // Build report content
        StringBuilder content = new StringBuilder();
        content.append("OVERALL ATTENDANCE REPORT\n");
        content.append("Generated: ").append(new Date()).append("\n");
        content.append("Total Records: ").append(allRecords.size()).append("\n\n");
        
        // Calculate overall statistics
        int present = 0, absent = 0, late = 0, excused = 0;
        for (AttendanceRecord record : allRecords) {
            switch (record.getStatus()) {
                case PRESENT: present++; break;
                case ABSENT: absent++; break;
                case LATE: late++; break;
                case EXCUSED: excused++; break;
            }
        }
        
        content.append("Overall Statistics:\n");
        content.append("Present: ").append(present).append("\n");
        content.append("Absent: ").append(absent).append("\n");
        content.append("Late: ").append(late).append("\n");
        content.append("Excused: ").append(excused).append("\n");
        
        // Calculate method breakdown for overall report
        int manual = 0, biometric = 0, qrCode = 0;
        for (AttendanceRecord record : allRecords) {
            switch (record.getMethod()) {
                case MANUAL: manual++; break;
                case BIOMETRIC: biometric++; break;
                case QR_CODE: qrCode++; break;
            }
        }
        
        content.append("\nAttendance Method Breakdown:\n");
        content.append("Manual: ").append(manual).append("\n");
        content.append("Biometric: ").append(biometric).append("\n");
        content.append("QR Code: ").append(qrCode).append("\n");
        
        if (allRecords.size() > 0) {
            double attendanceRate = (present + late) * 100.0 / allRecords.size();
            content.append("\nOverall Attendance Rate: ").append(String.format("%.2f", attendanceRate)).append("%\n");
        }
        
        report.setContent(content.toString());
        report.generateReport();
        reports.add(report);
        
        return report;
    }

    /**
     * Generate daily report
     */
    public Report generateDailyReport(Date date, String format) {
        String reportId = "RPT" + String.format("%04d", reportCounter++);
        Report report = new Report(reportId, format);
        
        // Get records for the date
        List<AttendanceRecord> dateRecords = attendanceService.getRecordsByDate(date);
        
        // Build report content
        StringBuilder content = new StringBuilder();
        content.append("DAILY ATTENDANCE REPORT\n");
        content.append("Date: ").append(date).append("\n");
        content.append("Generated: ").append(new Date()).append("\n");
        content.append("Total Records: ").append(dateRecords.size()).append("\n\n");
        
        // List all records
        for (AttendanceRecord record : dateRecords) {
            content.append("Student: ").append(record.getStudentId())
                   .append(" | Status: ").append(record.getStatus())
                   .append(" | Remarks: ").append(record.getRemarks()).append("\n");
        }
        
        report.setContent(content.toString());
        report.generateReport();
        reports.add(report);
        
        return report;
    }

    /**
     * Analyze attendance trends
     */
    public String analyzeTrends() {
        List<AttendanceRecord> allRecords = attendanceService.getAllAttendanceRecords();
        
        if (allRecords.isEmpty()) {
            return "No data available for trend analysis.";
        }
        
        StringBuilder analysis = new StringBuilder();
        analysis.append("ATTENDANCE TRENDS ANALYSIS\n");
        analysis.append("Generated: ").append(new Date()).append("\n\n");
        
        // Calculate trends
        int present = attendanceService.getRecordsByStatus(Status.PRESENT).size();
        int absent = attendanceService.getRecordsByStatus(Status.ABSENT).size();
        int late = attendanceService.getRecordsByStatus(Status.LATE).size();
        
        int total = allRecords.size();
        double presentRate = present * 100.0 / total;
        double absentRate = absent * 100.0 / total;
        double lateRate = late * 100.0 / total;
        
        analysis.append("Present Rate: ").append(String.format("%.2f", presentRate)).append("%\n");
        analysis.append("Absent Rate: ").append(String.format("%.2f", absentRate)).append("%\n");
        analysis.append("Late Rate: ").append(String.format("%.2f", lateRate)).append("%\n\n");
        
        // Provide insights
        if (absentRate > 20) {
            analysis.append("WARNING: High absence rate detected. Consider intervention.\n");
        }
        if (lateRate > 15) {
            analysis.append("WARNING: High late check-in rate. Review schedule timing.\n");
        }
        if (presentRate > 85) {
            analysis.append("SUCCESS: Good overall attendance rate.\n");
        }
        
        return analysis.toString();
    }

    /**
     * Get all generated reports
     */
    public List<Report> getAllReports() {
        return new ArrayList<>(reports);
    }

    /**
     * Get report by ID
     */
    public Report getReportById(String reportId) {
        for (Report report : reports) {
            if (report.getReportId().equals(reportId)) {
                return report;
            }
        }
        return null;
    }

    /**
     * Export report (placeholder - returns content as string)
     */
    public String exportReport(Report report) {
        if (report == null) return "";
        
        if ("PDF".equalsIgnoreCase(report.getFormat())) {
            return "PDF Export: " + report.getContent();
        } else if ("CSV".equalsIgnoreCase(report.getFormat())) {
            return "CSV Export: " + report.getContent();
        }
        
        return report.getContent();
    }
}
