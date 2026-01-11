1.	Introduction
1.1	PURPOSE
This Software Requirements Specification (SRS) document provides a comprehensive description of the Time and Attendance Management System (TAMS) developed for Nile University of Nigeria. This document is aligned with the actual implemented Android application and serves as the authoritative reference for the system’s functionality.

1. Two-Phase QR Code Attendance (Check-In & Check-Out)
The system uses QR codes to record attendance at two distinct points: when a student arrives (Check-In) and when they leave (Check-Out). This prevents fake attendance and allows accurate tracking of presence duration, not just arrival.

2. Role-Based Access Control (Student, Staff, Administrator)
Access to system features is restricted based on user roles.
•	Students can mark attendance and view their records.
•	Staff can manage courses, generate QR codes, and view attendance.
•	Administrators can manage users, roles, and system-wide data.
This ensures security and prevents users from doing things they shouldn’t. No “student editing attendance”.

3. Real-Time Attendance Tracking and Management
Attendance records are updated instantly once a QR code is scanned. Lecturers and administrators can view attendance status in real time, making it easy to monitor class participation as it happens, not three weeks later.

4. Course Enrollment Management
Students are linked to specific courses within the system. Attendance can only be recorded for courses a student is officially enrolled in, ensuring accurate class lists and preventing random attendance entries.

5. Attendance History and Reporting
The system stores attendance records for each student over time. These records can be reviewed to analyze trends, generate reports, and support academic or administrative decisions such as eligibility for exams.

6. Absent Student Processing
The system identifies students who fail to check in or check out for a session. These students are automatically marked absent, reducing manual work and eliminating guesswork during attendance compilation.

Out of Scope (Not Implemented)
1. Biometric Authentication Hardware Integration
The system does not integrate with fingerprint scanners, facial recognition devices, or other biometric hardware. Attendance is recorded strictly through QR code scanning.

2. Payroll System Integration
Attendance data is not linked to salary calculations or staff payroll systems. The system is focused solely on attendance management, not financial operations.

3. Web-Based Interface
The application does not include a browser-based (web) version. Interaction with the system is limited to the implemented platform (e.g., mobile or desktop app).

4. Cloud Database Synchronization
Data is stored locally or within a single database instance. There is no real-time synchronization with cloud services or multi-device data replication.

5. GPS / Geofencing
The system does not track user location or restrict attendance based on geographical boundaries. Attendance validation relies entirely on QR code scanning.

1.2	Document Conventions
Document Conventions for the Time and Attendance Management System  
This section outlines the conventions used throughout the Software Requirements Specification (SRS) document to ensure clarity, consistency, and ease of use. By adhering to standardized formatting, labeling, and organization, the document facilitates readability and ensures accurate communication among all stakeholders.  

1. Formatting Conventions:  
•	Font Type: We have chosen a standard, readable font which is Times New Roman, typically in size 12 for body text.
•	Headings: We will use a hierarchical heading structure (e.g., Heading 1 for main sections, heading 2 for subsections) to organize content clearly. Headings will be bolded for emphasis.
•	Bold: Used to highlight key terms, headings, and important requirements.  
•	Italics: Used for emphasis and to denote document titles or references.  
•	Mono spaced Font: Used for code snippets, system messages, and commands.  
2. Requirements Labeling:  
All system requirements are distinctly identified using the pattern REQ [Number] (e.g., REQ001, REQ002) for reference and traceability.  
3. Numbering and Bullets:  
•	Numbered Lists: Used to outline steps in processes or to organize ordered information.  
•	Bullet Lists: Used to present unordered information or key points.  

4. Date and Time Format:  
•	Date: Follows the YYYYMMDD format (e.g., 20241224).  
•	Time: Follows the 24hour format (e.g., 14:30 for 2:30 PM).  

5. Units of Measurement:  
•	Time: Measured in hours and minutes.  
•	Attendance: Recorded as Present, Absent or Late.  

6. Section Organization:  
Sections and subsections are hierarchically numbered (e.g., 1, 1.1, 1.2) for clear structure and logical flow.  

7. Glossary:  
Technical terms, abbreviations, and acronyms are explained in the glossary to ensure a consistent understanding of specialized language.  
	
8. Figures, Tables, and Diagrams:  
•	Diagrams: We will use diagrams or tables where appropriate to illustrate complex processes or relationships between requirements, such as the flow of attendance data.


1.3	Intended Audience and Reading Suggestions

Intended Audience

1. Developers  
   Developers will use this document to understand the functional and non-functional requirements, system design constraints, and technical specifications necessary for building the software. They are advised to focus on the detailed requirements (Sections 3 and 4) and system architecture.

2. Project Managers  
   Project managers will use the document to plan, allocate resources, and monitor the progress of the software's development. They should begin with the purpose (Section 1) and scope (Section 2) to understand the goals and boundaries of the project, then review the functional requirements and priorities.
	
