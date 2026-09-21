public class Student {

    int rollNo;
    String name;
    String course;
    double marks;

    Student(int rollNo, String name, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    double getPercentage() {
        return marks;
    }

    String getGrade() {

        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

   
    void displayStudent() {

    System.out.println("\n========================================");
    System.out.println("           STUDENT DETAILS");
    System.out.println("========================================");

    System.out.println("Roll No     : " + rollNo);
    System.out.println("Name        : " + name);
    System.out.println("Course      : " + course);
    System.out.println("Marks       : " + marks);
    System.out.println("Percentage  : " + getPercentage() + "%");
    System.out.println("Grade       : " + getGrade());

    System.out.println("========================================");
    }
    
}