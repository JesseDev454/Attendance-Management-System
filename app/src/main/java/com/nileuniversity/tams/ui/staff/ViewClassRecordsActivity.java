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
 * ViewClassRecordsActivity
 * Displays historical attendance records for classes
 */
public class ViewClassRecordsActivity extends AppCompatActivity {
    
    private LinearLayout layoutRecordsList;
    private TextView tvNoData;
    private MaterialButton btnBack;
    private AttendanceService attendanceService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class_records);
        
        // Initialize service
        attendanceService = AttendanceService.getInstance();
        
        // Initialize views
        initializeViews();
        loadClassRecords();
        setupClickListeners();
    }
    
    private void initializeViews() {
        layoutRecordsList = findViewById(R.id.layoutRecordsList);
        tvNoData = findViewById(R.id.tvNoData);
        btnBack = findViewById(R.id.btnBack);
    }
    
    private void loadClassRecords() {
        // Clear existing views
        layoutRecordsList.removeAllViews();
        
        // Get all attendance records
        List<AttendanceRecord> allRecords = attendanceService.getAllAttendanceRecords();
        
        if (allRecords.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            layoutRecordsList.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            layoutRecordsList.setVisibility(View.VISIBLE);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            
            // Display each record
            for (AttendanceRecord record : allRecords) {
                View itemView = getLayoutInflater().inflate(R.layout.item_class_record, layoutRecordsList, false);
                
                TextView tvStudentId = itemView.findViewById(R.id.tvStudentId);
                TextView tvDate = itemView.findViewById(R.id.tvDate);
                TextView tvTime = itemView.findViewById(R.id.tvTime);
                TextView tvStatus = itemView.findViewById(R.id.tvStatus);
                TextView tvRemarks = itemView.findViewById(R.id.tvRemarks);
                
                tvStudentId.setText("Student: " + record.getStudentId());
                tvDate.setText("Date: " + dateFormat.format(record.getDate()));
                tvTime.setText("Time: " + timeFormat.format(record.getDate()));
                tvStatus.setText("Status: " + record.getStatus().toString());
                
                // Set status color
                int statusColor = getStatusColor(record.getStatus().toString());
                tvStatus.setTextColor(statusColor);
                
                // Show remarks if available
                if (record.getRemarks() != null && !record.getRemarks().isEmpty()) {
                    tvRemarks.setVisibility(View.VISIBLE);
                    tvRemarks.setText("Remarks: " + record.getRemarks());
                } else {
                    tvRemarks.setVisibility(View.GONE);
                }
                
                layoutRecordsList.addView(itemView);
            }
        }
    }
    
    private int getStatusColor(String status) {
        switch (status) {
            case "PRESENT":
                return 0xFF27AE60; // Green
            case "ABSENT":
                return 0xFFE74C3C; // Red
            case "LATE":
                return 0xFFF39C12; // Orange
            default:
                return 0xFF7F8C8D; // Gray
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
