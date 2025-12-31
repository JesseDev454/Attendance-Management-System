package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for AuthenticationService
 * Tests authentication, login, logout, and user management functionality
 */
public class AuthenticationServiceTest {
    
    private AuthenticationService authService;
    
    /**
     * Set up test fixture before each test
     * Creates a fresh instance of AuthenticationService
     */
    @Before
    public void setUp() {
        // Reset singleton instance for each test
        authService = AuthenticationService.getInstance();
        authService.logout(); // Ensure no user is logged in
    }
    
    /**
     * Test Case: TC-AUTH-001
     * Test valid login with correct credentials
     * Expected: User successfully logged in and returned
     */
    @Test
    public void testLoginWithValidCredentials() {
        // Arrange
        String username = "student1";
        String password = "pass123";
        
        // Act
        User user = authService.login(username, password);
        
        // Assert
        assertNotNull("User should not be null after valid login", user);
        assertEquals("Username should match", username, user.getUsername());
        assertEquals("User role should be STUDENT", UserRole.STUDENT, user.getRole());
        assertTrue("User should be logged in", authService.isLoggedIn());
    }
    
    /**
     * Test Case: TC-AUTH-002
     * Test login with invalid password
     * Expected: Login fails and returns null
     */
    @Test
    public void testLoginWithInvalidPassword() {
        // Arrange
        String username = "student1";
        String wrongPassword = "wrongpass";
        
        // Act
        User user = authService.login(username, wrongPassword);
        
        // Assert
        assertNull("User should be null when password is incorrect", user);
        assertFalse("User should not be logged in", authService.isLoggedIn());
    }
    
    /**
     * Test Case: TC-AUTH-003
     * Test login with invalid username
     * Expected: Login fails and returns null
     */
    @Test
    public void testLoginWithInvalidUsername() {
        // Arrange
        String invalidUsername = "nonexistent";
        String password = "pass123";
        
        // Act
        User user = authService.login(invalidUsername, password);
        
        // Assert
        assertNull("User should be null when username doesn't exist", user);
        assertFalse("User should not be logged in", authService.isLoggedIn());
    }
    
    /**
     * Test Case: TC-AUTH-004
     * Test role-based authentication for Student
     * Expected: Student user has correct role
     */
    @Test
    public void testStudentRoleBasedAccess() {
        // Arrange & Act
        User user = authService.login("student1", "pass123");
        
        // Assert
        assertNotNull("Student login should succeed", user);
        assertTrue("User should be instance of Student", user instanceof Student);
        assertEquals("User role should be STUDENT", UserRole.STUDENT, user.getRole());
    }
    
    /**
     * Test Case: TC-AUTH-005
     * Test role-based authentication for Staff
     * Expected: Staff user has correct role
     */
    @Test
    public void testStaffRoleBasedAccess() {
        // Arrange & Act
        User user = authService.login("staff1", "pass123");
        
        // Assert
        assertNotNull("Staff login should succeed", user);
        assertTrue("User should be instance of Staff", user instanceof Staff);
        assertEquals("User role should be STAFF", UserRole.STAFF, user.getRole());
    }
    
    /**
     * Test Case: TC-AUTH-006
     * Test role-based authentication for Administrator
     * Expected: Admin user has correct role
     */
    @Test
    public void testAdminRoleBasedAccess() {
        // Arrange & Act
        User user = authService.login("admin1", "pass123");
        
        // Assert
        assertNotNull("Admin login should succeed", user);
        assertTrue("User should be instance of Administrator", user instanceof Administrator);
        assertEquals("User role should be ADMINISTRATOR", UserRole.ADMINISTRATOR, user.getRole());
    }
    
    /**
     * Test Case: TC-AUTH-007
     * Test logout functionality
     * Expected: User is logged out and current user is null
     */
    @Test
    public void testLogout() {
        // Arrange
        authService.login("student1", "pass123");
        assertTrue("User should be logged in initially", authService.isLoggedIn());
        
        // Act
        authService.logout();
        
        // Assert
        assertFalse("User should not be logged in after logout", authService.isLoggedIn());
        assertNull("Current user should be null after logout", authService.getCurrentUser());
    }
    
    /**
     * Test Case: TC-AUTH-008
     * Test getCurrentUser returns correct user
     * Expected: Current user matches logged in user
     */
    @Test
    public void testGetCurrentUser() {
        // Arrange
        String username = "student1";
        authService.login(username, "pass123");
        
        // Act
        User currentUser = authService.getCurrentUser();
        
        // Assert
        assertNotNull("Current user should not be null", currentUser);
        assertEquals("Current user username should match", username, currentUser.getUsername());
    }
    
    /**
     * Test Case: TC-AUTH-009
     * Test login with empty credentials
     * Expected: Login fails
     */
    @Test
    public void testLoginWithEmptyCredentials() {
        // Arrange & Act
        User user = authService.login("", "");
        
        // Assert
        assertNull("Login should fail with empty credentials", user);
        assertFalse("User should not be logged in", authService.isLoggedIn());
    }
    
    /**
     * Test Case: TC-AUTH-010
     * Test multiple consecutive logins
     * Expected: Only the last login is active
     */
    @Test
    public void testMultipleConsecutiveLogins() {
        // Arrange & Act
        User student = authService.login("student1", "pass123");
        assertNotNull("First login should succeed", student);
        
        User staff = authService.login("staff1", "pass123");
        assertNotNull("Second login should succeed", staff);
        
        // Assert
        User currentUser = authService.getCurrentUser();
        assertEquals("Current user should be the last logged in user", 
                    "staff1", currentUser.getUsername());
        assertTrue("Current user should be Staff", currentUser instanceof Staff);
    }
}
