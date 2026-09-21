import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {

    // ================= ADD =================

    public boolean addStudent(Student student) {

        String sql =
                "INSERT INTO students (roll_no, name, course, marks) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, student.rollNo);
            statement.setString(2, student.name);
            statement.setString(3, student.course);
            statement.setDouble(4, student.marks);

            int rows = statement.executeUpdate();

            return rows > 0;

        }catch (SQLException e) {

    if (e.getErrorCode() == 1062) {

        System.out.println(
                "❌ Roll number already exists!"
        );

    } else {

        System.out.println(
                "❌ Error while adding student!"
        );

        e.printStackTrace();
    }

    return false;
}
    }

       // ================= View =================

    public void viewStudents() {

    String sql =
            "SELECT roll_no, name, course, marks FROM students";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        boolean found = false;

        System.out.println("\n======================================================================");
        System.out.println("                         STUDENT LIST");
        System.out.println("======================================================================");

        System.out.printf(
                "%-10s %-20s %-25s %-10s %-8s%n",
                "Roll No", "Name", "Course", "Marks", "Grade"
        );

        System.out.println("----------------------------------------------------------------------");

        while (result.next()) {

            found = true;

            int rollNo = result.getInt("roll_no");
            String name = result.getString("name");
            String course = result.getString("course");
            double marks = result.getDouble("marks");

            String grade;

            if (marks >= 90)
                grade = "A+";
            else if (marks >= 80)
                grade = "A";
            else if (marks >= 70)
                grade = "B";
            else if (marks >= 60)
                grade = "C";
            else if (marks >= 50)
                grade = "D";
            else
                grade = "F";

            System.out.printf(
                    "%-10d %-20s %-25s %-10.2f %-8s%n",
                    rollNo, name, course, marks, grade
            );
        }

        System.out.println("======================================================================");

        if (!found) {
            System.out.println("No students found.");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while viewing students!");
        e.printStackTrace();
    }
}

// ================= SORT STUDENTS =================

public void sortStudents(int option) {

    String sql;

    if (option == 1) {
        sql = "SELECT roll_no, name, course, marks " +
              "FROM students ORDER BY roll_no ASC";
    }
    else if (option == 2) {
        sql = "SELECT roll_no, name, course, marks " +
              "FROM students ORDER BY name ASC";
    }
    else if (option == 3) {
        sql = "SELECT roll_no, name, course, marks " +
              "FROM students ORDER BY marks DESC";
    }
    else {
        System.out.println("❌ Invalid sorting option!");
        return;
    }

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        boolean found = false;

        System.out.println("\n======================================================================");
        System.out.println("                         SORTED STUDENTS");
        System.out.println("======================================================================");

        System.out.printf(
                "%-10s %-20s %-25s %-10s %-8s%n",
                "Roll No", "Name", "Course", "Marks", "Grade"
        );

        System.out.println("----------------------------------------------------------------------");

        while (result.next()) {

            found = true;

            int rollNo = result.getInt("roll_no");
            String name = result.getString("name");
            String course = result.getString("course");
            double marks = result.getDouble("marks");

            String grade;

            if (marks >= 90)
                grade = "A+";
            else if (marks >= 80)
                grade = "A";
            else if (marks >= 70)
                grade = "B";
            else if (marks >= 60)
                grade = "C";
            else if (marks >= 50)
                grade = "D";
            else
                grade = "F";

            System.out.printf(
                    "%-10d %-20s %-25s %-10.2f %-8s%n",
                    rollNo, name, course, marks, grade
            );
        }

        System.out.println("======================================================================");

        if (!found) {
            System.out.println("No students found.");
        }

    } catch (SQLException e) {
        System.out.println("❌ Error while sorting students!");
        e.printStackTrace();
    }
}


    // ================= SEARCH =================

    public Student searchStudent(int rollNo) {

        String sql =
                "SELECT roll_no, name, course, marks " +
                "FROM students WHERE roll_no = ?";

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, rollNo);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                int roll =
                        result.getInt("roll_no");

                String name =
                        result.getString("name");

                String course =
                        result.getString("course");

                double marks =
                        result.getDouble("marks");

                return new Student(
                        roll,
                        name,
                        course,
                        marks
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error while searching student!"
            );

            e.printStackTrace();
        }

        return null;
    }

    // ================= SEARCH BY NAME =================

