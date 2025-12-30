package com.nileuniversity.tams.ui.staff;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nileuniversity.tams.R;

/**
 * Dashboard for Staff users
 * Phase 3: Basic stub implementation
 * Phase 4: Will add full functionality
 */
public class StaffDashboardActivity extends AppCompatActivity {
    
    private TextView tvWelcome;
    private TextView tvRole;
    private Button btnMonitorAttendance;
    private Button btnViewRecords;
    private Button btnManageSchedule;
    private Button btnLogout;
    
    private String userId;
    private String username;
    private String userRole;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Get user data from intent
        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");
        username = intent.getStringExtra("USERNAME");
        userRole = intent.getStringExtra("USER_ROLE");
        
        // For Phase 3: Simple placeholder message
        // Phase 4 will add proper UI
        System.out.println("Staff Dashboard loaded for: " + username);
        System.out.println("User ID: " + userId);
        System.out.println("Role: " + userRole);
    }
}