3. Marketing Staff  
   Marketing teams can leverage the document to understand the value propositions and key benefits of the software. They should focus on the system overview and benefits (Sections 1.1 and 2.1), as well as use cases that demonstrate the software's relevance to end-users.  

4. End Users  
   End users, such as employees, students, and administrators, can refer to this document to gain insight into the software's functionality and usability. They are encouraged to review the use case scenarios (Section 2.3) and the functional requirements that directly impact their interactions with the system.

5. Testers  
   Quality assurance testers will utilize the document to create test cases and verify that the system meets the defined requirements. They should pay particular attention to the detailed functional requirements (Section 3), nonfunctional requirements (Section 4), and acceptance criteria.  

6. Documentation Writers  
   Technical writers responsible for user manuals and guides will use the document to understand the system's features and workflows. They should focus on the functional requirements and use case scenarios (Section 2.3), which will provide the necessary information to craft comprehensive user documentation.  

 Document Structure and Suggested Reading Order

This document is structured to facilitate ease of understanding for all stakeholders.  
	
1. Introduction (Section 1): 
   Provides an overview of the purpose, scope, and audience of the document. All readers should begin here to gain context about the software.  
	
2. Overall Description (Section 2): 
   Describes the system's goals, benefits, and key use cases. This section is particularly useful for project managers, marketing staff, and end users.  

3. Specific Requirements (Section 3): 
   Details the functional and nonfunctional requirements of the software. Developers, testers, and documentation writers should prioritize this section.  
	
4. System Models (Section 4): 
   Includes diagrams, flowcharts, and architectural overviews to illustrate how the system components interact. This section is vital for developers and technical architects.  

5. Appendices (Section 5)  
   Contains reference materials, definitions, and supplementary information. Readers seeking clarification or additional context should refer to this section.  

For most readers, it is recommended to follow this sequence: Introduction → Overall Description → Specific Requirements → System Models. Additional sections can be reviewed as needed based on roles and responsibilities.  

1.4	Product Scope

Brief description of the Time and Attendance System
As the 21st century continues to evolve digitally, most modern learning institutions are adopting automated systems to improve efficiency and accuracy in managing academic and administrative processes. One critical aspect of institutional management is attendance tracking, as it plays a vital role in monitoring student participation and staff engagement. Managing attendance for a large population is challenging, especially when relying on manual or paper-based methods.
At Nile University of Nigeria, attendance recording is still largely handled using traditional methods, which are time-consuming, error-prone, and inefficient. These methods make it difficult to accurately track attendance, generate reliable records, and identify patterns such as absenteeism or lateness. As student and staff populations grow, the limitations of manual attendance systems become increasingly evident.
The Time and Attendance Management System (TAMS) is designed to address these challenges by providing a digital, QR code–based solution for attendance tracking. The system enables students and staff to check in and check out of classes or work sessions using a mobile application, ensuring accurate and real-time attendance recording. By automating the attendance process, the system reduces administrative workload and eliminates inconsistencies associated with manual record keeping.
The primary purpose of TAMS is to improve operational efficiency by automating attendance tracking and management while providing reliable attendance records for academic and administrative decision-making. The system allows authorized users such as lecturers and administrators to monitor attendance, view attendance history, and generate reports based on defined parameters. This supports proactive identification of attendance issues and promotes accountability among users.
By leveraging QR-based attendance logging and secure user authentication, TAMS enhances transparency, accuracy, and reliability in attendance management. The system provides a user-friendly interface that supports students, staff, and administrators, ensuring ease of use while maintaining data integrity and security. Ultimately, the Time and Attendance Management System contributes to a more organized, efficient, and accountable academic environment.

Benefits of a Time and Attendance Management System
1.	Enhanced Accuracy:
Reduces time lost to manual errors commonly encountered when recording attendance or preparing payroll, ensuring precise and reliable data.

2.	Improved Efficiency:
Automates time-consuming tasks such as monitoring attendance, approving or rejecting leave requests, and maintaining records.

3.	Regulatory Compliance:
Ensures adherence to labor laws by accurately recording working hours, breaks, and overtime, reducing the risk of violations.

4.	Cost Savings:
Minimizes overpayments caused by time theft, inaccurate calculations, or manual processing errors, thereby reducing payroll costs.

5.	Flexible Functionality:
Provides support for remote work and hybrid arrangements through features like mobile clock in and geolocation tracking.

6.	Data Driven Decision Making:
Enhances resource planning and operational efficiency by analyzing real-time data on attendance, availability, and productivity.

7.	Improved Corporate Image:
Builds trust with stakeholders by maintaining honest and transparent records of employee time spent on organizational activities.

8.	Organizational Time Optimization:
Increases overall productivity by minimizing time wasted on manual tracking and redundant processes.

