# Phase 4: Automated ABSENT Logic - Implementation Summary

## Overview
Phase 4 implements automatic marking of students as ABSENT when they fail to check in for a scheduled lecture by the end of the class time.

## Key Components

### 1. AbsentMarkingService.java
**Location:** `com.nileuniversity.tams.service.AbsentMarkingService`

**Purpose:** Central service for automated ABSENT marking logic

**Key Methods:**
- `checkAndMarkAbsentStudents()` - Checks all courses and marks absent students for ended lectures
- `markAbsentForSession(courseCode, sessionId, schedule)` - Marks specific session as complete
- `isLectureEnded(schedule, currentTime)` - Determines if lecture end time has passed
- `findStudentsWithoutAttendance(courseCode, sessionId, enrolledStudents)` - Identifies absent students
- `createAbsentRecord(studentId, courseCode, schedule, sessionId)` - Creates ABSENT record with method=SYSTEM

**Logic Flow:**
1. For each course, check if lecture end time has passed (based on Schedule)
2. Generate unique sessionId (format: YYYYMMDD_COURSECD_HHMM)
3. Get list of enrolled students from EnrollmentService
4. Check which students don't have attendance record for this session
5. Create AttendanceRecord with status=ABSENT, method=SYSTEM for each absent student
6. Track processed sessions to avoid duplicate marking

### 2. AttendanceMethod Enum Enhancement
**Location:** `com.nileuniversity.tams.model.AttendanceMethod`

**Added:**
```java
SYSTEM  // Phase 4: Automated system-generated ABSENT records
```

**Purpose:** Distinguishes automatic ABSENT marking from user-initiated actions

**Display in UI:** Shows as "SYSTEM" in both student and staff attendance views

### 3. QRCodeService Integration
**Location:** `com.nileuniversity.tams.service.QRCodeService`

**Enhancements:**
- Added `absentMarkingService` field
- `expireSession()` now triggers `markAbsentForCourseNow()` when QR session ends
- Added `checkAndMarkAllAbsentStudents()` public method for periodic checks

**When ABSENT Marking Occurs:**
1. **QR Session Expires:** When staff ends a QR session, system immediately marks absent students
2. **Before Viewing Records:** When staff views class records, system checks all courses first
3. **Before Viewing Attendance:** When student views attendance, system updates records

### 4. Dashboard Integration

**StaffDashboardActivity:**
- `handleViewRecords()` calls `qrCodeService.checkAndMarkAllAbsentStudents()` before navigation
- Ensures staff always sees up-to-date ABSENT records

**StudentDashboardActivity:**
- `handleViewHistory()` calls `qrCodeService.checkAndMarkAllAbsentStudents()` before navigation
- Students see their ABSENT records as soon as lecture ends

## Business Rules

### When ABSENT is Marked:
✓ Lecture end time has passed (based on Course Schedule)
✓ Student is enrolled in the course (verified via EnrollmentService)
✓ Student has NO attendance record for that specific session
✓ Today matches the scheduled day (e.g., Monday, Wednesday)

### When ABSENT is NOT Marked:
✗ Student already has attendance record (PRESENT, LATE, or ABSENT)
✗ Lecture has not ended yet
✗ Not the scheduled day for the course
✗ Session already processed (tracked in processedSessions set)

## Data Integrity

### Session ID Format:
```
YYYYMMDD_COURSECD_HHMM
Example: 20260101_CSC301_1000
```

**Components:**
- Date: YYYYMMDD (20260101 = Jan 1, 2026)
- Course Code: CSC301
- Start Time: HHMM (1000 = 10:00 AM)

**Purpose:** Uniquely identifies each lecture session to prevent duplicate ABSENT marking

### Deduplication:
- `processedSessions` Set tracks which sessions have been marked
- Prevents creating multiple ABSENT records for same session
- Can be reset via `resetProcessedSessions()` for testing or new day

## UI Display

### Student View (ViewAttendanceByCourseActivity):
**ABSENT records show:**
- Status: "ABSENT" (red indicator)
- Method: "SYSTEM" (indicates automatic marking)
- Date & Time: When lecture occurred
- Course: Which course student missed

