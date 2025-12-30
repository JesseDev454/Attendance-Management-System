package com.nileuniversity.tams.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nileuniversity.tams.R;

/**
 * Dashboard for Student users
 * Phase 3: Basic stub implementation
 * Phase 4: Will add full functionality
 */
public class StudentDashboardActivity extends AppCompatActivity {
    
    private TextView tvWelcome;
    private TextView tvRole;
    private Button btnCheckIn;
    private Button btnCheckOut;
    private Button btnViewHistory;
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
        System.out.println("Student Dashboard loaded for: " + username);
        System.out.println("User ID: " + userId);
        System.out.println("Role: " + userRole);
    }
}