public void searchStudentByName(String name) {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students WHERE name LIKE ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setString(1, "%" + name + "%");

        ResultSet result = statement.executeQuery();

        boolean found = false;

        System.out.println("\n======================================================================");
        System.out.println("                         SEARCH RESULTS");
        System.out.println("======================================================================");

        System.out.printf(
                "%-10s %-20s %-25s %-10s %-8s%n",
                "Roll No", "Name", "Course", "Marks", "Grade"
        );

        System.out.println("----------------------------------------------------------------------");

        while (result.next()) {

            found = true;

            int rollNo = result.getInt("roll_no");
            String studentName = result.getString("name");
            String course = result.getString("course");
            double marks = result.getDouble("marks");

            String grade;

            if (marks >= 90)
                grade = "A+";
            else if (marks >= 80)
                grade = "A";
            else if (marks >= 70)
                grade = "B";
            else if (marks >= 60)
                grade = "C";
            else if (marks >= 50)
                grade = "D";
            else
                grade = "F";

            System.out.printf(
                    "%-10d %-20s %-25s %-10.2f %-8s%n",
                    rollNo, studentName, course, marks, grade
            );
        }

        System.out.println("======================================================================");

        if (!found) {
            System.out.println("❌ Student Not Found!");
        }

    } catch (SQLException e) {
        System.out.println("❌ Error while searching student!");
        e.printStackTrace();
    }
}


    // ================= UPDATE =================

    public boolean updateStudent(
            int rollNo,
            String name,
            String course,
            double marks) {

        String sql =
                "UPDATE students " +
                "SET name = ?, course = ?, marks = ? " +
                "WHERE roll_no = ?";

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, course);
            statement.setDouble(3, marks);
            statement.setInt(4, rollNo);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error while updating student!"
            );

            e.printStackTrace();

            return false;
        }
    }


    // ================= DELETE =================

    public boolean deleteStudent(int rollNo) {

        String sql =
                "DELETE FROM students WHERE roll_no = ?";

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, rollNo);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error while deleting student!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // ================= STUDENT STATISTICS =================

public void showStatistics() {

    String sql =
            "SELECT COUNT(*) AS total, " +
            "AVG(marks) AS average, " +
            "MAX(marks) AS highest, " +
            "MIN(marks) AS lowest " +
            "FROM students";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        if (result.next()) {

            int total = result.getInt("total");
            double average = result.getDouble("average");
            double highest = result.getDouble("highest");
            double lowest = result.getDouble("lowest");

            String passSql =
                    "SELECT COUNT(*) AS passed " +
                    "FROM students WHERE marks >= 50";

            String failSql =
                    "SELECT COUNT(*) AS failed " +
                    "FROM students WHERE marks < 50";

            int passed = 0;
            int failed = 0;

            try (PreparedStatement passStatement =
                         connection.prepareStatement(passSql);
                 ResultSet passResult =
                         passStatement.executeQuery()) {

                if (passResult.next()) {
                    passed = passResult.getInt("passed");
                }
            }

            try (PreparedStatement failStatement =
                         connection.prepareStatement(failSql);
                 ResultSet failResult =
                         failStatement.executeQuery()) {

                if (failResult.next()) {
                    failed = failResult.getInt("failed");
                }
            }

            System.out.println("\n========================================");
            System.out.println("          STUDENT STATISTICS");
            System.out.println("========================================");

            System.out.println("Total Students       : " + total);
            System.out.printf("Average Marks        : %.2f%n", average);
            System.out.printf("Highest Marks        : %.2f%n", highest);
            System.out.printf("Lowest Marks         : %.2f%n", lowest);
            System.out.println("Passed Students      : " + passed);
            System.out.println("Failed Students      : " + failed);

            System.out.println("========================================");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while calculating statistics!");
        e.printStackTrace();
    }
}


