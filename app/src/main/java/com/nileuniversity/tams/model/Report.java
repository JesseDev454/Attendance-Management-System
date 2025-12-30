package com.nileuniversity.tams.model;

import java.util.Date;

/**
 * Report class
 * Represents generated attendance reports
 */
public class Report {
    private String reportId;
    private Date generatedDate;
    private String format; // "PDF" or "CSV"
    private String content;

    /**
     * Constructor
     */
    public Report(String reportId, String format) {
        this.reportId = reportId;
        this.format = format;
        this.generatedDate = new Date();
        this.content = "";
    }

    /**
     * Generate attendance report
     */
    public void generateReport() {
        // Logic to generate report
        this.generatedDate = new Date();
        System.out.println("Report generated: " + reportId + " in " + format + " format");
    }

    /**
     * Analyze attendance trends
     */
    public void analyzeTrends() {
        // Logic to analyze data
        System.out.println("Analyzing attendance trends for report: " + reportId);
    }

    // Getters and Setters
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
