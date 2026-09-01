public class Practice2_BankAccount {

    static class BankAccount {
        private double balance;

        BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount + " | New Balance: " + balance);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdraw amount must be positive.");
            } else if (amount > balance) {
                System.out.println("Insufficient balance.");
            } else {
                balance -= amount;
                System.out.println("Withdrew: " + amount + " | New Balance: " + balance);
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);
        account.deposit(500);
        account.withdraw(300);
        account.withdraw(5000); // should show insufficient balance
    }
}
