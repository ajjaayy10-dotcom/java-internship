public class Student {
    // Attributes
    private String name;
    private int rollNumber;
    private double marks;

    // Constructor
    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    // Method to display student info
    public void displayInfo() {
        System.out.println("----- Student Details -----");
        System.out.println("Name       : " + name);
        System.out.println("Roll No.   : " + rollNumber);
        System.out.println("Marks      : " + marks);
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ajay Kumar", 101, 89.5);
        Student s2 = new Student("Divyesh", 102, 76.0);

        s1.displayInfo();
        s2.displayInfo();
    }
}
