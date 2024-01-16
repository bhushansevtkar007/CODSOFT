import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }
}

public class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void processOption(int option) {
        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case 1:

                System.out.println("Current Balance: $" + userAccount.getBalance());
                break;

            case 2:

                System.out.print("Enter deposit amount: $");
                double depositAmount = scanner.nextDouble();
                userAccount.deposit(depositAmount);
                System.out.println("Deposit successful. New Balance: $" + userAccount.getBalance());
                break;

            case 3:

                System.out.print("Enter withdrawal amount: $");
                double withdrawAmount = scanner.nextDouble();
                if (userAccount.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawal successful. New Balance: $" + userAccount.getBalance());
                }
                break;

            case 4:

                System.out.println("Exiting. Thank you!");
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankAccount userAccount = new BankAccount(1000.0);

        ATM atm = new ATM(userAccount);

        int option;

        do {

            atm.displayMenu();

            System.out.print("Select an option (1-4): ");
            option = scanner.nextInt();

            atm.processOption(option);

        } while (option != 4);

        scanner.close();
    }
}
