package com.nileuniversity.tams.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.Student;
import com.nileuniversity.tams.model.AttendanceRecord;
import com.nileuniversity.tams.service.AttendanceService;
import com.nileuniversity.tams.service.AuthenticationService;
import com.nileuniversity.tams.ui.auth.LoginActivity;
import java.util.List;

/**
 * Dashboard for Student users
 * Phase 4: Full implementation with attendance functionality
 */
public class StudentDashboardActivity extends AppCompatActivity {
    
    private TextView tvWelcome;
    private TextView tvRole;
    private MaterialButton btnCheckIn;
    private MaterialButton btnCheckOut;
    private MaterialButton btnViewHistory;
    private MaterialButton btnLogout;
    
    private String userId;
    private String username;
    private String userRole;
    
    private AttendanceService attendanceService;
    private AuthenticationService authService;
    private Student currentStudent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        
        // Get user data from intent
        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");
        username = intent.getStringExtra("USERNAME");
        userRole = intent.getStringExtra("USER_ROLE");
        
        // Initialize services
        attendanceService = AttendanceService.getInstance();
        authService = AuthenticationService.getInstance();
        currentStudent = (Student) authService.getCurrentUser();
        
        // Initialize UI
        initializeViews();
        displayUserInfo();
        setupClickListeners();
    }
    
    private void initializeViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        tvRole = findViewById(R.id.tvRole);
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnViewHistory = findViewById(R.id.btnViewHistory);
        btnLogout = findViewById(R.id.btnLogout);
    }
    
    private void displayUserInfo() {
        tvWelcome.setText("Welcome, " + username);
        tvRole.setText("Role: " + userRole);
    }
    
    private void setupClickListeners() {
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCheckIn();
            }
        });
        
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCheckOut();
            }
        });
        
        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleViewHistory();
            }
        });
        
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();
            }
        });
    }
    
    private void handleCheckIn() {
        try {
            attendanceService.checkIn(currentStudent, "Campus Location");
            Toast.makeText(this, "Check-in successful!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Check-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void handleCheckOut() {
        try {
            attendanceService.checkOut(currentStudent, "Campus Location");
            Toast.makeText(this, "Check-out successful!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Check-out failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void handleViewHistory() {
        List<AttendanceRecord> history = attendanceService.getAttendanceHistory(userId);
        if (history.isEmpty()) {
            Toast.makeText(this, "No attendance records found", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder message = new StringBuilder("Attendance Records (" + history.size() + "):\n\n");
            for (AttendanceRecord record : history) {
                message.append(record.getDate()).append(" - ").append(record.getStatus()).append("\n");
            }
            Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show();
        }
    }
    
    private void handleLogout() {
        authService.logout();
        Intent intent = new Intent(StudentDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        // Disable back button to prevent going back to login
    }
}
