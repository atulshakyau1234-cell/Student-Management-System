import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StudentService service = new StudentService();

            public static void main(String[] args) {

                // ================= LOGIN LOOP  =================
             while(true){

                 // ================= ADMIN LOGIN =================
                boolean loggedIn = AdminLogin.login();

                if (!loggedIn) {
                    System.out.println("Access Denied!");
                    return;
                }

                // ================= MAIN MENU =================

                boolean sessionActive = true;
                while (sessionActive) {
                        System.out.println("\n========================================");
                        System.out.println("       STUDENT MANAGEMENT SYSTEM");
                        System.out.println("========================================");
                        System.out.println("1. Add Student");
                        System.out.println("2. View Students");
                        System.out.println("3. Search Student");
                        System.out.println("4. Update Student");
                        System.out.println("5. Delete Student");
                        System.out.println("6. Sort Students");
                        System.out.println("7. Student Statistics");
                        System.out.println("8. Course-wise Statistics");
                        System.out.println("9. Top Performer");
                        System.out.println("10. Pass/Fail Report");
                        System.out.println("11. Grade-wise Report");
                        System.out.println("12. Failed Students");
                        System.out.println("13. Top 3 Students");
                        System.out.println("14. Marks Range Search");
                        System.out.println("15. Lowest Performer");
                        System.out.println("16. Course Student Count");
                        System.out.println("17. Course Performance");
                        System.out.println("18. Dashboard");
                        System.out.println("19. Export Student CSV");
                        System.out.println("20. Database Backup");
                        System.out.println("21. Student Full Report");
                        System.out.println("22. Logout");
                        System.out.println("23. Exit");

                        System.out.println("========================================");
                        System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
               System.out.println("❌ Please enter a number from 1 to 23.");
                sc.nextLine();
                continue;
            }


            switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        service.viewStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        updateStudent();
                        break;

                    case 5:
                        deleteStudent();
                        break;

                    case 6:
                        sortStudents();
                        break;

                    case 7:
                        service.showStatistics();
                        break;

                    case 8:
                        service.showCourseStatistics();
                        break;

                    case 9:
                        service.showTopPerformer();
                        break;

                    case 10:
                        service.showPassFailReport();
                        break;

                    case 11:
                        service.showGradeReport();
                        break;

                    case 12:
                        service.showFailedStudents();
                        break;

                    case 13:
                        service.showTopThreeStudents();
                        break;

                    case 14:

                        System.out.print("Enter minimum marks: ");

                        double minMarks;

                        try {
                            minMarks = sc.nextDouble();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("❌ Marks must be a number.");
                            sc.nextLine();
                            break;
                        }

                        System.out.print("Enter maximum marks: ");

                        double maxMarks;

                        try {
                            maxMarks = sc.nextDouble();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("❌ Marks must be a number.");
                            sc.nextLine();
                            break;
                        }

                        if (minMarks < 0 ||
                            maxMarks > 100 ||
                            minMarks > maxMarks) {

                            System.out.println("❌ Invalid marks range!");
                            break;
                        }

                        service.searchStudentsByMarks(minMarks, maxMarks);
                        break;

                    case 15:
                        service.showLowestPerformer();
                        break;

                    case 16:
                        service.showCourseStudentCount();
                        break;

                    case 17:
                        service.showCoursePerformance();
                        break;

                    case 18:
                        service.showDashboard();
                        break;

                    case 19:
                        service.exportStudentsToCSV();
                        break;

                    case 20:
                        service.backupStudentData();
                        break;

                    case 21:

                        System.out.print("Enter Roll No: ");

                        int reportRollNo;

                        try {
                            reportRollNo = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("❌ Roll No. must be a number.");
                            sc.nextLine();
                            break;
                        }

                        if (reportRollNo <= 0) {
                            System.out.println("❌ Roll No. must be greater than 0.");
                            break;
                        }

                        service.showFullStudentReport(reportRollNo);
                        break;
                   

                        case 22:
                                    System.out.println("\n🔐 Logging out...");
                                    System.out.println("✅ You have been logged out.");
                                    sessionActive = false;
                                    break;
                        case 23:

                                    System.out.print(
                                            "\nAre you sure you want to exit? (Y/N): "
                                    );

                                    String exitConfirmation = sc.nextLine();

                                    if (exitConfirmation.equalsIgnoreCase("Y")) {

                                        System.out.println(
                                                "\nThank you for using Student Management System!"
                                        );

                                        System.out.println("Program closed.");
                                        return;

                                    } else if (exitConfirmation.equalsIgnoreCase("N")) {

                                        System.out.println("\n❌ Exit cancelled.");

                                    } else {

                                        System.out.println(
                                                "\n❌ Please enter Y or N."
                                        );
                                    }

                                    break;

                    default:
                        System.out.println(
                                "❌ Invalid choice! Please enter 1 to 23."
                        );
            }
        }
    }
}


    // ================= ADD =================

    static void addStudent() {

        System.out.print("Enter Roll No: ");
        int rollNo;

        try {
            rollNo = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Roll No. must be a number.");
            sc.nextLine();
            return;
        }
         
                if (rollNo <= 0) {
            System.out.println("❌ Roll No. must be greater than 0.");
            return;
        }


        System.out.print("Enter Name: ");
            String name = sc.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("❌ Name cannot be empty.");
                return;
            }

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            if (course.trim().isEmpty()) {
                System.out.println("❌ Course cannot be empty.");
                return;
            }


            System.out.print("Enter Marks: ");
             double marks;

            try {
                marks = sc.nextDouble();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Marks must be a number.");
                sc.nextLine();
                return;
            }

        if (marks < 0 || marks > 100) {
            System.out.println("❌ Marks must be between 0 and 100.");
            return;
        }


        Student student =
                new Student(
                        rollNo,
                        name,
                        course,
                        marks
                );

        boolean result =
                service.addStudent(student);

        if (result) {
            System.out.println(
                    "Student Added Successfully!"
            );
        } else {
            System.out.println(
                    "Student could not be added!"
            );
        }
    }


