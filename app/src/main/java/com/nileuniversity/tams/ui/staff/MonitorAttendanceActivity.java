package com.nileuniversity.tams.ui.staff;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.AttendanceRecord;
import com.nileuniversity.tams.service.AttendanceService;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * MonitorAttendanceActivity
 * Displays list of currently checked-in students
 */
public class MonitorAttendanceActivity extends AppCompatActivity {
    
    private LinearLayout layoutAttendanceList;
    private TextView tvNoData;
    private MaterialButton btnBack;
    private AttendanceService attendanceService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_attendance);
        
        // Initialize service
        attendanceService = AttendanceService.getInstance();
        
        // Initialize views
        initializeViews();
        loadAttendanceData();
        setupClickListeners();
    }
    
    private void initializeViews() {
        layoutAttendanceList = findViewById(R.id.layoutAttendanceList);
        tvNoData = findViewById(R.id.tvNoData);
        btnBack = findViewById(R.id.btnBack);
    }
    
    private void loadAttendanceData() {
        // Clear existing views
        layoutAttendanceList.removeAllViews();
        
        // Get currently checked-in students
        List<AttendanceRecord> checkedInStudents = attendanceService.getCurrentlyCheckedInStudents();
        
        if (checkedInStudents.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            layoutAttendanceList.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            layoutAttendanceList.setVisibility(View.VISIBLE);
            
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            
            // Display each checked-in student
            for (AttendanceRecord record : checkedInStudents) {
                View itemView = getLayoutInflater().inflate(R.layout.item_attendance_record, layoutAttendanceList, false);
                
                TextView tvStudentId = itemView.findViewById(R.id.tvStudentId);
                TextView tvCheckInTime = itemView.findViewById(R.id.tvCheckInTime);
                TextView tvStatus = itemView.findViewById(R.id.tvStatus);
                TextView tvMethod = itemView.findViewById(R.id.tvMethod);
                
                tvStudentId.setText("Student: " + record.getStudentId());
                tvCheckInTime.setText("Time: " + timeFormat.format(record.getDate()));
                tvStatus.setText("Status: " + record.getStatus().toString());
                tvMethod.setText("Method: " + record.getMethod().toString());
                
                layoutAttendanceList.addView(itemView);
            }
        }
    }
    
    private void setupClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
