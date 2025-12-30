package com.nileuniversity.tams.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nileuniversity.tams.R;

/**
 * Dashboard for Administrator users
 * Phase 3: Basic stub implementation
 * Phase 4: Will add full functionality
 */
public class AdminDashboardActivity extends AppCompatActivity {
    
    private TextView tvWelcome;
    private TextView tvRole;
    private Button btnAddUser;
    private Button btnConfigurePolicies;
    private Button btnGenerateReports;
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
        System.out.println("Admin Dashboard loaded for: " + username);
        System.out.println("User ID: " + userId);
        System.out.println("Role: " + userRole);
    }
}
