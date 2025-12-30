package com.nileuniversity.tams.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.nileuniversity.tams.R;
import com.nileuniversity.tams.model.User;
import com.nileuniversity.tams.model.UserRole;
import com.nileuniversity.tams.service.AuthenticationService;
import com.nileuniversity.tams.ui.student.StudentDashboardActivity;
import com.nileuniversity.tams.ui.staff.StaffDashboardActivity;
import com.nileuniversity.tams.ui.admin.AdminDashboardActivity;

/**
 * LoginActivity
 * Handles user authentication and redirects to appropriate dashboard
 */
public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etUsername;
    private TextInputEditText etPassword;
    private MaterialButton btnLogin;
    private TextView tvError;
    private AuthenticationService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize authentication service
        authService = AuthenticationService.getInstance();

        // Initialize views
        initializeViews();

        // Set login button click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    /**
     * Initialize UI components
     */
    private void initializeViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvError = findViewById(R.id.tvError);
    }

    /**
     * Attempt to login with provided credentials
     */
    private void attemptLogin() {
        // Hide previous error message
        tvError.setVisibility(View.GONE);

        // Get username and password
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (username.isEmpty()) {
            showError("Please enter username");
            return;
        }

        if (password.isEmpty()) {
            showError("Please enter password");
            return;
        }

        // Attempt login
        User user = authService.login(username, password);

        if (user != null) {
            // Login successful - redirect based on role
            redirectToDashboard(user);
        } else {
            // Login failed
            showError("Invalid username or password");
        }
    }

    /**
     * Show error message
     */
    private void showError(String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
    }

    /**
     * Redirect user to appropriate dashboard based on role
     */
    private void redirectToDashboard(User user) {
        Intent intent;

        if (user.getRole() == UserRole.STUDENT) {
            intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
        } else if (user.getRole() == UserRole.STAFF) {
            intent = new Intent(LoginActivity.this, StaffDashboardActivity.class);
        } else if (user.getRole() == UserRole.ADMINISTRATOR) {
            intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
        } else {
            showError("Unknown user role");
            return;
        }

        // Pass user information
        intent.putExtra("USER_ID", user.getUserId());
        intent.putExtra("USERNAME", user.getUsername());
        intent.putExtra("USER_ROLE", user.getRole().name());

        // Start dashboard and finish login
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Disable back button on login screen
        // User must login to proceed
    }
}