// ================= COURSE-WISE STATISTICS =================

public void showCourseStatistics() {

    String sql =
            "SELECT course, COUNT(*) AS total_students, " +
            "AVG(marks) AS average_marks " +
            "FROM students " +
            "GROUP BY course " +
            "ORDER BY course";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        boolean found = false;

        System.out.println("\n==============================================================");
        System.out.println("                  COURSE-WISE STATISTICS");
        System.out.println("==============================================================");

        System.out.printf(
                "%-25s %-15s %-15s%n",
                "Course", "Students", "Avg Marks"
        );

        System.out.println("--------------------------------------------------------------");

        while (result.next()) {

            found = true;

            String course = result.getString("course");
            int totalStudents = result.getInt("total_students");
            double averageMarks = result.getDouble("average_marks");

            System.out.printf(
                    "%-25s %-15d %-15.2f%n",
                    course,
                    totalStudents,
                    averageMarks
            );
        }

        System.out.println("==============================================================");

        if (!found) {
            System.out.println("No students found.");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while calculating course statistics!");
        e.printStackTrace();
    }
}

// ================= TOP PERFORMER =================

public void showTopPerformer() {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "ORDER BY marks DESC " +
            "LIMIT 1";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        if (result.next()) {

            int rollNo = result.getInt("roll_no");
            String name = result.getString("name");
            String course = result.getString("course");
            double marks = result.getDouble("marks");

            System.out.println("\n========================================");
            System.out.println("             TOP PERFORMER");
            System.out.println("========================================");

            System.out.println("Roll No     : " + rollNo);
            System.out.println("Name        : " + name);
            System.out.println("Course      : " + course);
            System.out.println("Marks       : " + marks);

            System.out.println("========================================");

        } else {
            System.out.println("❌ No students found!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while finding top performer!");
        e.printStackTrace();
    }
}

// ================= PASS / FAIL REPORT =================

public void showPassFailReport() {

    String sql =
            "SELECT " +
            "COUNT(*) AS total, " +
            "SUM(CASE WHEN marks >= 50 THEN 1 ELSE 0 END) AS passed, " +
            "SUM(CASE WHEN marks < 50 THEN 1 ELSE 0 END) AS failed " +
            "FROM students";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        if (result.next()) {

            int total = result.getInt("total");
            int passed = result.getInt("passed");
            int failed = result.getInt("failed");

            double passPercentage = 0;
            double failPercentage = 0;

            if (total > 0) {
                passPercentage = (passed * 100.0) / total;
                failPercentage = (failed * 100.0) / total;
            }

            System.out.println("\n========================================");
            System.out.println("            PASS / FAIL REPORT");
            System.out.println("========================================");

            System.out.println("Total Students : " + total);
            System.out.println("Passed         : " + passed);
            System.out.println("Failed         : " + failed);

            System.out.printf(
                    "Pass Percentage: %.2f%%%n",
                    passPercentage
            );

            System.out.printf(
                    "Fail Percentage: %.2f%%%n",
                    failPercentage
            );

            System.out.println("========================================");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while generating pass/fail report!");
        e.printStackTrace();
    }
}

// ================= GRADE-WISE REPORT =================

