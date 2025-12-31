# Time and Attendance Management System (TAMS)
## Nile University of Nigeria - SEN 301 Course Project

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)

An Android mobile application for managing student attendance at Nile University of Nigeria. Built with object-oriented design principles and layered architecture.

## 📱 Project Overview

TAMS is an academic course project implementing a complete Time and Attendance Management System with role-based access control:

- **Students**: Check in/out, view attendance history
- **Staff**: Monitor real-time attendance, manage class schedules
- **Administrators**: User management, policy configuration, generate reports

## ✅ Implementation Status

### Completed Phases

- ✅ **Phase 1**: Model Classes (12 classes - User hierarchy, Attendance, Schedule, Notification, Report, Enums)
- ✅ **Phase 2**: Service Layer (5 Singleton services with in-memory storage)
- ✅ **Phase 3**: Login UI (Material Design with Nile University branding)
- ✅ **Phase 4**: Role-Based Dashboards (Student, Staff, Admin activities)
- ✅ **Phase 5**: Attendance Functionality (Check-in/out with timestamp recording)
- ✅ **Phase 6**: UI Polish (Removed debug logs, consistent footer branding)

### Critical Fixes Applied

- ✅ Attendance history viewing with full record list
- ✅ Report viewing with detailed content display
- ✅ User creation with authentication service persistence
- ✅ Footer consistency (SEN 301 on all screens)
- ✅ Data persistence verification

## 🏗️ Architecture

### Package Structure

```
com.nileuniversity.tams/
├── model/                    # POJOs (12 classes)
│   ├── User (abstract)
│   ├── Student, Staff, Administrator
│   ├── Attendance, AttendanceRecord
│   ├── Schedule, Notification, Report
│   ├── Authentication
│   └── Enums (UserRole, Status)
├── service/                  # Business logic (5 Singletons)
│   ├── AuthenticationService
│   ├── AttendanceService
│   ├── ScheduleService
│   ├── NotificationService
│   └── ReportService
├── ui/                       # Activities by role
│   ├── auth/                 # LoginActivity
│   ├── student/              # StudentDashboardActivity, AttendanceHistoryActivity
│   ├── staff/                # StaffDashboardActivity
│   └── admin/                # AdminDashboardActivity, ReportViewActivity
├── utils/                    # Helper classes
└── MainActivity.java
```

### Design Principles

- **Layered Architecture**: Clear separation between model, service, and UI layers
- **Singleton Pattern**: Services maintain centralized in-memory data
- **Role-Based Access Control**: Authentication enforces user permissions
- **Material Design**: Modern Android UI with consistent branding

## 🚀 Prerequisites

### Required Software

1. **Android Studio** (or VS Code with Android extensions)
2. **Java Development Kit (JDK) 17** or higher
3. **Android SDK** (API 24+, compileSdk 34, targetSdk 34)
4. **Gradle 8.0** (wrapper included)

### Android Emulator

- **Device**: Medium Phone API 36.1 (or any device with API 24+)
- **RAM**: 2GB minimum
- **Resolution**: 1080x1920 recommended

## 📦 Installation & Build

### 1. Clone Repository

```bash
git clone https://github.com/JesseDev454/Attendance-Management-System.git
cd Attendance-Management-System/TAMS
```

### 2. Configure Android SDK

Create or update `local.properties`:

```properties
sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

### 3. Build Application

```bash
# Windows
.\gradlew.bat assembleDebug

# Linux/Mac
./gradlew assembleDebug
```

Build output: `app/build/outputs/apk/debug/app-debug.apk`

### 4. Install on Emulator

```bash
# Start emulator
emulator -avd Your_AVD_Name

