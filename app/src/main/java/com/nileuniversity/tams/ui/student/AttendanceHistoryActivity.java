package com.nileuniversity.tams.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.AttendanceRecord;
import com.nileuniversity.tams.service.AttendanceService;
import com.nileuniversity.tams.service.AuthenticationService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Activity to display student's attendance history
 */
public class AttendanceHistoryActivity extends AppCompatActivity {
    
    private TextView tvStudentInfo;
    private TextView tvEmpty;
    private ListView lvAttendanceHistory;
    private MaterialButton btnBack;
    
    private String userId;
    private String username;
    private AttendanceService attendanceService;
    private AuthenticationService authService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history);
        
        // Get user data from intent
        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");
        username = intent.getStringExtra("USERNAME");
        
        // Initialize services
        attendanceService = AttendanceService.getInstance();
        authService = AuthenticationService.getInstance();
        
        // Initialize views
        initializeViews();
        loadAttendanceHistory();
    }
    
    private void initializeViews() {
        tvStudentInfo = findViewById(R.id.tvStudentInfo);
        tvEmpty = findViewById(R.id.tvEmpty);
        lvAttendanceHistory = findViewById(R.id.lvAttendanceHistory);
        btnBack = findViewById(R.id.btnBack);
        
        tvStudentInfo.setText("Student: " + username + " (ID: " + userId + ")");
        
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    private void loadAttendanceHistory() {
        List<AttendanceRecord> records = attendanceService.getAttendanceHistory(userId);
        
        if (records == null || records.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            lvAttendanceHistory.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            lvAttendanceHistory.setVisibility(View.VISIBLE);
            
            // Create string list for display
            List<String> displayItems = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            
            for (AttendanceRecord record : records) {
                StringBuilder item = new StringBuilder();
                item.append("Date: ").append(dateFormat.format(record.getDate())).append("\n");
                item.append("Status: ").append(record.getStatus()).append("\n");
                item.append("Method: ").append(record.getMethod()).append("\n");
                if (record.getRemarks() != null && !record.getRemarks().isEmpty()) {
                    item.append("Remarks: ").append(record.getRemarks());
                }
                displayItems.add(item.toString());
            }
            
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayItems
            );
            lvAttendanceHistory.setAdapter(adapter);
        }
    }
}
