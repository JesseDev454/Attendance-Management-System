package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.AttendancePolicy;

/**
 * PolicyService
 * Manages attendance policy configuration
 * Uses in-memory storage
 */
public class PolicyService {
    private static PolicyService instance;
    private AttendancePolicy currentPolicy;

    /**
     * Private constructor for Singleton pattern
     */
    private PolicyService() {
        // Initialize with default policy
        currentPolicy = new AttendancePolicy();
    }

    /**
     * Get singleton instance
     */
    public static PolicyService getInstance() {
        if (instance == null) {
            instance = new PolicyService();
        }
        return instance;
    }

    /**
     * Get current attendance policy
     */
    public AttendancePolicy getCurrentPolicy() {
        return currentPolicy;
    }

    /**
     * Update attendance policy
     */
    public void updatePolicy(AttendancePolicy newPolicy) {
        this.currentPolicy = newPolicy;
    }

    /**
     * Update specific policy settings
     */
    public void updatePolicySettings(int lateThreshold, int maxAbsences, 
                                    boolean requireLocation, String description) {
        currentPolicy.setLateThresholdMinutes(lateThreshold);
        currentPolicy.setMaxAllowedAbsences(maxAbsences);
        currentPolicy.setRequireLocationValidation(requireLocation);
        currentPolicy.setPolicyDescription(description);
    }

    /**
     * Reset policy to defaults
     */
    public void resetToDefaults() {
        currentPolicy = new AttendancePolicy();
    }
}
