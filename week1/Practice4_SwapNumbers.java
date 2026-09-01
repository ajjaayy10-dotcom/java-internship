import java.util.Scanner;

public class Practice4_SwapNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        System.out.println("\nBefore Swap: a = " + a + ", b = " + b);

        // Swapping without a third variable using arithmetic operations
        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("After Swap: a = " + a + ", b = " + b);

        sc.close();
    }
}
