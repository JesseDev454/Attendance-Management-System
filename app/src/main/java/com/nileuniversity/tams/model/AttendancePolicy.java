package com.nileuniversity.tams.model;

/**
 * AttendancePolicy class
 * Represents attendance policy settings
 */
public class AttendancePolicy {
    private int lateThresholdMinutes;
    private int maxAllowedAbsences;
    private boolean requireLocationValidation;
    private String policyDescription;

    /**
     * Constructor with default values
     */
    public AttendancePolicy() {
        this.lateThresholdMinutes = 15;
        this.maxAllowedAbsences = 3;
        this.requireLocationValidation = true;
        this.policyDescription = "Default attendance policy";
    }

    /**
     * Constructor with parameters
     */
    public AttendancePolicy(int lateThresholdMinutes, int maxAllowedAbsences, 
                          boolean requireLocationValidation, String policyDescription) {
        this.lateThresholdMinutes = lateThresholdMinutes;
        this.maxAllowedAbsences = maxAllowedAbsences;
        this.requireLocationValidation = requireLocationValidation;
        this.policyDescription = policyDescription;
    }

    // Getters and Setters
    public int getLateThresholdMinutes() {
        return lateThresholdMinutes;
    }

    public void setLateThresholdMinutes(int lateThresholdMinutes) {
        this.lateThresholdMinutes = lateThresholdMinutes;
    }

    public int getMaxAllowedAbsences() {
        return maxAllowedAbsences;
    }

    public void setMaxAllowedAbsences(int maxAllowedAbsences) {
        this.maxAllowedAbsences = maxAllowedAbsences;
    }

    public boolean isRequireLocationValidation() {
        return requireLocationValidation;
    }

    public void setRequireLocationValidation(boolean requireLocationValidation) {
        this.requireLocationValidation = requireLocationValidation;
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription;
    }
}
