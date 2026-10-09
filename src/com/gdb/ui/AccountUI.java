package com.gdb.ui;

import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;
import com.gdb.command.TransactionCommand;
import com.gdb.service.AccountService;

import java.util.*;

public class AccountUI {
    private final AccountService service;
    private final Scanner scanner;

    public AccountUI(AccountService service) {
        this.service = Objects.requireNonNull(service, "AccountService cannot be null");
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMainMenu();
            int choice;
            try {
                choice = readInt("Enter your choice: ");
                switch (choice) {
                    case 1:
                        handleOpenAccount();
                        break;
                    case 2:
                        handleDeposit();
                        break;
                    case 3:
                        handleWithdraw();
                        break;
                    case 4:
                        handleTransfer();
                        break;
                    case 5:
                        handleCloseAccount();
                        break;
                    case 6:
                        handleViewAccount();
                        break;
                    case 7:
                        handleViewTransactions();
                        break;
                    case 8:
                        System.out.println("Thank you! Goodbye.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1-8.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                if (!scanner.hasNextLine()) {
                    return;
                }
            }
            System.out.println();
        }
    }

    private void displayMainMenu() {
        System.out.println("========================================");
        System.out.println("       GLOBAL DIGITAL BANK");
        System.out.println("========================================");
        System.out.println("1. Open Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Close Account");
        System.out.println("6. View Account Details");
        System.out.println("7. View Transaction History");
        System.out.println("8. Exit");
        System.out.println("========================================");
    }

    private void handleOpenAccount() throws Exception {
        System.out.println("--- Open Account ---");
        String type = readString("Account Type (Savings/Current/FixedDeposit/Salary): ");
        String name = readString("Name: ");
        int age = readInt("Age: ");
        double amount = readDouble("Initial Balance: ");

        IAccount account = service.openAccount(type, name, age, amount);
        System.out.println("SUCCESS: " + account.getAccountInfo());

        int pin = readInt("Set 4-digit PIN: ");
        account.setPin(pin);
        System.out.println("PIN set successfully.");
    }

    private void handleDeposit() throws Exception {
        System.out.println("--- Deposit ---");
        int accountNumber = readInt("Account Number: ");
        double amount = readDouble("Amount to deposit: ");
        Transaction transaction = service.deposit(accountNumber, amount);
        System.out.println("SUCCESS: " + transaction);
        System.out.println("New balance: Rs. " + transaction.getBalanceAfter());
    }

    private void handleWithdraw() throws Exception {
        System.out.println("--- Withdraw ---");
        int accountNumber = readInt("Account Number: ");
        double amount = readDouble("Amount to withdraw: ");
        int pin = readInt("PIN: ");
        Transaction transaction = service.withdraw(accountNumber, amount, pin);
        System.out.println("SUCCESS: " + transaction);
        System.out.println("New balance: Rs. " + transaction.getBalanceAfter());
    }

    private void handleTransfer() throws Exception {
        System.out.println("--- Transfer ---");
        int fromAccount = readInt("From Account: ");
        int toAccount = readInt("To Account: ");
        double amount = readDouble("Amount: ");
        int pin = readInt("PIN: ");
        Transaction transaction = service.transfer(fromAccount, toAccount, amount, pin);
        System.out.println("SUCCESS: " + transaction);
    }

    private void handleCloseAccount() throws Exception {
        System.out.println("--- Close Account ---");
        int accountNumber = readInt("Account Number: ");
        int pin = readInt("PIN: ");
        service.closeAccount(accountNumber, pin);
        System.out.println("SUCCESS: Account #" + accountNumber + " has been closed.");
    }

    private void handleViewAccount() {
        System.out.println("--- View Account ---");
        int accountNumber = readInt("Account Number: ");
        IAccount account = service.getAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found: " + accountNumber);
        } else {
            System.out.println(account.getAccountInfo());
        }
    }

    private void handleViewTransactions() {
        System.out.println("--- Transaction History ---");
        List<TransactionCommand> history = service.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions logged.");
            return;
        }

        for (int i = 0; i < history.size(); i++) {
            Transaction transaction = history.get(i).getTransaction();
            System.out.println("[" + (i + 1) + "] "
                    + (transaction != null ? transaction : "Transaction completed (details unavailable)"));
        }
    }

    private int readInt(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                double number = Double.parseDouble(value);
                if (!Double.isFinite(number)) {
                    System.out.println("Invalid input. Please enter a finite number.");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            throw new NoSuchElementException("No more input available.");
        }
        return scanner.nextLine().trim();
    }
}
