# CampusConnect

CampusConnect - Java Swing Application
A desktop-based Student Grievance Redressal System built using Java Swing and File I/O.
Features
Modular Design: Separate classes for Models, Views, and Services.
File Handling: Stores users and grievances in .txt files (No MySQL installation needed).
Role-Based Access: * Student: Can Log in, Post Grievance, View Own History.
Admin: Can Log in, View All Grievances, Update Status.
Project Structure
models/ - Contains data classes (User, Grievance).
services/ - Handles File I/O and Authentication logic.
views/ - Contains JFrame GUI classes.
main/ - The entry point of the application.
Prerequisites
Java Development Kit (JDK) 8 or higher.
How to Run
1. Compile all files:
javac -d bin src/campusconnect/**/*.java
2. Run the Main class:
java -cp bin campusconnect.main.CampusConnectApp
3. Default Admin Credentials:
Username: admin
Password: admin123
Testing
Register a new user via the GUI.
Login as the new user and submit a grievance.
Logout and login as Admin to resolve it
