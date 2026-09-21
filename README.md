# 🎓 Student Management System

A Java-based Student Management System developed using **Java, JDBC and MySQL** to efficiently manage student records, academic performance, authentication and reports.

---

## 📌 Project Overview

The **Student Management System** is a console-based application that allows an administrator to manage student information through a structured and database-driven system.

The application provides:

- Student CRUD operations
- Admin authentication
- Student search and sorting
- Marks and grade management
- Academic statistics
- Course-wise analytics
- Performance reports
- CSV export
- Database backup
- Individual student full reports
- Input validation

---

## ✨ Features

### 🔐 Authentication
- Admin login system
- Maximum 3 login attempts
- Login validation
- Logout functionality
- Exit confirmation

### 👨‍🎓 Student Management
- Add Student
- View Students
- Search Student
- Update Student
- Delete Student
- Search students by name
- Search students by marks range

### 📊 Academic Analytics
- Student statistics
- Course-wise statistics
- Top performer
- Top 3 students
- Lowest performer
- Pass/Fail report
- Grade-wise report
- Failed students
- Course student count
- Course performance analysis

### 📑 Reports & Data Management
- Student full report
- CSV export
- Database backup
- Dashboard
- Structured student records

### ✅ Input Validation
- Roll number validation
- Name and course validation
- Marks validation (0–100)
- Menu choice validation
- Login attempt validation
- Exit confirmation

---

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| ☕ Java | Application development |
| 🔗 JDBC | Java–MySQL connectivity |
| 🗄️ MySQL | Database management |
| 📝 SQL | Database queries |
| 💻 VS Code | Development environment |
| 🔌 MySQL Connector/J | JDBC Driver |
| 🌐 GitHub | Version control & project hosting |

---

## 🏗️ Project Architecture

```text
                    👨‍💼 Admin
                       │
                       ▼
              Java Console Interface
                       │
                       ▼
                   Main.java
                       │
                       ▼
              StudentService.java
                       │
                       ▼
                      JDBC
                       │
                       ▼
                DBConnection.java
                       │
                       ▼
                    MySQL
                       │
                       ▼
              student_management
                       │
                       ▼
                   students
