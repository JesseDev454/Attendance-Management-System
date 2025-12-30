# Time and Attendance Management System (TAMS)
## Nile University of Nigeria - SEN 207 Course Project
### Android Mobile Application

## ✅ Phase 1 Complete: Model Classes

All required model classes have been implemented:

### Enums
- ✅ UserRole (STUDENT, STAFF, ADMINISTRATOR)
- ✅ Status (PRESENT, ABSENT, LATE, EXCUSED)

### User Hierarchy
- ✅ User (abstract base class)
- ✅ Student extends User
- ✅ Staff extends User
- ✅ Administrator extends User

### Core Classes
- ✅ Authentication
- ✅ Attendance
- ✅ AttendanceRecord
- ✅ Schedule
- ✅ Notification
- ✅ Report

## Project Structure

```
TAMS/
├── app/
│   ├── build.gradle
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/nileuniversity/tams/
│           │   ├── model/
│           │   │   ├── User.java
│           │   │   ├── Student.java
│           │   │   ├── Staff.java
│           │   │   ├── Administrator.java
│           │   │   ├── Authentication.java
│           │   │   ├── Attendance.java
│           │   │   ├── AttendanceRecord.java
│           │   │   ├── Schedule.java
│           │   │   ├── Notification.java
│           │   │   ├── Report.java
│           │   │   ├── UserRole.java
│           │   │   └── Status.java
│           │   ├── service/ (ready for Phase 2)
│           │   ├── ui/
│           │   │   ├── auth/ (ready for Phase 3)
│           │   │   ├── student/
│           │   │   ├── staff/
│           │   │   └── admin/
│           │   ├── utils/
│           │   └── MainActivity.java
│           └── res/
│               ├── layout/
│               ├── values/
│               │   └── strings.xml
│               └── drawable/
├── build.gradle
└── settings.gradle
```

## How to Run (VS Code)

### Prerequisites
1. Install **Android SDK** via Android Studio or command line tools
2. Install VS Code extensions:
   - Extension Pack for Java
   - Gradle for Java

### Build and Run
1. Open terminal in TAMS folder
2. Build project:
   ```
   ./gradlew build
   ```
3. Run on emulator or device:
   ```
   ./gradlew installDebug
   ```

## Next Phase

Ready to proceed to **Phase 2: Service Layer**
- AuthenticationService
- AttendanceService
- ScheduleService
- NotificationService
- ReportService

## Notes
- All model classes are plain POJOs (no Android dependencies)
- In-memory data storage only
- Clean OOP design following SRS and UML diagrams
- Academic project focused on correct architecture
