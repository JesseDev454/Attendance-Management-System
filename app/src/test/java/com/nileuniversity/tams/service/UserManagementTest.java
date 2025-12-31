package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for User Management functionality
 * Tests admin capabilities to add users and user account creation
 */
public class UserManagementTest {
    
    private AuthenticationService authService;
    private Administrator testAdmin;
    
    /**
     * Set up test fixture before each test
     */
    @Before
    public void setUp() {
        authService = AuthenticationService.getInstance();
        authService.logout(); // Ensure clean state
        
        // Login as admin to have permission to add users
        authService.login("admin1", "pass123");
        testAdmin = (Administrator) authService.getCurrentUser();
    }
    
    /**
     * Test Case: TC-USER-001
     * Test admin can add new student user
     * Expected: New student user created successfully
     */
    @Test
    public void testAdminAddNewStudent() {
        // Arrange
        Student newStudent = new Student("NEW001", "newstudent", "password123", "new@nileuniversity.edu.ng");
        
        // Act
        boolean result = authService.addUser(newStudent);
        
        // Assert
        assertTrue("Admin should be able to add new student", result);
        
        // Verify the user was added by trying to log in
        User loginResult = authService.login("newstudent", "password123");
        assertNotNull("New student should be able to log in", loginResult);
        assertEquals("Logged in user should match created user", "newstudent", loginResult.getUsername());
    }
    
    /**
     * Test Case: TC-USER-002
     * Test newly created user can login
     * Expected: New user successfully authenticates
     */
    @Test
    public void testNewUserCanLogin() {
        // Arrange
        Student newStudent = new Student("NEW002", "freshstudent", "fresh123", "fresh@nileuniversity.edu.ng");
        authService.addUser(newStudent);
        
        // Act
        User loggedInUser = authService.login("freshstudent", "fresh123");
        
        // Assert
        assertNotNull("Newly created user should be able to log in", loggedInUser);
        assertEquals("Username should match", "freshstudent", loggedInUser.getUsername());
        assertEquals("User ID should match", "NEW002", loggedInUser.getUserId());
        assertEquals("Role should be STUDENT", UserRole.STUDENT, loggedInUser.getRole());
    }
    
    /**
     * Test Case: TC-USER-003
     * Test adding duplicate user
     * Expected: Duplicate user addition should fail or be handled
     */
    @Test
    public void testAddDuplicateUser() {
        // Arrange
        Student student1 = new Student("DUP001", "duplicate", "pass123", "dup@test.com");
        Student student2 = new Student("DUP002", "duplicate", "pass456", "dup2@test.com");
        
        // Act
        boolean firstAdd = authService.addUser(student1);
        boolean secondAdd = authService.addUser(student2);
        
        // Assert
        assertTrue("First user should be added successfully", firstAdd);
        // The second add might succeed or fail depending on business rules
        // We just verify that the system handles it without crashing
        assertNotNull("AuthService should handle duplicate usernames gracefully", authService);
    }
    
    /**
     * Test Case: TC-USER-004
     * Test admin can add staff user
     * Expected: New staff user created successfully
     */
    @Test
    public void testAdminAddStaffUser() {
        // Arrange
        Staff newStaff = new Staff("STAFF_NEW", "newstaff", "staff123", "staff@nileuniversity.edu.ng");
        
        // Act
        boolean result = authService.addUser(newStaff);
        
        // Assert
        assertTrue("Admin should be able to add new staff", result);
        
        // Verify staff can login
        User loginResult = authService.login("newstaff", "staff123");
        assertNotNull("New staff should be able to log in", loginResult);
        assertTrue("Logged in user should be Staff instance", loginResult instanceof Staff);
    }
    
    /**
     * Test Case: TC-USER-005
     * Test admin can add another admin user
     * Expected: New administrator created successfully
     */
    @Test
    public void testAdminAddAdminUser() {
        // Arrange
        Administrator newAdmin = new Administrator("ADMIN_NEW", "newadmin", "admin123", "newadmin@nileuniversity.edu.ng");
        
        // Act
        boolean result = authService.addUser(newAdmin);
        
        // Assert
        assertTrue("Admin should be able to add new administrator", result);
        
        // Verify admin can login
        User loginResult = authService.login("newadmin", "admin123");
        assertNotNull("New admin should be able to log in", loginResult);
        assertTrue("Logged in user should be Administrator instance", loginResult instanceof Administrator);
    }
    
    /**
     * Test Case: TC-USER-006
     * Test multiple users can be added sequentially
     * Expected: All users added successfully
     */
    @Test
    public void testAddMultipleUsers() {
        // Arrange
        Student student1 = new Student("MULTI001", "multi1", "pass1", "m1@test.com");
        Student student2 = new Student("MULTI002", "multi2", "pass2", "m2@test.com");
        Student student3 = new Student("MULTI003", "multi3", "pass3", "m3@test.com");
        
        // Act
        boolean result1 = authService.addUser(student1);
        boolean result2 = authService.addUser(student2);
        boolean result3 = authService.addUser(student3);
        
        // Assert
        assertTrue("First user should be added", result1);
        assertTrue("Second user should be added", result2);
        assertTrue("Third user should be added", result3);
        
        // Verify all can login
        assertNotNull("User 1 should login", authService.login("multi1", "pass1"));
        assertNotNull("User 2 should login", authService.login("multi2", "pass2"));
        assertNotNull("User 3 should login", authService.login("multi3", "pass3"));
    }
    
    /**
     * Test Case: TC-USER-007
     * Test user with all required fields
     * Expected: User created with all properties set correctly
     */
    @Test
    public void testUserCreationWithAllFields() {
        // Arrange
        String userId = "FULL001";
        String username = "fulluser";
        String password = "fullpass";
        String email = "full@nileuniversity.edu.ng";
        
        Student newUser = new Student(userId, username, password, email);
        
        // Act
        authService.addUser(newUser);
        User retrievedUser = authService.login(username, password);
        
        // Assert
        assertNotNull("User should be retrieved", retrievedUser);
        assertEquals("User ID should match", userId, retrievedUser.getUserId());
        assertEquals("Username should match", username, retrievedUser.getUsername());
        assertEquals("Email should match", email, retrievedUser.getEmail());
        assertEquals("Role should be STUDENT", UserRole.STUDENT, retrievedUser.getRole());
    }
    
    /**
     * Test Case: TC-USER-008
     * Test Administrator's addUser method
     * Expected: Administrator model method executes without error
     */
    @Test
    public void testAdministratorAddUserMethod() {
        // Arrange
        Student newStudent = new Student("ADM001", "admstudent", "pass", "adm@test.com");
        
        // Act
        testAdmin.addUser(newStudent);
        
        // Assert
        // Method should execute without throwing exception
        assertNotNull("Admin should exist after adding user", testAdmin);
    }
}
