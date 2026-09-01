import java.util.Scanner;

public class GradeEvaluation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter marks (out of 100): ");
        int marks = sc.nextInt();

        String grade;

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks entered!");
        } else {
            if (marks >= 90) {
                grade = "A";
            } else if (marks >= 75) {
                grade = "B";
            } else if (marks >= 40) {
                grade = "C";
            } else {
                grade = "Fail";
            }
            System.out.println("Marks: " + marks);
            System.out.println("Grade: " + grade);
        }

        sc.close();
    }
}
