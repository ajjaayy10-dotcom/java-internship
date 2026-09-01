import java.util.Scanner;

// Custom exception class
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Practice4_CustomAgeException {

    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative.");
        } else if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18.");
        } else if (age > 120) {
            throw new InvalidAgeException("Age value seems unrealistic.");
        } else {
            System.out.println("Age is valid: " + age);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        try {
            validateAge(age);
        } catch (InvalidAgeException e) {
            System.out.println("Invalid Age: " + e.getMessage());
        }

        sc.close();
    }
}
