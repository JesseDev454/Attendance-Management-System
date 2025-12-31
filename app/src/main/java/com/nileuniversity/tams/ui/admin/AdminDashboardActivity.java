package com.nileuniversity.tams.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.Administrator;
import com.nileuniversity.tams.model.Student;
import com.nileuniversity.tams.model.UserRole;
import com.nileuniversity.tams.model.Report;
import com.nileuniversity.tams.service.AuthenticationService;
import com.nileuniversity.tams.service.ReportService;
import com.nileuniversity.tams.ui.auth.LoginActivity;
import java.util.List;

/**
 * Dashboard for Administrator users
 * Phase 4: Full implementation with admin functionality
 */
public class AdminDashboardActivity extends AppCompatActivity {
    
    private TextView tvWelcome;
    private TextView tvRole;
    private MaterialButton btnAddUser;
    private MaterialButton btnConfigurePolicies;
    private MaterialButton btnGenerateReports;
    private MaterialButton btnLogout;
    
    private String userId;
    private String username;
    private String userRole;
    
    private AuthenticationService authService;
    private ReportService reportService;
    private Administrator currentAdmin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        
        // Get user data from intent
        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");
        username = intent.getStringExtra("USERNAME");
        userRole = intent.getStringExtra("USER_ROLE");
        
        // Initialize services
        authService = AuthenticationService.getInstance();
        reportService = ReportService.getInstance();
        currentAdmin = (Administrator) authService.getCurrentUser();
        
        // Initialize UI
        initializeViews();
        displayUserInfo();
        setupClickListeners();
    }
    
    private void initializeViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        tvRole = findViewById(R.id.tvRole);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnConfigurePolicies = findViewById(R.id.btnConfigurePolicies);
        btnGenerateReports = findViewById(R.id.btnGenerateReports);
        btnLogout = findViewById(R.id.btnLogout);
    }
    
    private void displayUserInfo() {
        tvWelcome.setText("Welcome, " + username);
        tvRole.setText("Role: " + userRole);
    }
    
    private void setupClickListeners() {
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddUser();
            }
        });
        
        btnConfigurePolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleConfigurePolicies();
            }
        });
        
        btnGenerateReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGenerateReports();
            }
        });
        
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();
            }
        });
    }
    
    private void handleAddUser() {
        // Simulate adding a new student user
        Student newStudent = new Student(
            "S" + String.format("%03d", (int)(Math.random() * 1000)),
            "newstudent" + (int)(Math.random() * 100),
            "password123",
            "student@nileuniversity.edu.ng"
        );
        
        try {
            // Add to authentication service so user can login
            authService.addUser(newStudent);
            currentAdmin.addUser(newStudent);
            Toast.makeText(this, "User added successfully: " + newStudent.getUsername() + "\nPassword: password123", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to add user: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void handleConfigurePolicies() {
        // Configure attendance policies - Navigate to ConfigurePoliciesActivity
        currentAdmin.configurePolicies();
        Intent intent = new Intent(AdminDashboardActivity.this, ConfigurePoliciesActivity.class);
        startActivity(intent);
    }
    
    private void handleGenerateReports() {
        // Monitor trends and generate overall report
        currentAdmin.monitorTrends();
        
        try {
            Report overallReport = reportService.generateOverallReport("PDF");
            
            // Open report view activity
            Intent intent = new Intent(AdminDashboardActivity.this, ReportViewActivity.class);
            intent.putExtra("REPORT_ID", overallReport.getReportId());
            intent.putExtra("REPORT_DATE", overallReport.getGeneratedDate().toString());
            intent.putExtra("REPORT_CONTENT", overallReport.getContent());
            intent.putExtra("REPORT_FORMAT", overallReport.getFormat());
            startActivity(intent);
            
            Toast.makeText(this, "Report generated successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Report generation failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void handleLogout() {
        authService.logout();
        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        // Disable back button to prevent going back to login
    }
}
