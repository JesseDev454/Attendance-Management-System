1. Introduction

1.1 Purpose
This Software Requirements Specification (SRS) document provides a comprehensive and authoritative description of the Time and Attendance Management System (TAMS), a native Android mobile application developed for Nile University of Nigeria. This document is designed to guide the development, testing, and evaluation of the system, ensuring that all functional and non-functional requirements are clearly defined and understood.

The primary purpose of this document is to:
1.  **Define System Behavior:** Clearly articulate how the application functions, including user interactions, data processing, and system responses.
2.  **Establish Constraints:** Specify the technical boundaries, such as the offline-first architecture, in-memory data storage, and Android platform requirements.
3.  **Facilitate Evaluation:** Serve as the baseline for verifying that the final software product meets the academic and functional goals of the SEN 207 Course Project.

This document covers the entire scope of the software, from the user interface design to the underlying object-oriented logic, ensuring full traceability between the initial requirements and the final Java implementation.

1.2 Document Conventions
To ensure clarity, consistency, and ease of understanding, this document adheres to the following conventions:

1.  **Typographical Conventions:**
    *   **Bold:** Used for key terms, section headings, and emphasis on specific requirements.
    *   *Italics:* Used for document titles, referenced files, and emphasis on specific constraints.
    *   `Monospaced Font`: Used for class names (e.g., `Student`), method names (e.g., `checkIn()`), file paths, and code snippets.

2.  **Requirement Identifiers:**
    *   All requirements are uniquely identified using the format **REQ-Category-Number** (e.g., **REQ-FR-001** for Functional Requirements, **REQ-NFR-001** for Non-Functional Requirements). This facilitates traceability and testing.

3.  **Priority Levels:**
    *   **High:** Critical features that must be implemented for the system to function (e.g., Login, QR Scanning).
    *   **Medium:** Important features that enhance usability but are not critical for the core workflow (e.g., Reporting).
    *   **Low:** Desirable features that may be implemented if time permits (e.g., UI Polish).

4.  **Date and Time Format:**
    *   Dates are represented in **YYYY-MM-DD** format (ISO 8601).
    *   Times are represented in **HH:mm:ss** (24-hour format).

1.3 Intended Audience and Reading Suggestions
This document is intended for a diverse audience, each with specific needs:

1.  **Developers and Programmers:**
    *   *Usage:* To understand the detailed class structures, method signatures, and logic required for implementation.
    *   *Focus:* Sections 3 (External Interfaces), 4 (System Features), and the Class Diagrams.

2.  **Project Supervisors and Evaluators:**
    *   *Usage:* To verify that the project meets the academic requirements and adheres to the specified constraints (e.g., Object-Oriented Design).
    *   *Focus:* Sections 1 (Introduction), 2 (Overall Description), and 5 (Non-Functional Requirements).

3.  **Quality Assurance Testers:**
    *   *Usage:* To design test cases and validate that the application performs as expected under various scenarios.
    *   *Focus:* Section 4 (System Features) and the Stimulus/Response sequences.

4.  **End Users (Students, Staff, Administrators):**
    *   *Usage:* To understand the capabilities and limitations of the system.
    *   *Focus:* Section 2 (User Characteristics) and Section 4 (System Features).

**Reading Suggestion:** It is recommended to read Section 1 and 2 sequentially to gain a high-level understanding, followed by the specific sections relevant to your role.

1.4 Product Scope

**1.4.1 System Overview**
The Time and Attendance Management System (TAMS) is a standalone Android application designed to modernize the attendance tracking process at Nile University of Nigeria. Unlike traditional paper-based methods, TAMS leverages the ubiquity of smartphones and the efficiency of QR code technology to create a seamless, digital attendance experience. The system operates entirely offline, utilizing an in-memory data structure to demonstrate core Object-Oriented Programming principles without the complexity of external database dependencies.

**1.4.2 Key Capabilities**
1.  **Two-Phase QR Code Attendance:**
    The system implements a rigorous "Check-In" and "Check-Out" mechanism. Students must scan a dynamically generated QR code at the start of the class and another at the end. This ensures that students remain present for the duration of the lecture, preventing "attendance fraud" where a student signs in and leaves immediately.

2.  **Role-Based Access Control (RBAC):**
    The application enforces strict security boundaries based on user roles:
    *   **Students:** Can only scan codes and view their own history.
    *   **Staff:** Can generate codes, monitor real-time class stats, and manage schedules.
    *   **Administrators:** Have full control over user accounts and system policies.

3.  **Real-Time Logic & Validation:**
    Although offline, the system performs real-time validation. It checks if the scanned QR code is valid for the current time, if the student is enrolled in the course, and if the scan is a duplicate. Feedback is instantaneous.

4.  **Automated Reporting:**
    The system automatically compiles attendance data into readable reports. It can identify students who are "Absent" (failed to scan) or "Incomplete" (scanned in but not out), reducing the administrative burden on lecturers.

**1.4.3 Benefits**
*   **Academic Integrity:** Eliminates proxy attendance through secure, device-specific logins and real-time QR validation.
*   **Operational Efficiency:** Removes the need for manual roll calls, saving valuable lecture time.
*   **Cost Effectiveness:** Requires no specialized hardware (biometric scanners) or infrastructure (servers/cloud), running entirely on existing user devices.
*   **Data Accuracy:** Digital records eliminate handwriting legibility issues and manual data entry errors.

**1.4.4 Out of Scope (Strict Constraints)**
To maintain the academic focus and feasibility of the project, the following are explicitly **excluded**:
*   **Biometric Integration:** No fingerprint or facial recognition hardware.
*   **Cloud Synchronization:** No data is sent to a remote server; all data resides in the device's RAM during the session.
*   **Web Interface:** There is no browser-based portal; the system is Android-only.
*   **Persistent Database:** No SQL (SQLite/Room) or NoSQL databases are used. Data is volatile and resets on app restart (simulated persistence via file export is optional).

1.5 References
The following documents and resources were used in the preparation of this specification:

1.  **IEEE Standards:**
    *   IEEE Std 830-1998, *IEEE Recommended Practice for Software Requirements Specifications*.

2.  **Technical Documentation:**
    *   *Android Developer Documentation* (developer.android.com) - API Level 24+ Guidelines.
    *   *ZXing ("Zebra Crossing") Library Documentation* - QR Code generation and scanning.

3.  **Stakeholder Interviews (Nile University):**
    *   *Interview 1:* Abdulsalam Nur (Lecturer) - Insights on classroom management challenges (31/12/2025).
    *   *Interview 2:* Obi Tabugbo (Security Manager) - Inputs on access control and identity verification (31/12/2025).
    *   *Interview 3:* Ologun Abraham (Admin Assistant) - Requirements for reporting and record-keeping (01/01/2026).
    *   *Interview 4:* Rogbitan Seun (Creative Lead) - Suggestions for UI/UX and branding consistency (03/01/2026).

4.  **Project Artifacts:**
    *   *TAMS Design Review Report* - Initial architectural decisions.
    *   *TAMS Testing Report* - Validation of core functionalities.
