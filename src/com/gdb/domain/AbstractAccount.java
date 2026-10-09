package com.gdb.domain;

import com.gdb.exceptions.*;
import java.time.LocalDate;

// Base class shared by every account type.
public abstract class AbstractAccount implements IAccount {
    protected String accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected String pin;

    private static final double DAILY_TRANSFER_LIMIT = 50000.0;
    private double dailyTransferTotal = 0.0;
    private LocalDate transferDate = LocalDate.now();

    public AbstractAccount(String accountNumber, String name, int age, double balance, String accountType, String status, String pin) {
        if (age < 18) throw new IllegalArgumentException("Customer age must be 18 or above");
        if (balance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        if (pin == null || !pin.matches("\\d{4}")) throw new IllegalArgumentException("PIN must be 4 digits");
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.accountType = accountType;
        this.status = status;
        this.pin = pin;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin != null && this.pin.equals(enteredPin);
    }

    public boolean changePin(String oldPin, String newPin) {
        if (!validatePin(oldPin)) return false;
        if (newPin == null || !newPin.matches("\\d{4}")) return false;
        this.pin = newPin;
        return true;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive");
        this.balance += amount;
    }

    public void withdraw(double amount, String enteredPin) throws AccountException {
        if (!validatePin(enteredPin)) throw new InvalidPinException("Invalid PIN entered");
        if (!isActive()) throw new InactiveAccountException("Account is not active");
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive");
        processDebit(amount);
    }

    public abstract void processDebit(double amount) throws AccountException;

    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    public boolean verifyPin(int enteredPin) {
        return validatePin(String.valueOf(enteredPin));
    }

    // Savings accounts must retain their configured minimum balance.
    public boolean canWithdraw(double amount) {
        if (amount <= 0) return false;
        double minimumBalance = this instanceof SavingsAccount
                ? ((SavingsAccount) this).getMinBalance() : 0.0;
        return balance - amount >= minimumBalance;
    }

    public void resetDailyTransferIfNeeded() {
        LocalDate today = LocalDate.now();
        if (!today.equals(transferDate)) {
            transferDate = today;
            dailyTransferTotal = 0.0;
        }
    }

    public double getDailyTransferLimit() {
        return DAILY_TRANSFER_LIMIT;
    }

    public boolean canTransfer(double amount) {
        resetDailyTransferIfNeeded();
        return amount > 0 && dailyTransferTotal + amount <= getDailyTransferLimit();
    }

    public double getDailyTransferTotal() {
        resetDailyTransferIfNeeded();
        return dailyTransferTotal;
    }

    public double getRemainingDailyTransferLimit() {
        resetDailyTransferIfNeeded();
        return Math.max(0.0, getDailyTransferLimit() - dailyTransferTotal);
    }

    public void updateDailyTransferTotal(double amount) {
        resetDailyTransferIfNeeded();
        dailyTransferTotal += amount;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Balance: Rs " + balance);
        System.out.println("Account Type: " + accountType);
        System.out.println("Status: " + status);
    }

    public String getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public String getStatus() { return status; }
}