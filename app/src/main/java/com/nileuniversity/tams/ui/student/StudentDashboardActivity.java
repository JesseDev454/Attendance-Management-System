package com.nileuniversity.tams.ui.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.AttendanceMethod;
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
    private MaterialButton btnCheckInBiometric;
    private MaterialButton btnCheckInQR;
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
        btnCheckInBiometric = findViewById(R.id.btnCheckInBiometric);
        btnCheckInQR = findViewById(R.id.btnCheckInQR);
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
        
        btnCheckInBiometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBiometricCheckIn();
            }
        });
        
        btnCheckInQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleQRCheckIn();
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
    
    /**
     * Handle biometric check-in with simulated verification
     */
    private void handleBiometricCheckIn() {
        // Show biometric verification dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Biometric Verification");
        builder.setMessage("Place your finger on the sensor...\n\nVerifying...");
        builder.setCancelable(false);
        
        final AlertDialog dialog = builder.create();
        dialog.show();
        
        // Simulate biometric verification (1.5 seconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                
                // Show success and perform check-in
                try {
                    attendanceService.checkIn(currentStudent, "Campus Location", AttendanceMethod.BIOMETRIC);
                    
                    AlertDialog.Builder successBuilder = new AlertDialog.Builder(StudentDashboardActivity.this);
                    successBuilder.setTitle("Success");
                    successBuilder.setMessage("Biometric verification successful!\nCheck-in recorded.");
                    successBuilder.setPositiveButton("OK", null);
                    successBuilder.show();
                } catch (Exception e) {
                    Toast.makeText(StudentDashboardActivity.this, "Biometric check-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, 1500);
    }
    
    /**
     * Handle QR code check-in with simulated scan
     */
    private void handleQRCheckIn() {
        // Show QR scan dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("QR Code Scanner");
        builder.setMessage("Position QR code within frame...\n\n[▢▢▢▢▢]\n[▢▢▢▢▢]\n[▢▢▢▢▢]\n\nScanning...");
        builder.setCancelable(false);
        
        final AlertDialog dialog = builder.create();
        dialog.show();
        
        // Simulate QR scan (2 seconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                
                // Show success and perform check-in
                try {
                    attendanceService.checkIn(currentStudent, "Campus Location", AttendanceMethod.QR_CODE);
                    
                    AlertDialog.Builder successBuilder = new AlertDialog.Builder(StudentDashboardActivity.this);
                    successBuilder.setTitle("Success");
                    successBuilder.setMessage("QR code scanned successfully!\nCheck-in recorded.");
                    successBuilder.setPositiveButton("OK", null);
                    successBuilder.show();
                } catch (Exception e) {
                    Toast.makeText(StudentDashboardActivity.this, "QR check-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);
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
        Intent intent = new Intent(StudentDashboardActivity.this, AttendanceHistoryActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
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
