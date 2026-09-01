import java.util.Scanner;

public class BankManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 0.0;
        int choice;

        System.out.println("===== Welcome to Bank Management Console App =====");

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    if (depositAmount <= 0) {
                        System.out.println("Deposit amount must be positive.");
                    } else {
                        balance += depositAmount;
                        System.out.println("Deposit successful! Current Balance: " + balance);
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    if (withdrawAmount <= 0) {
                        System.out.println("Withdrawal amount must be positive.");
                    } else if (withdrawAmount > balance) {
                        System.out.println("Insufficient balance!");
                    } else {
                        balance -= withdrawAmount;
                        System.out.println("Withdrawal successful! Current Balance: " + balance);
                    }
                    break;

                case 3:
                    System.out.println("Current Balance: " + balance);
                    break;

                case 4:
                    System.out.println("Thank you for using our Bank App. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1-4.");
            }

        } while (choice != 4);

        sc.close();
    }
}