### Staff View (ViewClassRecordsActivityNew):
**ABSENT records show:**
- Student name
- Status: "ABSENT" (red indicator)
- Method: "SYSTEM"
- Statistics: Counted in total absent count
- Recent records: Last 3 attendance records per student

## Testing the Feature

### Manual Test Steps:

1. **Setup:**
   - Login as student (e.g., 180404001)
   - Ensure student is enrolled in courses (auto-enrolled in demo mode)

2. **Create Scenario:**
   - Check current time (e.g., 3:00 PM)
   - Find a course with lecture that has ended (e.g., CSC301: Mon/Wed 10:00-11:50)
   - If today is Monday and time is after 11:50 AM, lecture has ended

3. **Do NOT Check In:**
   - Avoid scanning QR code for that course
   - Skip the check-in process

4. **Trigger ABSENT Marking:**
   - As **Staff**, click "View Class Records"
   - Select the course (e.g., CSC301)
   - System automatically marks absent students

5. **Verify as Student:**
   - Login as student
   - Click "View Attendance History"
   - Should see ABSENT record with method="SYSTEM" for CSC301

6. **Verify as Staff:**
   - View Class Records > Select CSC301
   - Should see student with ABSENT status and method="SYSTEM"
   - Absent count should increase by 1

### Expected Results:
✓ ABSENT record created automatically
✓ Status = ABSENT
✓ Method = SYSTEM (not MANUAL, QR_CODE, or BIOMETRIC)
✓ Visible in both student and staff views
✓ Counted in attendance statistics
✓ Cannot be created twice for same session

## Integration Points

### Services Used:
1. **AttendanceService** - Creates attendance records
2. **EnrollmentService** - Gets enrolled students per course
3. **QRCodeService** - Triggers absent marking on session expire
4. **AbsentMarkingService** - Central logic handler

### Models Used:
1. **Course** - Gets course details and schedule
2. **Schedule** - Determines lecture timing
3. **AttendanceRecord** - Stores ABSENT record
4. **AttendanceMethod** - SYSTEM enum value
5. **Status** - ABSENT enum value

## Configuration

### No Configuration Required:
- Fully automated based on Schedule data
- Uses course start/end times from Schedule model
- Checks day of week automatically (Monday, Tuesday, etc.)

### Adjustable Behavior:
- `resetProcessedSessions()` - Clear tracking for testing
- `getProcessedSessionCount()` - Debug processed sessions count

## Troubleshooting

### ABSENT Not Appearing:

**Check 1:** Is lecture time actually ended?
- Current time > Schedule.endTime
- Current day matches Schedule.day

**Check 2:** Is student enrolled in course?
- EnrollmentService.getEnrolledStudents(courseCode) contains studentId

**Check 3:** Does student already have record?
- Check attendanceService.getRecordsByStudentAndCourse(studentId, courseCode)
- If record exists for sessionId, no ABSENT created

**Check 4:** Was absent marking triggered?
- View records or view attendance to trigger check
- Or wait for QR session to expire

### Multiple ABSENT Records:

**Should NOT happen:** processedSessions tracks completed sessions
- If this occurs, check sessionId generation logic
- Verify processedSessions.add(sessionId) is called

## Future Enhancements (Not in Phase 4)

**Potential Improvements:**
- Background service running every 10 minutes
- Push notifications to students marked ABSENT
- Grace period configuration (already exists in Schedule)
- Bulk absent marking for multiple days
- Admin override to remove system-generated ABSENT

## Phase 4 Completion Checklist

✅ AbsentMarkingService created with full logic
✅ AttendanceMethod.SYSTEM added to enum
✅ QRCodeService integrated with absent marking
✅ StaffDashboardActivity triggers check before viewing records
✅ StudentDashboardActivity triggers check before viewing attendance
✅ UI displays SYSTEM method correctly
✅ Session deduplication implemented
✅ Enrollment validation included
✅ Schedule timing validation included
✅ No duplicate ABSENT records created

---

**Phase 4 Status:** ✅ COMPLETE

**Next Phase:** Phase 5 - Staff Functional Buttons (Make Them Real)