// ================= SEARCH =================

static void searchStudent() {

    System.out.println("\n===== SEARCH STUDENT =====");
    System.out.println("1. Search by Roll No.");
    System.out.println("2. Search by Name");
    System.out.print("Enter your choice: ");

    int choice;

    try {
        choice = sc.nextInt();
        sc.nextLine();
    } catch (Exception e) {
        System.out.println("❌ Please enter a number.");
        sc.nextLine();
        return;
    }

    // Search by Roll No.
    if (choice == 1) {

        System.out.print("Enter Roll No: ");

        int rollNo;

        try {
            rollNo = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Roll No. must be a number.");
            sc.nextLine();
            return;
        }

        if (rollNo <= 0) {
            System.out.println("❌ Roll No. must be greater than 0.");
            return;
        }

        Student student = service.searchStudent(rollNo);

        if (student != null) {
            System.out.println("\nStudent Found:");
            student.displayStudent();
        } else {
            System.out.println("❌ Student Not Found!");
        }
    }

    // Search by Name
    else if (choice == 2) {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("❌ Name cannot be empty.");
            return;
        }

        service.searchStudentByName(name);
    }

    else {
        System.out.println("❌ Invalid search option!");
    }
}


// ================= UPDATE =================

static void updateStudent() {

    System.out.print("Enter Roll No: ");

    int rollNo;

    try {
        rollNo = sc.nextInt();
        sc.nextLine();
    } catch (Exception e) {
        System.out.println("❌ Roll No. must be a number.");
        sc.nextLine();
        return;
    }

    if (rollNo <= 0) {
        System.out.println("❌ Roll No. must be greater than 0.");
        return;
    }

    System.out.print("Enter New Name: ");
    String name = sc.nextLine();

    if (name.trim().isEmpty()) {
        System.out.println("❌ Name cannot be empty.");
        return;
    }

    System.out.print("Enter New Course: ");
    String course = sc.nextLine();

    if (course.trim().isEmpty()) {
        System.out.println("❌ Course cannot be empty.");
        return;
    }

    System.out.print("Enter New Marks: ");

    double marks;

    try {
        marks = sc.nextDouble();
        sc.nextLine();
    } catch (Exception e) {
        System.out.println("❌ Marks must be a number.");
        sc.nextLine();
        return;
    }

    if (marks < 0 || marks > 100) {
        System.out.println("❌ Marks must be between 0 and 100.");
        return;
    }

    boolean result =
            service.updateStudent(
                    rollNo,
                    name,
                    course,
                    marks
            );

    if (result) {
        System.out.println("Student Updated Successfully!");
    } else {
        System.out.println("Student Not Found!");
    }
}


// ================= DELETE =================

static void deleteStudent() {

    System.out.print("Enter Roll No: ");

    int rollNo;

    try {
        rollNo = sc.nextInt();
        sc.nextLine();
    } catch (Exception e) {
        System.out.println("❌ Roll No. must be a number.");
        sc.nextLine();
        return;
    }

    if (rollNo <= 0) {
        System.out.println("❌ Roll No. must be greater than 0.");
        return;
    }

    // Delete Confirmation
    System.out.print(
        "Are you sure you want to delete this student? (Y/N): "
    );

    String confirmation = sc.nextLine();

    if (confirmation.equalsIgnoreCase("N")) {
        System.out.println("❌ Delete cancelled.");
        return;
    }

    if (!confirmation.equalsIgnoreCase("Y")) {
        System.out.println("❌ Please enter Y or N.");
        return;
    }

    boolean result = service.deleteStudent(rollNo);

    if (result) {
        System.out.println("✅ Student Deleted Successfully!");
    } else {
        System.out.println("❌ Student Not Found!");
    }
}

// ================= SORT =================

static void sortStudents() {

    System.out.println("\n===== SORT STUDENTS =====");
    System.out.println("1. Sort by Roll No.");
    System.out.println("2. Sort by Name");
    System.out.println("3. Sort by Marks");
    System.out.print("Enter your choice: ");

    int option;

    try {
        option = sc.nextInt();
        sc.nextLine();
    } catch (Exception e) {
        System.out.println("❌ Please enter a number.");
        sc.nextLine();
        return;
    }

    if (option < 1 || option > 3) {
        System.out.println("❌ Invalid sorting option!");
        return;
    }

    service.sortStudents(option);
}

}