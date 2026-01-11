COURSE MANAGEMENT & COURSE-BASED ATTENDANCE RECORDS
🎯 Goal of This part

Expand the system so that:

Students can offer multiple courses

Attendance is stored, viewed, and grouped per course

Staff can monitor real attendance data, not fake success alerts

ABSENT / LATE / PRESENT is visible and justified

Everything still aligns with your UML + CRC

QR Code logic already exists — DO NOT rebuild it.

Build this part of the Android Attendance Management System in Java.
QR-based attendance is already implemented.
This part focuses on Course management, enrollment, and attendance records per course.
Do not redesign QR logic — only integrate it with course-based data.

Phase 1️⃣ Course Class (FULL BUT SIMPLE)

Enhance the existing Course class.

Each course must have:

courseCode (String)

courseTitle (String)

creditUnit (int)

schedule (Schedule)

assignedLecturer (Staff)

Courses are predefined (do NOT allow students to create courses).

Phase 2️⃣ Student ↔ Course Enrollment

Implement Many-to-Many relationship.

Student:

Can be enrolled in multiple courses

Sees only courses they are enrolled in

Selects course when:

Checking attendance

Viewing attendance history

Course:

Maintains a list of enrolled students

Enrollment can be:

Auto-assigned by Admin (simplified)

Or preloaded for demo purposes

Phase3️⃣ Attendance Records Per Course (NO FAKE DATA)

Fix this issue:

“Attendance successful but nothing is visible”

Implement:

AttendanceRecord must store:

studentId

courseCode

date

attendanceStatus (PRESENT / LATE / ABSENT)

attendanceMethod (QR)

timestamp

Student View:

“My Attendance”

Grouped by Course

Shows:

Date

Status

Check-in time

Staff View:

“View Class Records”

Select course

See:

List of students

Their attendance status for each session

No toast-only messages. Show real lists.

Phase4️⃣ ABSENT Logic (AUTOMATED — NO BUTTON)

Implement this rule system-wide:

If:

Lecture end time is reached

Student has no attendance record for that course session

Then:

System automatically creates AttendanceRecord with:

status = ABSENT

method = SYSTEM

ABSENT must appear in:

Student attendance history

Staff class records

Phase 5️⃣ Staff Functional Buttons (MAKE THEM REAL)

Fix these buttons:

Monitor Attendance

View Class Records

Manage Schedule

Configure Policies

Expected Behavior:

Monitor Attendance

Shows real-time attendance list for selected course

View Class Records

Historical attendance per course

Manage Schedule

View/edit start, grace, and end times

Configure Policies

Toggle rules (e.g. grace period enabled)

No dummy “operation successful” alerts.

Phase6️⃣ Schedule Enforcement (STRICT)

Attendance status must come ONLY from:

Course Schedule

Current timestamp

No hardcoded PRESENT / LATE buttons anywhere.

Phase 7️⃣ UI Improvements

Course list screen for students

Course-based tabs or dropdowns

Clean Material UI

Nile University of Nigeria logo remains visible

Consistent footer (SEN301)

Phase8️⃣ Data Integrity Rules

A student cannot check in twice for same course session

A student cannot check into a course they are not enrolled in

Attendance records must persist across app restarts