package com.nileuniversity.tams.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.Report;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Activity to display generated attendance reports
 */
public class ReportViewActivity extends AppCompatActivity {
    
    private TextView tvReportId;
    private TextView tvReportDate;
    private TextView tvReportContent;
    private MaterialButton btnBack;
    
    private Report report;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);
        
        // Get report data from intent
        Intent intent = getIntent();
        String reportId = intent.getStringExtra("REPORT_ID");
        String reportDate = intent.getStringExtra("REPORT_DATE");
        String reportContent = intent.getStringExtra("REPORT_CONTENT");
        String reportFormat = intent.getStringExtra("REPORT_FORMAT");
        
        // Initialize views
        initializeViews();
        displayReport(reportId, reportDate, reportContent, reportFormat);
    }
    
    private void initializeViews() {
        tvReportId = findViewById(R.id.tvReportId);
        tvReportDate = findViewById(R.id.tvReportDate);
        tvReportContent = findViewById(R.id.tvReportContent);
        btnBack = findViewById(R.id.btnBack);
        
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    private void displayReport(String reportId, String reportDate, String content, String format) {
        tvReportId.setText("Report ID: " + reportId + " (" + format + " format)");
        tvReportDate.setText("Generated: " + reportDate);
        tvReportContent.setText(content != null ? content : "No content available");
    }
}