# Install app
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Launch app
adb shell am start -n com.nileuniversity.tams/.ui.auth.LoginActivity
```

## 🎮 Test Credentials

### Pre-loaded Accounts

| Role          | Username   | Password  |
|---------------|------------|-----------|
| **Student**   | student1   | pass123   |
| **Staff**     | staff1     | pass123   |
| **Admin**     | admin1     | pass123   |

## 🧪 Testing Instructions

### Test 1: Student Attendance Workflow

1. Login as **student1** / **pass123**
2. Tap **Check In** button → Toast confirms check-in
3. Tap **Check Out** button → Toast confirms check-out
4. Tap **View Attendance History** → Opens list view with attendance records
5. Verify records show date, status (PRESENT/ABSENT), and remarks
6. Tap **Back** to return to dashboard

### Test 2: Admin Report Generation

1. Login as **admin1** / **pass123**
2. Tap **Generate Reports** button
3. Should open **Report View** screen showing:
   - Report ID
   - Generated date
   - Full report content
   - Report format (PDF)
4. Tap **Back** to return to dashboard

### Test 3: User Creation & Authentication

1. Login as **admin1** / **pass123**
2. Tap **Add User** button
3. Toast displays new username and password (e.g., "student2" / "password123")
4. **Write down credentials**
5. Tap **Logout**
6. Login with new credentials → Should succeed
7. Verify new user can access student dashboard

### Test 4: Staff Dashboard

1. Login as **staff1** / **pass123**
2. Verify buttons:
   - Monitor Attendance (toast notification)
   - View Class Records (toast notification)
   - Manage Schedule (toast notification)
3. Tap **Logout**

## 📂 Key Files

### Activities

- `LoginActivity.java` - Authentication with role validation
- `StudentDashboardActivity.java` - Check-in/out, view history
- `AttendanceHistoryActivity.java` - ListView of attendance records
- `StaffDashboardActivity.java` - Monitoring and schedule management
- `AdminDashboardActivity.java` - User management, reports, policies
- `ReportViewActivity.java` - Detailed report content display

### Layouts

- `activity_login.xml` - Login screen with logo
- `activity_student_dashboard.xml` - Student interface
- `activity_attendance_history.xml` - Attendance record list
- `activity_admin_dashboard.xml` - Admin interface
- `activity_report_view.xml` - Report viewing screen
- `item_attendance_record.xml` - ListView item template

### Configuration

- `AndroidManifest.xml` - App configuration, activities
- `themes.xml` - Material Components theme (AppTheme)
- `build.gradle` - Dependencies and build configuration
- `gradle.properties` - AndroidX enabled

## 🎨 Design Features

### Material Design Components

- **Theme**: Theme.MaterialComponents.Light.NoActionBar
- **Colors**: 
  - Primary: #003366 (Nile University blue)
  - Secondary: #1976D2
- **Components**: MaterialButton, CardView, ListView

### Branding

- **Logo**: Nile University logo (100x100dp) on all screens
- **Footer**: "Nile University of Nigeria – SEN 301 Course Project"
- **Typography**: Clean, readable text hierarchy

## 💾 Data Storage

### In-Memory Collections

- **Users**: ArrayList in AuthenticationService
- **Attendance Records**: ArrayList in AttendanceService
- **Schedules**: ArrayList in ScheduleService
- **Notifications**: ArrayList in NotificationService
- **Reports**: ArrayList in ReportService

**Note**: Data resets on app restart (no database persistence).

## 🐛 Troubleshooting

### Build Issues

**Error**: `Configuration contains AndroidX dependencies`
- **Solution**: Ensure `gradle.properties` has `android.useAndroidX=true`

**Error**: `Theme.MaterialComponents required`
- **Solution**: Verify `themes.xml` exists with AppTheme definition

**Error**: `Gradle wrapper not found`
- **Solution**: Run `gradle wrapper` to generate wrapper files

### Runtime Issues

**App crashes on launch**
- Check `AndroidManifest.xml` has correct theme reference
- Verify all activities are declared
- Check Logcat for stack traces

**Emulator frozen/unresponsive**
```bash
adb kill-server
adb start-server
emulator -avd Your_AVD_Name -gpu host -no-snapshot-load
```

**Login fails for new users**
- Ensure `AdminDashboardActivity.handleAddUser()` calls `authService.addUser()`

## 📝 Development Notes

### Design Constraints

- **No databases** (SQLite, Room, Firebase) - in-memory only
- **No Jetpack Compose** - XML layouts only
- **No Retrofit/OkHttp** - no network calls
- **No external libraries** beyond Android/Material essentials

### Academic Requirements

- Full traceability: SRS → CRC cards → UML diagram → Java code
- Object-oriented design with proper encapsulation
- Layered architecture with clear separation of concerns
- Clean, readable code with meaningful naming

## 🔧 Technology Stack

| Component         | Technology                     |
|-------------------|--------------------------------|
| Language          | Java                           |
| Platform          | Android (API 24+)              |
| UI Framework      | Material Design Components     |
| Build Tool        | Gradle 8.0                     |
| Architecture      | Layered (Model-Service-UI)     |
| Design Patterns   | Singleton, Factory             |
| Version Control   | Git (GitHub)                   |

## 📄 License

This is an academic course project for Nile University of Nigeria.

## 👨‍💻 Author

**Course**: SEN 301 - Object-Oriented Analysis & Design  
**Institution**: Nile University of Nigeria  
**Repository**: [github.com/JesseDev454/Attendance-Management-System](https://github.com/JesseDev454/Attendance-Management-System)

---

**Last Updated**: December 31, 2025  
**Status**: ✅ All phases complete, tested on Android emulator
