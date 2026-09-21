# 🎓 Student Management System

A Java-based Student Management System developed using Java, JDBC, and MySQL.
The application provides student record management, authentication, analytics,
reports, CSV export, and database backup functionality.

---

## 🚀 Features

### 🔐 Authentication
- Admin Login
- Maximum 3 Login Attempts
- Logout System
- Exit Confirmation

### 👨‍🎓 Student Management
- Add Student
- View All Students
- Search Student by Roll Number
- Search Student by Name
- Update Student
- Delete Student
- Delete Confirmation

### 📊 Student Analytics
- Student Statistics
- Course-wise Statistics
- Top Performer
- Top 3 Students
- Lowest Performer
- Pass/Fail Report
- Grade-wise Report
- Failed Students
- Marks Range Search
- Course Student Count
- Course Performance
- Student Dashboard

### 📄 Reports & Data Management
- Individual Student Full Report
- Export Student Data to CSV
- Database Backup
- Grade Calculation
- Percentage Calculation

### 🛡️ Validation
- Roll Number Validation
- Marks Validation (0–100)
- Empty Name Validation
- Empty Course Validation
- Numeric Input Validation
- Menu Choice Validation
- Search and Sort Validation

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Application development |
| JDBC | Java-MySQL connectivity |
| MySQL | Database management |
| SQL | Database queries |
| VS Code | Development environment |
| MySQL Connector/J | JDBC Driver |

---

## 🗂️ Project Structure

```text
Student_Management_System.java/
│
├── lib/
│   └── mysql-connector-j-26.7.0.jar
│
├── DBConnection.java
├── Student.java
├── StudentService.java
├── AdminLogin.java
├── Main.java
├── settings.json
├── students_report.csv
└── student_database_backup.sql