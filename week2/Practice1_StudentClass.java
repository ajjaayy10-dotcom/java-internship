public class Practice1_StudentClass {

    static class Student {
        String name;
        double marks;
        int rollNumber;

        Student(String name, double marks, int rollNumber) {
            this.name = name;
            this.marks = marks;
            this.rollNumber = rollNumber;
        }

        void display() {
            System.out.println("Name: " + name + ", Marks: " + marks + ", Roll No: " + rollNumber);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ajay Kumar", 91.5, 21);
        s1.display();
    }
}
