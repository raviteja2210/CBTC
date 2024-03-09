import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void transfer(Account recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}

public class Banky {
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Exiting BankY. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        accounts.put(accountNumber, new Account(accountNumber));
        System.out.println("Account created successfully.");
    }

    private static void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            accounts.get(accountNumber).deposit(amount);
            System.out.println("Deposit successful. New balance: " + accounts.get(accountNumber).getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (accounts.get(accountNumber).withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: " + accounts.get(accountNumber).getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sender account number: ");
        String senderAccountNumber = scanner.nextLine();
        System.out.print("Enter recipient account number: ");
        String recipientAccountNumber = scanner.nextLine();

        if (accounts.containsKey(senderAccountNumber) && accounts.containsKey(recipientAccountNumber)) {
            System.out.print("Enter transfer amount: ");
            double amount = scanner.nextDouble();
            accounts.get(senderAccountNumber).transfer(accounts.get(recipientAccountNumber), amount);
        } else {
            System.out.println("One or both accounts not found.");
        }
    }
}
