package com.gdb.domain;

import com.gdb.exceptions.*;

/**
 * Abstract base class representing a Bank Account.
 */
public abstract class BankAccount {
    protected String accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected String pin;

    public BankAccount(String accountNumber, String name, int age, double balance, String accountType, String status, String pin)
            throws InvalidAmountException {
        if (balance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative: " + balance);
        }
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.accountType = accountType;
        this.status = status != null ? status : "ACTIVE";
        this.pin = pin != null ? pin : "0000";
    }

    public BankAccount(String accountNumber, String name, int age, double balance, String accountType, String status)
            throws InvalidAmountException {
        this(accountNumber, name, age, balance, accountType, status, "0000");
    }

    public void deposit(double amount) throws InactiveAccountException, InvalidAmountException {
        if (!"ACTIVE".equalsIgnoreCase(this.status)) {
            throw new InactiveAccountException("Account is not active.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws InactiveAccountException, InvalidAmountException, InsufficientBalanceException, MinimumBalanceViolationException {
        if (!"ACTIVE".equalsIgnoreCase(this.status)) {
            throw new InactiveAccountException("Account is not active.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (!canWithdraw(amount)) {
            throw new MinimumBalanceViolationException("Withdrawal violates account balance policy.");
        }
        if (this.balance < amount && !(this instanceof CurrentAccount)) {
            throw new InsufficientBalanceException("Insufficient funds.");
        }
        this.balance -= amount;
    }

    // ============================================================
    // 📝 STEP 1: Declare Abstract Methods
    // 
    // INSTRUCTIONS:
    //   1. public abstract double getMinimumBalance();
    //   2. public abstract String getAccountType();
    //   3. public abstract double getInterestRate();
    //   4. public abstract boolean canWithdraw(double amount);
    //   5. public abstract void applyMonthlyInterest() throws InactiveAccountException, InvalidAmountException;
    // ============================================================
    // TODO: STEP 1 - Declare 5 abstract methods
    public abstract double getMinimumBalance();
    public abstract String getAccountType();
    public abstract double getInterestRate();
    public abstract boolean canWithdraw(double amount);
    public abstract void applyMonthlyInterest() throws InactiveAccountException, InvalidAmountException;

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void displayAccountInfo() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + name);
        System.out.println("Age            : " + age);
        System.out.println("Account Type   : " + getAccountType());
        System.out.println("Balance        : Rs " + balance);
        System.out.println("Min Balance    : Rs " + getMinimumBalance());
        System.out.println("Interest Rate  : " + getInterestRate() + "%");
        System.out.println("Status         : " + status);
    }
}
