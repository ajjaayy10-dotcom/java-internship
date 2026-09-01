import java.util.Scanner;

public class Practice3_MultipleCatchBlocks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {10, 20, 30};

        try {
            System.out.print("Enter an array index (0-2): ");
            int index = Integer.parseInt(sc.nextLine());

            System.out.print("Enter a divisor: ");
            int divisor = Integer.parseInt(sc.nextLine());

            int result = arr[index] / divisor;
            System.out.println("Result: " + result);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index is out of bounds!");
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric input!");
        } finally {
            System.out.println("Program execution finished.");
        }

        sc.close();
    }
}
