package com.nileuniversity.tams;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity - Entry point of TAMS Android Application
 * Nile University of Nigeria - SEN 301 Course Project
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create a simple layout programmatically for now
        TextView textView = new TextView(this);
        textView.setText("Time & Attendance Management System\nNile University of Nigeria\n\nPhase 1: Model Classes Complete ✓");
        textView.setTextSize(18);
        textView.setPadding(50, 50, 50, 50);
        textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        
        setContentView(textView);
    }
}