9.	Reduced Bureaucracy:
Simplifies the process of tracking work hours, reducing administrative burdens and associated costs.

10.	Lower Risk Levels:
Decreases risks for both organizations and employees by maintaining clear, compliant, and secure records of attendance and work hours.

11.	Scalable and Flexible Solution:
Adapts to evolving business needs, providing a solution that can grow with the organization as its workforce and operational demands expand	



 Objectives  
1.	Precision in Time Tracking:  
 	 Implement advanced tools to ensure precise logging of employee work hours, including overtime and breaks, through QR systems, mobile apps, or cloud-based platforms.  

2.	Streamlined Payroll Processes:  
 	 Automate payroll operations by integrating with existing HR and accounting systems, reducing errors and manual intervention in wage calculation.  
	
3.	Data Driven Resource Allocation:  
  	Utilize detailed analytics to optimize staffing, reduce downtime, and allocate resources efficiently across various departments.  
	
4.	Improved Employee Experience:  
  	Foster transparency and trust by providing employees with real-time access to their attendance records, leave balances, and work schedules.  

 Goals  
1.	Boost Organizational Productivity:
  	Optimize employee performance and operational workflows by eliminating bottlenecks in time tracking and attendance management.  

2.	Minimize Administrative Overhead: 
 	 Reduce the workload and costs associated with manual timekeeping and error correction by automating routine tasks.  
	
3.	Promote Accountability and Transparency:  
 	 Build a culture of trust by offering accurate, transparent time records and fostering accountability among employees.  

4.	Deliver a Scalable Solution:  
 	 Provide a robust platform that can adapt to evolving business requirements, supporting workforce expansion and new operational models without compromising efficiency.  

Relation to corporate Goals and Business Strategies

The Time and Attendance Management Software directly aligns with corporate goals and business strategies by improving efficiency, ensuring compliance, and fostering accountability among staff and students. Here’s how it relates:  

1.	Enhancing Operational Efficiency:
By automating repetitive tasks such as attendance tracking, payroll processing, and resource scheduling, the software reduces administrative overhead and allows organizations to allocate resources strategically. This supports the corporate goal of maximizing productivity while minimizing waste.  

2.	Driving Data Driven Decision Making: 
Real-time insights provided by the software enable management to identify trends, optimize staffing, and improve resource allocation. These capabilities align with business strategies focused on leveraging analytics for informed decision making and continuous improvement.  

3.	Ensuring Compliance and Risk Mitigation:  
By accurately tracking work hours, overtime, and attendance, the software ensures compliance with labor laws and institutional policies, reducing the risk of legal disputes or financial penalties. This supports corporate goals of maintaining ethical standards and regulatory adherence.  

4.	Promoting Accountability and Transparency:  
The system fosters a culture of trust by providing clear, accessible records for both staff and students. Transparency in time tracking and attendance management builds credibility and aligns with business strategies focused on improving organizational culture and stakeholder trust.  

5.	Supporting Flexibility and Scalability:  
  	As organizations grow, the software scales to meet increased demands, integrating seamlessly with existing systems to support evolving business needs. This aligns with corporate goals of adaptability and long-term sustainability.  

6. Enhancing Employee and Student Engagement:
  	For employees, the software simplifies timekeeping and payroll processes, boosting satisfaction and morale. For students, it reinforces accountability and supports academic success. Aligning with strategies that prioritize stakeholder wellbeing, the software contributes to a positive institutional reputation.  

By addressing key operational challenges and aligning with broader organizational objectives, the Time and Attendance Management Software becomes a strategic tool that drives efficiency, compliance, and engagement across the institution.



1.5	References

1.	IEEE Software Engineering Standards:
•	Title: IEEE Standard for Software Requirements Specifications
•	Author: IEEE Computer Society
•	Version Number: IEEE Std 830-1998
•	Date: 1998
•	Source/Location: IEEE Xplore Digital Library

2.	Interview 1:
•	Name of the interviewee: Abdulsalam Nur (Lecturer)
•	Organization: Nile University of Nigeria
•	Date of the interview: 31/12/2025  
•	Location or format: online

3.	Interview 2:
•	Name of the interviewee: Obi Tabugbo (Security and safety Manager)
•	Organization: Nile University of Nigeria
•	Date of the interview: 31/12/2025  
•	Location or format: online

4.	Interview 3:
•	Name of the interviewee: Ologun Abraham (Administrative Assistant)
•	Organization: Nile University of Nigeria
•	Date of the interview: 1/1/2026  
•	Location or format: online

5.	Interview 4:
•	Name of the interviewee: Rogbitan Seun (Creative Lead (Media and Graphics))
•	Organization: Nile University of Nigeria
•	Date of the interview: 3/1/2026 
•	Location or format: online
	
