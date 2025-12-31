package com.nileuniversity.tams.ui.staff;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.Schedule;
import com.nileuniversity.tams.service.ScheduleService;
import java.util.List;

/**
 * ManageScheduleActivity
 * Allows staff to view and add class schedules
 */
public class ManageScheduleActivity extends AppCompatActivity {
    
    private LinearLayout layoutScheduleList;
    private TextView tvNoData;
    private EditText etClassId;
    private EditText etCourseName;
    private EditText etStartTime;
    private EditText etEndTime;
    private EditText etRoom;
    private MaterialButton btnAddSchedule;
    private MaterialButton btnBack;
    private ScheduleService scheduleService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_schedule);
        
        // Initialize service
        scheduleService = ScheduleService.getInstance();
        
        // Initialize views
        initializeViews();
        loadSchedules();
        setupClickListeners();
    }
    
    private void initializeViews() {
        layoutScheduleList = findViewById(R.id.layoutScheduleList);
        tvNoData = findViewById(R.id.tvNoData);
        etClassId = findViewById(R.id.etClassId);
        etCourseName = findViewById(R.id.etCourseName);
        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
        etRoom = findViewById(R.id.etRoom);
        btnAddSchedule = findViewById(R.id.btnAddSchedule);
        btnBack = findViewById(R.id.btnBack);
    }
    
    private void loadSchedules() {
        // Clear existing views
        layoutScheduleList.removeAllViews();
        
        // Get all schedules
        List<Schedule> schedules = scheduleService.getAllSchedules();
        
        if (schedules.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            layoutScheduleList.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            layoutScheduleList.setVisibility(View.VISIBLE);
            
            // Display each schedule
            for (Schedule schedule : schedules) {
                View itemView = getLayoutInflater().inflate(R.layout.item_schedule, layoutScheduleList, false);
                
                TextView tvClassId = itemView.findViewById(R.id.tvClassId);
                TextView tvCourseName = itemView.findViewById(R.id.tvCourseName);
                TextView tvTime = itemView.findViewById(R.id.tvTime);
                TextView tvRoom = itemView.findViewById(R.id.tvRoom);
                
                tvClassId.setText("Class ID: " + schedule.getClassId());
                tvCourseName.setText(schedule.getCourseName());
                tvTime.setText("Time: " + schedule.getStartTime() + " - " + schedule.getEndTime());
                tvRoom.setText("Room: " + schedule.getRoom());
                
                layoutScheduleList.addView(itemView);
            }
        }
    }
    
    private void setupClickListeners() {
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddSchedule();
            }
        });
        
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    private void handleAddSchedule() {
        // Get input values
        String classId = etClassId.getText().toString().trim();
        String courseName = etCourseName.getText().toString().trim();
        String startTime = etStartTime.getText().toString().trim();
        String endTime = etEndTime.getText().toString().trim();
        String room = etRoom.getText().toString().trim();
        
        // Validate inputs
        if (classId.isEmpty() || courseName.isEmpty() || startTime.isEmpty() || 
            endTime.isEmpty() || room.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Create new schedule
        Schedule newSchedule = new Schedule(classId, courseName, startTime, endTime, room);
        
        // Add to service
        boolean success = scheduleService.addSchedule(newSchedule);
        
        if (success) {
            Toast.makeText(this, "Schedule added successfully", Toast.LENGTH_SHORT).show();
            
            // Clear input fields
            etClassId.setText("");
            etCourseName.setText("");
            etStartTime.setText("");
            etEndTime.setText("");
            etRoom.setText("");
            
            // Reload schedules
            loadSchedules();
        } else {
            Toast.makeText(this, "Failed to add schedule - Conflict detected", Toast.LENGTH_SHORT).show();
        }
    }
}
