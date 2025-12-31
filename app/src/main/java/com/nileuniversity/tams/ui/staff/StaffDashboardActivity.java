package com.nileuniversity.tams.ui.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.Staff;
import com.nileuniversity.tams.model.Schedule;
import com.nileuniversity.tams.model.AttendanceRecord;
import com.nileuniversity.tams.service.AttendanceService;
import com.nileuniversity.tams.service.ScheduleService;
import com.nileuniversity.tams.service.AuthenticationService;
import com.nileuniversity.tams.ui.auth.LoginActivity;
import java.util.List;

/**
 * Dashboard for Staff users
 * Phase 4: Full implementation with monitoring functionality
 */
public class StaffDashboardActivity extends AppCompatActivity {
    
    private TextView tvWelcome;
    private TextView tvRole;
    private MaterialButton btnMonitorAttendance;
    private MaterialButton btnViewRecords;
    private MaterialButton btnManageSchedule;
    private MaterialButton btnLogout;
    
    private String userId;
    private String username;
    private String userRole;
    
    private AttendanceService attendanceService;
    private ScheduleService scheduleService;
    private AuthenticationService authService;
    private Staff currentStaff;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);
        
        // Get user data from intent
        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");
        username = intent.getStringExtra("USERNAME");
        userRole = intent.getStringExtra("USER_ROLE");
        
        // Initialize services
        attendanceService = AttendanceService.getInstance();
        scheduleService = ScheduleService.getInstance();
        authService = AuthenticationService.getInstance();
        currentStaff = (Staff) authService.getCurrentUser();
        
        // Initialize UI
        initializeViews();
        displayUserInfo();
        setupClickListeners();
    }
    
    private void initializeViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        tvRole = findViewById(R.id.tvRole);
        btnMonitorAttendance = findViewById(R.id.btnMonitorAttendance);
        btnViewRecords = findViewById(R.id.btnViewRecords);
        btnManageSchedule = findViewById(R.id.btnManageSchedule);
        btnLogout = findViewById(R.id.btnLogout);
    }
    
    private void displayUserInfo() {
        tvWelcome.setText("Welcome, " + username);
        tvRole.setText("Role: " + userRole);
    }
    
    private void setupClickListeners() {
        btnMonitorAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleMonitorAttendance();
            }
        });
        
        btnViewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleViewRecords();
            }
        });
        
        btnManageSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleManageSchedule();
            }
        });
        
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();
            }
        });
    }
    
    private void handleMonitorAttendance() {
        // Monitor real-time attendance - Navigate to MonitorAttendanceActivity
        currentStaff.monitorRealTime();
        Intent intent = new Intent(StaffDashboardActivity.this, MonitorAttendanceActivity.class);
        startActivity(intent);
    }
    
    private void handleViewRecords() {
        // View class records - Navigate to ViewClassRecordsActivity
        currentStaff.viewClassRecords();
        Intent intent = new Intent(StaffDashboardActivity.this, ViewClassRecordsActivity.class);
        startActivity(intent);
    }
    
    private void handleManageSchedule() {
        // Manage schedule - Navigate to ManageScheduleActivity
        currentStaff.manageSchedule();
        Intent intent = new Intent(StaffDashboardActivity.this, ManageScheduleActivity.class);
        startActivity(intent);
    }
    
    private void handleLogout() {
        authService.logout();
        Intent intent = new Intent(StaffDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        // Disable back button to prevent going back to login
    }
}