public void showGradeReport() {

    String sql =
            "SELECT " +
            "SUM(CASE WHEN marks >= 90 THEN 1 ELSE 0 END) AS a_plus, " +
            "SUM(CASE WHEN marks >= 80 AND marks < 90 THEN 1 ELSE 0 END) AS a, " +
            "SUM(CASE WHEN marks >= 70 AND marks < 80 THEN 1 ELSE 0 END) AS b, " +
            "SUM(CASE WHEN marks >= 60 AND marks < 70 THEN 1 ELSE 0 END) AS c, " +
            "SUM(CASE WHEN marks >= 50 AND marks < 60 THEN 1 ELSE 0 END) AS d, " +
            "SUM(CASE WHEN marks < 50 THEN 1 ELSE 0 END) AS f " +
            "FROM students";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        if (result.next()) {

            System.out.println("\n========================================");
            System.out.println("             GRADE-WISE REPORT");
            System.out.println("========================================");

            System.out.println("A+ Grade : " + result.getInt("a_plus"));
            System.out.println("A Grade  : " + result.getInt("a"));
            System.out.println("B Grade  : " + result.getInt("b"));
            System.out.println("C Grade  : " + result.getInt("c"));
            System.out.println("D Grade  : " + result.getInt("d"));
            System.out.println("F Grade  : " + result.getInt("f"));

            System.out.println("========================================");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while generating grade report!");
        e.printStackTrace();
    }
}

// ================= FAILED STUDENTS =================

public void showFailedStudents() {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "WHERE marks < 50 " +
            "ORDER BY marks ASC";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        boolean found = false;

        System.out.println("\n==============================================================");
        System.out.println("                  FAILED STUDENTS");
        System.out.println("==============================================================");

        System.out.printf(
                "%-10s %-20s %-20s %-10s%n",
                "Roll No", "Name", "Course", "Marks"
        );

        System.out.println("--------------------------------------------------------------");

        while (result.next()) {

            found = true;

            System.out.printf(
                    "%-10d %-20s %-20s %-10.2f%n",
                    result.getInt("roll_no"),
                    result.getString("name"),
                    result.getString("course"),
                    result.getDouble("marks")
            );
        }

        System.out.println("==============================================================");

        if (!found) {
            System.out.println("✅ No failed students found!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while finding failed students!");
        e.printStackTrace();
    }
}

// ================= TOP 3 STUDENTS =================

public void showTopThreeStudents() {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "ORDER BY marks DESC " +
            "LIMIT 3";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        boolean found = false;
        int rank = 1;

        System.out.println("\n================================================================");
        System.out.println("                     TOP 3 STUDENTS");
        System.out.println("================================================================");

        System.out.printf(
                "%-8s %-10s %-20s %-20s %-10s%n",
                "Rank", "Roll No", "Name", "Course", "Marks"
        );

        System.out.println("----------------------------------------------------------------");

        while (result.next()) {

            found = true;

            System.out.printf(
                    "%-8d %-10d %-20s %-20s %-10.2f%n",
                    rank++,
                    result.getInt("roll_no"),
                    result.getString("name"),
                    result.getString("course"),
                    result.getDouble("marks")
            );
        }

        System.out.println("================================================================");

        if (!found) {
            System.out.println("❌ No students found!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while finding top students!");
        e.printStackTrace();
    }
}

// ================= MARKS RANGE SEARCH =================

public void searchStudentsByMarks(double minMarks, double maxMarks) {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "WHERE marks BETWEEN ? AND ? " +
            "ORDER BY marks DESC";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setDouble(1, minMarks);
        statement.setDouble(2, maxMarks);

        ResultSet result = statement.executeQuery();

        boolean found = false;

        System.out.println("\n==============================================================");
        System.out.println("                    MARKS RANGE RESULTS");
        System.out.println("==============================================================");

        System.out.printf(
                "%-10s %-20s %-20s %-10s%n",
                "Roll No", "Name", "Course", "Marks"
        );

        System.out.println("--------------------------------------------------------------");

        while (result.next()) {

            found = true;

            System.out.printf(
                    "%-10d %-20s %-20s %-10.2f%n",
                    result.getInt("roll_no"),
                    result.getString("name"),
                    result.getString("course"),
                    result.getDouble("marks")
            );
        }

        System.out.println("==============================================================");

        if (!found) {
            System.out.println("❌ No students found in this marks range!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while searching marks range!");
        e.printStackTrace();
    }
}

