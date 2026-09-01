import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + department + "," + salary;
    }

    static Employee fromString(String line) {
        String[] parts = line.split(",");
        return new Employee(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Double.parseDouble(parts[3])
        );
    }

    void display() {
        System.out.println("ID: " + id + " | Name: " + name +
                " | Department: " + department + " | Salary: " + salary);
    }
}

public class EmployeeManagementSystem {
    static final String FILE_NAME = "employees.txt";
    static ArrayList<Employee> employeeList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();
        int choice;

        do {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Save & Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1-3.");
            }

        } while (choice != 3);

        sc.close();
    }

    static void addEmployee() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String dept = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative.");
            }

            employeeList.add(new Employee(id, name, dept, salary));
            System.out.println("Employee added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! ID and Salary must be numeric.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        System.out.println("\n----- Employee Records -----");
        for (Employee emp : employeeList) {
            emp.display();
        }
    }

    static void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Employee emp : employeeList) {
                writer.write(emp.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // No existing data yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    employeeList.add(Employee.fromString(line));
                }
            }
            System.out.println("Loaded " + employeeList.size() + " employee record(s) from file.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
