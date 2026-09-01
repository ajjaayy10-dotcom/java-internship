import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * WEEK 4 CAPSTONE PROJECT
 * Student Management System
 * ---------------------------------------------
 * Combines: OOP (Model class), Collections (ArrayList),
 * File Handling (persistent storage), and a Menu-driven Controller.
 *
 * Note: Data is persisted to a local file (students.txt) instead of a
 * MySQL database, so the project runs immediately without any extra
 * setup (no DB server/driver needed). The same Student/StudentDAO
 * structure could be swapped to use JDBC if a database is required.
 */

// ---------- MODEL ----------
class Student {
    int id;
    String name;
    String course;
    double marks;

    Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    String toFileFormat() {
        return id + "," + name + "," + course + "," + marks;
    }

    static Student fromFileFormat(String line) {
        String[] parts = line.split(",");
        return new Student(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Double.parseDouble(parts[3])
        );
    }

    void display() {
        System.out.println("ID: " + id + " | Name: " + name +
                " | Course: " + course + " | Marks: " + marks);
    }
}

// ---------- DATA ACCESS (FILE HANDLING) ----------
class StudentFileHandler {
    private static final String FILE_NAME = "students.txt";

    static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(Student.fromFileFormat(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    static void saveStudents(ArrayList<Student> students) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Student s : students) {
                writer.write(s.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
}

// ---------- CONTROLLER / SERVICE ----------
class StudentManager {
    private ArrayList<Student> students;

    StudentManager() {
        students = StudentFileHandler.loadStudents();
        System.out.println("Loaded " + students.size() + " student record(s) from file.\n");
    }

    void addStudent(Student s) {
        students.add(s);
        StudentFileHandler.saveStudents(students);
        System.out.println("Student added successfully!");
    }

    void updateStudent(int id, String name, String course, double marks) {
        for (Student s : students) {
            if (s.id == id) {
                s.name = name;
                s.course = course;
                s.marks = marks;
                StudentFileHandler.saveStudents(students);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    void deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.id == id);
        if (removed) {
            StudentFileHandler.saveStudents(students);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n----- Student Records -----");
        for (Student s : students) {
            s.display();
        }
    }
}

// ---------- MAIN / VIEW (Menu) ----------
public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = Double.parseDouble(sc.nextLine());

                        manager.addStudent(new Student(id, name, course, marks));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! ID and Marks must be numeric.");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter ID of student to update: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter new Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter new Course: ");
                        String course = sc.nextLine();
                        System.out.print("Enter new Marks: ");
                        double marks = Double.parseDouble(sc.nextLine());

                        manager.updateStudent(id, name, course, marks);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! ID and Marks must be numeric.");
                    }
                    break;

                case 3:
                    System.out.print("Enter ID of student to delete: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        manager.deleteStudent(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! ID must be numeric.");
                    }
                    break;

                case 4:
                    manager.viewStudents();
                    break;

                case 5:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1-5.");
            }

        } while (choice != 5);

        sc.close();
    }
}
