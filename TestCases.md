# Test Cases Document
**Time & Attendance Management System (TAMS)**  
**Nile University of Nigeria**  
**Course: SEN 207 - Object-Oriented Analysis and Design**

---

## Test Summary
- **Total Test Cases:** 12
- **Date:** December 31, 2025
- **Tester:** Development Team
- **Application Version:** 1.0

---

## Test Cases

| Test Case ID | Feature | Precondition | Test Steps | Expected Result | Actual Result | Status |
|--------------|---------|--------------|------------|-----------------|---------------|--------|
| TC-001 | Authentication - Valid Login | User exists in system (username: student1, password: pass123) | 1. Launch application<br>2. Enter username "student1"<br>3. Enter password "pass123"<br>4. Click Login button | User successfully logged in and redirected to Student Dashboard | User logged in successfully, Student Dashboard displayed | ✅ PASS |
| TC-002 | Authentication - Invalid Password | User exists in system | 1. Launch application<br>2. Enter username "student1"<br>3. Enter incorrect password "wrong123"<br>4. Click Login button | Login fails with error message "Invalid username or password" | Error message displayed, user remains on login screen | ✅ PASS |
| TC-003 | Authentication - Role-Based Access (Student) | Valid student credentials | 1. Log in as student (student1/pass123) | User redirected to Student Dashboard with Check In, Check Out, and View History buttons | Student Dashboard displayed with appropriate buttons | ✅ PASS |
| TC-004 | Authentication - Role-Based Access (Staff) | Valid staff credentials | 1. Log in as staff (staff1/pass123) | User redirected to Staff Dashboard with Monitor Attendance, View Records, and Manage Schedule buttons | Staff Dashboard displayed with appropriate buttons | ✅ PASS |
| TC-005 | Authentication - Role-Based Access (Admin) | Valid admin credentials | 1. Log in as admin (admin1/pass123) | User redirected to Admin Dashboard with Add User, Configure Policies, and Generate Reports buttons | Admin Dashboard displayed with appropriate buttons | ✅ PASS |
| TC-006 | User Management - Admin Creates Student | Admin is logged in | 1. Log in as admin<br>2. Click "Add User" button<br>3. System creates new student with generated credentials | New student account created successfully, credentials displayed in toast message | Student account created with auto-generated ID and credentials shown | ✅ PASS |
| TC-007 | User Management - New User Can Login | New user created in TC-006 | 1. Log out<br>2. Log in with newly created student credentials | Login successful, redirected to Student Dashboard | New student can log in and access Student Dashboard | ✅ PASS |
| TC-008 | Attendance - Student Check-In | Student is logged in | 1. Log in as student<br>2. Click "Check In" button<br>3. Enter location (e.g., "Main Campus") | Check-in recorded with timestamp, status set to PRESENT, success message displayed | Check-in successful, attendance record created with current timestamp | ✅ PASS |
| TC-009 | Attendance - Student Check-Out | Student is logged in and has checked in | 1. Log in as student<br>2. Click "Check Out" button<br>3. Enter location | Check-out recorded with timestamp, success message displayed | Check-out successful, timestamp recorded | ✅ PASS |
| TC-010 | Attendance - View History | Student has attendance records | 1. Log in as student<br>2. Click "View Attendance History" button | List of attendance records displayed showing date, time, and status | Attendance history screen shows all student's records with dates and statuses | ✅ PASS |
| TC-011 | Reports - Admin Generates Report | Admin is logged in, attendance data exists | 1. Log in as admin<br>2. Click "Generate Reports" button | Report generated with attendance statistics, report view displayed | Report successfully generated showing total records, present/absent/late counts, opens in ReportViewActivity | ✅ PASS |
| TC-012 | Staff Monitoring - View Currently Checked-In Students | Staff is logged in, students have checked in | 1. Log in as staff<br>2. Click "Monitor Attendance" button | List displayed showing currently checked-in students with student ID, check-in time, and status | Monitor screen shows list of checked-in students with real-time data | ✅ PASS |

---

## Test Coverage by Feature

### Authentication & Login (5 test cases)
- Valid login: TC-001
- Invalid login: TC-002
- Student role access: TC-003
- Staff role access: TC-004
- Admin role access: TC-005

### User Management (2 test cases)
- Admin creates user: TC-006
- New user login: TC-007

### Attendance Operations (3 test cases)
- Check-in: TC-008
- Check-out: TC-009
- View history: TC-010

### Report Generation (1 test case)
- Generate report: TC-011

### Staff Monitoring (1 test case)
- Monitor attendance: TC-012

---

## Test Environment
- **Platform:** Android
- **Minimum SDK:** API 24 (Android 7.0)
- **Target SDK:** API 34
- **Device/Emulator:** Pixel 5 API 30
- **Build Tool:** Gradle 8.1.0

---

## Mapping to System Requirements

| Test Case ID | Requirement ID | Requirement Description |
|--------------|----------------|-------------------------|
| TC-001, TC-002 | FR-001 | System shall authenticate users with username and password |
| TC-003, TC-004, TC-005 | FR-002 | System shall provide role-based access control |
| TC-006, TC-007 | FR-003 | Administrator shall be able to add new users |
| TC-008, TC-009 | FR-004 | Students shall be able to check in and check out |
| TC-010 | FR-005 | Students shall be able to view their attendance history |
| TC-011 | FR-006 | Administrators shall be able to generate attendance reports |
| TC-012 | FR-007 | Staff shall be able to monitor real-time attendance |

---

## Test Results Summary
- **Total Test Cases:** 12
- **Passed:** 12
- **Failed:** 0
- **Blocked:** 0
- **Not Executed:** 0
- **Pass Rate:** 100%

---

## Notes
- All tests were executed manually on the Android emulator
- Tests cover core functionality as specified in the SRS
- Service-layer automated tests complement these manual tests
- No critical or blocking issues identified
- Application meets all functional requirements

---

## Approvals

**Tested By:** _____________________  
**Date:** December 31, 2025

**Reviewed By:** _____________________  
**Date:** _____________________