// ================= LOWEST PERFORMER =================

public void showLowestPerformer() {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "ORDER BY marks ASC " +
            "LIMIT 1";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        if (result.next()) {

            System.out.println("\n========================================");
            System.out.println("            LOWEST PERFORMER");
            System.out.println("========================================");

            System.out.println(
                    "Roll No     : " + result.getInt("roll_no")
            );

            System.out.println(
                    "Name        : " + result.getString("name")
            );

            System.out.println(
                    "Course      : " + result.getString("course")
            );

            System.out.println(
                    "Marks       : " + result.getDouble("marks")
            );

            System.out.println("========================================");

        } else {

            System.out.println("❌ No students found!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while finding lowest performer!");
        e.printStackTrace();
    }
}

// ================= COURSE STUDENT COUNT =================

public void showCourseStudentCount() {

    String sql =
            "SELECT course, COUNT(*) AS total_students " +
            "FROM students " +
            "GROUP BY course " +
            "ORDER BY total_students DESC";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        boolean found = false;

        System.out.println("\n==============================================");
        System.out.println("           COURSE STUDENT COUNT");
        System.out.println("==============================================");

        System.out.printf(
                "%-30s %-15s%n",
                "Course", "Students"
        );

        System.out.println("----------------------------------------------");

        while (result.next()) {

            found = true;

            System.out.printf(
                    "%-30s %-15d%n",
                    result.getString("course"),
                    result.getInt("total_students")
            );
        }

        System.out.println("==============================================");

        if (!found) {
            System.out.println("❌ No students found!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while calculating course count!");
        e.printStackTrace();
    }
}

// ================= COURSE PERFORMANCE =================

public void showCoursePerformance() {

    String sql =
            "SELECT course, " +
            "COUNT(*) AS total_students, " +
            "AVG(marks) AS average_marks, " +
            "MAX(marks) AS highest_marks, " +
            "MIN(marks) AS lowest_marks " +
            "FROM students " +
            "GROUP BY course " +
            "ORDER BY average_marks DESC";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        boolean found = false;

        System.out.println("\n======================================================================");
        System.out.println("                     COURSE PERFORMANCE");
        System.out.println("======================================================================");

        System.out.printf(
                "%-22s %-10s %-12s %-12s %-12s%n",
                "Course",
                "Students",
                "Average",
                "Highest",
                "Lowest"
        );

        System.out.println("----------------------------------------------------------------------");

        while (result.next()) {

            found = true;

            System.out.printf(
                    "%-22s %-10d %-12.2f %-12.2f %-12.2f%n",
                    result.getString("course"),
                    result.getInt("total_students"),
                    result.getDouble("average_marks"),
                    result.getDouble("highest_marks"),
                    result.getDouble("lowest_marks")
            );
        }

        System.out.println("======================================================================");

        if (!found) {
            System.out.println("❌ No course data found!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while calculating course performance!");
        e.printStackTrace();
    }
}

// ================= DASHBOARD =================

public void showDashboard() {

    String sql =
            "SELECT COUNT(*) AS total, " +
            "AVG(marks) AS average, " +
            "SUM(CASE WHEN marks >= 50 THEN 1 ELSE 0 END) AS passed, " +
            "SUM(CASE WHEN marks < 50 THEN 1 ELSE 0 END) AS failed, " +
            "MAX(marks) AS highest " +
            "FROM students";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        if (result.next()) {

            int total = result.getInt("total");
            double average = result.getDouble("average");
            int passed = result.getInt("passed");
            int failed = result.getInt("failed");
            double highest = result.getDouble("highest");

            System.out.println("\n==============================================");
            System.out.println("              STUDENT DASHBOARD");
            System.out.println("==============================================");

            System.out.println("Total Students    : " + total);
            System.out.printf("Average Marks     : %.2f%n", average);
            System.out.println("Passed Students   : " + passed);
            System.out.println("Failed Students   : " + failed);
            System.out.printf("Highest Marks     : %.2f%n", highest);

            System.out.println("==============================================");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while loading dashboard!");
        e.printStackTrace();
    }
}

