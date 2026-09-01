import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * WEEK 4 CAPSTONE PROJECT - Option 2
 * ATM Simulation
 * ---------------------------------------------
 * Features: PIN Login, Withdraw, Deposit, Check Balance, Transaction History
 * Data (balance + transaction history) is persisted to local files so it
 * survives between runs, without needing a database server.
 */

// ---------- MODEL ----------
class Transaction {
    String type;
    double amount;
    double balanceAfter;

    Transaction(String type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    String toFileFormat() {
        return type + "," + amount + "," + balanceAfter;
    }

    static Transaction fromFileFormat(String line) {
        String[] parts = line.split(",");
        return new Transaction(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
    }

    void display() {
        System.out.println(type + " : " + amount + " | Balance After: " + balanceAfter);
    }
}

// ---------- ACCOUNT (OOP + STATE) ----------
class Account {
    private String pin;
    private double balance;
    private ArrayList<Transaction> history;

    private static final String BALANCE_FILE = "atm_balance.txt";
    private static final String HISTORY_FILE = "atm_history.txt";
    private static final String DEFAULT_PIN = "1234";

    Account() {
        history = new ArrayList<>();
        loadAccount();
    }

    private void loadAccount() {
        File balFile = new File(BALANCE_FILE);
        if (balFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(balFile))) {
                String[] data = reader.readLine().split(",");
                pin = data[0];
                balance = Double.parseDouble(data[1]);
            } catch (IOException e) {
                System.out.println("Error loading account: " + e.getMessage());
                setupDefaults();
            }
        } else {
            setupDefaults();
        }

        File histFile = new File(HISTORY_FILE);
        if (histFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(histFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        history.add(Transaction.fromFileFormat(line));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading history: " + e.getMessage());
            }
        }
    }

    private void setupDefaults() {
        pin = DEFAULT_PIN;
        balance = 0.0;
        saveBalance();
    }

    private void saveBalance() {
        try (FileWriter writer = new FileWriter(BALANCE_FILE)) {
            writer.write(pin + "," + balance);
        } catch (IOException e) {
            System.out.println("Error saving balance: " + e.getMessage());
        }
    }

    private void saveHistory() {
        try (FileWriter writer = new FileWriter(HISTORY_FILE)) {
            for (Transaction t : history) {
                writer.write(t.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }

    boolean checkPin(String inputPin) {
        return pin.equals(inputPin);
    }

    void deposit(double amount) {
        balance += amount;
        history.add(new Transaction("Deposit", amount, balance));
        saveBalance();
        saveHistory();
        System.out.println("Deposit successful! New Balance: " + balance);
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            history.add(new Transaction("Withdraw", amount, balance));
            saveBalance();
            saveHistory();
            System.out.println("Withdrawal successful! New Balance: " + balance);
        }
    }

    void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n----- Transaction History -----");
        for (Transaction t : history) {
            t.display();
        }
    }
}

// ---------- MAIN / VIEW (Menu) ----------
public class ATMSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account = new Account();

        System.out.println("===== Welcome to the ATM =====");
        System.out.print("Enter PIN (default is 1234 for first-time use): ");
        String enteredPin = sc.nextLine();

        int attempts = 3;
        while (!account.checkPin(enteredPin) && attempts > 1) {
            attempts--;
            System.out.print("Incorrect PIN. Attempts remaining: " + attempts + ". Try again: ");
            enteredPin = sc.nextLine();
        }

        if (!account.checkPin(enteredPin)) {
            System.out.println("Too many incorrect attempts. Card blocked. Exiting...");
            sc.close();
            return;
        }

        System.out.println("Login successful!\n");

        int choice;
        do {
            System.out.println("===== ATM Menu =====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmt = Double.parseDouble(sc.nextLine());
                        if (withdrawAmt <= 0) {
                            System.out.println("Amount must be positive.");
                        } else {
                            account.withdraw(withdrawAmt);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount!");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmt = Double.parseDouble(sc.nextLine());
                        if (depositAmt <= 0) {
                            System.out.println("Amount must be positive.");
                        } else {
                            account.deposit(depositAmt);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount!");
                    }
                    break;

                case 3:
                    account.checkBalance();
                    break;

                case 4:
                    account.showHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-5.");
            }

        } while (choice != 5);

        sc.close();
    }
}
