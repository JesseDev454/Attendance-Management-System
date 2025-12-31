package com.nileuniversity.tams.ui.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.AttendancePolicy;
import com.nileuniversity.tams.service.PolicyService;

/**
 * ConfigurePoliciesActivity
 * Allows admin to view and update attendance policies
 */
public class ConfigurePoliciesActivity extends AppCompatActivity {
    
    private TextView tvCurrentLateThreshold;
    private TextView tvCurrentMaxAbsences;
    private TextView tvCurrentLocationRequired;
    private TextView tvCurrentDescription;
    
    private EditText etLateThreshold;
    private EditText etMaxAbsences;
    private CheckBox cbRequireLocation;
    private EditText etDescription;
    
    private MaterialButton btnUpdatePolicy;
    private MaterialButton btnResetDefaults;
    private MaterialButton btnBack;
    
    private PolicyService policyService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_policies);
        
        // Initialize service
        policyService = PolicyService.getInstance();
        
        // Initialize views
        initializeViews();
        loadCurrentPolicy();
        setupClickListeners();
    }
    
    private void initializeViews() {
        tvCurrentLateThreshold = findViewById(R.id.tvCurrentLateThreshold);
        tvCurrentMaxAbsences = findViewById(R.id.tvCurrentMaxAbsences);
        tvCurrentLocationRequired = findViewById(R.id.tvCurrentLocationRequired);
        tvCurrentDescription = findViewById(R.id.tvCurrentDescription);
        
        etLateThreshold = findViewById(R.id.etLateThreshold);
        etMaxAbsences = findViewById(R.id.etMaxAbsences);
        cbRequireLocation = findViewById(R.id.cbRequireLocation);
        etDescription = findViewById(R.id.etDescription);
        
        btnUpdatePolicy = findViewById(R.id.btnUpdatePolicy);
        btnResetDefaults = findViewById(R.id.btnResetDefaults);
        btnBack = findViewById(R.id.btnBack);
    }
    
    private void loadCurrentPolicy() {
        AttendancePolicy policy = policyService.getCurrentPolicy();
        
        // Display current policy
        tvCurrentLateThreshold.setText("Late Threshold: " + policy.getLateThresholdMinutes() + " minutes");
        tvCurrentMaxAbsences.setText("Max Allowed Absences: " + policy.getMaxAllowedAbsences());
        tvCurrentLocationRequired.setText("Location Validation: " + 
            (policy.isRequireLocationValidation() ? "Required" : "Not Required"));
        tvCurrentDescription.setText("Description: " + policy.getPolicyDescription());
        
        // Pre-fill input fields with current values
        etLateThreshold.setText(String.valueOf(policy.getLateThresholdMinutes()));
        etMaxAbsences.setText(String.valueOf(policy.getMaxAllowedAbsences()));
        cbRequireLocation.setChecked(policy.isRequireLocationValidation());
        etDescription.setText(policy.getPolicyDescription());
    }
    
    private void setupClickListeners() {
        btnUpdatePolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUpdatePolicy();
            }
        });
        
        btnResetDefaults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleResetDefaults();
            }
        });
        
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    private void handleUpdatePolicy() {
        try {
            // Get input values
            int lateThreshold = Integer.parseInt(etLateThreshold.getText().toString().trim());
            int maxAbsences = Integer.parseInt(etMaxAbsences.getText().toString().trim());
            boolean requireLocation = cbRequireLocation.isChecked();
            String description = etDescription.getText().toString().trim();
            
            // Validate inputs
            if (lateThreshold < 0 || maxAbsences < 0) {
                Toast.makeText(this, "Values cannot be negative", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (description.isEmpty()) {
                description = "Custom attendance policy";
            }
            
            // Update policy
            policyService.updatePolicySettings(lateThreshold, maxAbsences, requireLocation, description);
            
            // Reload display
            loadCurrentPolicy();
            
            Toast.makeText(this, "Policy updated successfully", Toast.LENGTH_SHORT).show();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void handleResetDefaults() {
        policyService.resetToDefaults();
        loadCurrentPolicy();
        Toast.makeText(this, "Policy reset to defaults", Toast.LENGTH_SHORT).show();
    }
}