// ================= CSV EXPORT =================

public void exportStudentsToCSV() {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "ORDER BY roll_no";

    String fileName = "students_report.csv";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery();
         FileWriter writer = new FileWriter(fileName)) {

        writer.write("Roll No,Name,Course,Marks,Grade\n");

        while (result.next()) {

            int rollNo = result.getInt("roll_no");
            String name = result.getString("name");
            String course = result.getString("course");
            double marks = result.getDouble("marks");

            String grade;

            if (marks >= 90) {
                grade = "A+";
            } else if (marks >= 80) {
                grade = "A";
            } else if (marks >= 70) {
                grade = "B";
            } else if (marks >= 60) {
                grade = "C";
            } else if (marks >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }

            writer.write(
                    rollNo + "," +
                    "\"" + name + "\"," +
                    "\"" + course + "\"," +
                    marks + "," +
                    grade + "\n"
            );
        }

        System.out.println("\n✅ Student data exported successfully!");
        System.out.println("File: " + fileName);

    } catch (SQLException | IOException e) {

        System.out.println("❌ Error while exporting CSV!");
        e.printStackTrace();
    }
}

// ================= DATABASE BACKUP =================

public void backupStudentData() {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "ORDER BY roll_no";

    String fileName = "student_database_backup.sql";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery();
         FileWriter writer = new FileWriter(fileName)) {

        writer.write("-- Student Management System Database Backup\n");
        writer.write("-- Students Table Data\n\n");

        writer.write("USE student_management;\n\n");

        while (result.next()) {

            int rollNo = result.getInt("roll_no");
            String name = result.getString("name");
            String course = result.getString("course");
            double marks = result.getDouble("marks");

            name = name.replace("'", "''");
            course = course.replace("'", "''");

            writer.write(
                    "INSERT INTO students " +
                    "(roll_no, name, course, marks) VALUES (" +
                    rollNo + ", '" +
                    name + "', '" +
                    course + "', " +
                    marks + ");\n"
            );
        }

        System.out.println("\n✅ Database backup created successfully!");
        System.out.println("File: " + fileName);

    } catch (SQLException | IOException e) {

        System.out.println("❌ Error while creating database backup!");
        e.printStackTrace();
    }
}

// ================= STUDENT FULL REPORT =================

public void showFullStudentReport(int rollNo) {

    String sql =
            "SELECT roll_no, name, course, marks " +
            "FROM students " +
            "WHERE roll_no = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setInt(1, rollNo);

        ResultSet result = statement.executeQuery();

        if (result.next()) {

            String name = result.getString("name");
            String course = result.getString("course");
            double marks = result.getDouble("marks");

            String grade;

            if (marks >= 90) {
                grade = "A+";
            } else if (marks >= 80) {
                grade = "A";
            } else if (marks >= 70) {
                grade = "B";
            } else if (marks >= 60) {
                grade = "C";
            } else if (marks >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }

            System.out.println("\n========================================");
            System.out.println("          STUDENT FULL REPORT");
            System.out.println("========================================");

            System.out.println("Roll No      : " + rollNo);
            System.out.println("Name         : " + name);
            System.out.println("Course       : " + course);
            System.out.printf("Marks        : %.2f%n", marks);
            System.out.printf("Percentage   : %.2f%%%n", marks);
            System.out.println("Grade        : " + grade);

            if (marks >= 50) {
                System.out.println("Result       : PASS");
            } else {
                System.out.println("Result       : FAIL");
            }

            System.out.println("========================================");

        } else {

            System.out.println("❌ Student Not Found!");
        }

    } catch (SQLException e) {

        System.out.println("❌ Error while generating student report!");
        e.printStackTrace();
    }
}


}