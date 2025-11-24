CampusConnect - Student Grievance Redressal System 🎓

CampusConnect is a desktop-based application designed to bridge the gap between students and university administration. It provides a digital interface for reporting, tracking, and resolving campus issues (infrastructure, academic, or residential), replacing inefficient manual paper trails with a transparent digital workflow.

📖 Table of Contents

Problem Statement

Key Features

Technology Stack

Installation & Execution

Project Structure

Usage Guide

🚩 Problem Statement

In many educational institutions, the process of reporting grievances is often manual, decentralized, and opaque. Students submit written complaints that are easily lost, and there is no mechanism to track the status of a resolution. CampusConnect solves this by offering a centralized database and a role-based dashboard for real-time tracking.

✨ Key Features

🔐 Security & Access Control

Role-Based Access Control (RBAC): Distinct environments for Students (Submission) and Admins (Resolution).

SHA-256 Encryption: User passwords are hashed before storage to ensure data security and prevent plain-text vulnerabilities.

👤 Student Module

Registration: Secure account creation.

Grievance Submission: Categorized reporting (Academic, Hostel, Infrastructure).

History Tracking: View personal grievance history and current status (Pending/Resolved).

🛠 Admin Module

Centralized Dashboard: View all grievances from all users in a tabular format.

Status Updates: Ability to mark issues as "In Progress" or "Resolved".

💾 Data Persistence

File-Based Database: Utilizes Java File I/O to store data in local .txt files (users.txt, grievances.txt), ensuring data is not lost when the application closes.

💻 Technology Stack

Programming Language: Java (JDK 8 or higher)

GUI Libraries: javax.swing, java.awt

Data Handling: java.io (File Handling), java.util (Collections)

Security: java.security.MessageDigest (SHA-256 Hashing)

🚀 Installation & Execution

Prerequisites

Ensure Java (JDK) is installed. Verify by running java -version in your terminal.

Clone or download this repository.

Steps to Run

Navigate to the project root directory in your terminal/command prompt:

cd CampusConnect_Project


Compile the source code:

javac -d bin src/campusconnect/**/*.java


(This command compiles all .java files and places the .class files in a 'bin' folder)

Run the application:

java -cp bin campusconnect.main.CampusConnectApp


📂 Project Structure

CampusConnect_Project/
├── src/
│   └── campusconnect/
│       ├── main/           # Entry point (CampusConnectApp.java)
│       ├── models/         # Data classes (User, Grievance)
│       ├── services/       # Backend Logic & File Handling (DataService)
│       └── views/          # GUI Screens (Login, Dashboards)
├── bin/                    # Compiled bytecode (generated after compilation)
├── users.txt               # Local storage for user credentials
├── grievances.txt          # Local storage for grievance records
├── statement.md            # Problem statement documentation
└── README.md               # Project documentation


📱 Usage Guide

Default Admin Credentials

To test the administrative features immediately, use the pre-configured admin account:

Username: admin

Password: admin123

Testing the Workflow

Run the app and click Register. Create a student account (e.g., john / pass).

Login as john. Submit a grievance (e.g., "WiFi not working").

Logout.

Login as admin. You will see John's grievance.

Select the row and click Update Status to mark it as "Resolved".
