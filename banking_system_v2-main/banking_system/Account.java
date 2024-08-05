package banking_system;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

class Account<T> {
    private Integer accountNumber;
    private double balance;
    private T accountHolder;

    public Account(Integer accountNumber, double balance, T accountHolder) {
        if (accountNumber == null || accountNumber <= 0) {
            throw new IllegalArgumentException("Account number cannot be null or less than or equal to zero");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (accountHolder == null) {
            throw new IllegalArgumentException("Account holder cannot be null");
        }
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public T getAccountHolder() {
        return accountHolder;
    }

    public void deposit(double amount) {
        balance += amount;
        try {
            printReceipt("deposit", amount);
        } catch (IOException e) {
            System.out.println("Error printing receipt: " + e.getMessage());
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            try {
                printReceipt("withdrawal", amount);
            } catch (IOException e) {
                System.out.println("Error printing receipt: " + e.getMessage());
            }
        } else {
            System.out.println("Insufficient balance");
        }
    }

    private void printReceipt(String transactionType, double amount) throws IOException {
        String fileName = "receipt_" + accountNumber + "_" + transactionType + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Receipt - " + LocalDateTime.now());
            writer.newLine();
            writer.write("Account Number: " + accountNumber);
            writer.newLine();
            writer.write("Transaction Type: " + transactionType);
            writer.newLine();
            writer.write("Amount: $" + amount);
            writer.newLine();
            writer.write("New Balance: $" + balance);
            writer.newLine();
            writer.write("----------------------------");
            writer.newLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", accountHolder=" + accountHolder +
                '}';
    }
}
