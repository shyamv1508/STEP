package com.gdb.domain;

import com.gdb.exceptions.*;

public class SavingsAccount extends BankAccount {
    public static final double MINIMUM_BALANCE = 500.0;
    public static final double INTEREST_RATE = 4.0;

    public SavingsAccount(String accountNumber, String name, int age, double balance, String status, String pin)
            throws InvalidAmountException, MinimumBalanceViolationException {
        super(accountNumber, name, age, balance, "SAVINGS", status, pin);
        if (balance < MINIMUM_BALANCE) {
            throw new MinimumBalanceViolationException("Initial deposit violates minimum balance of Rs " + MINIMUM_BALANCE);
        }
    }

    public SavingsAccount(String accountNumber, String name, int age, double balance, String status)
            throws InvalidAmountException, MinimumBalanceViolationException {
        this(accountNumber, name, age, balance, status, "0000");
    }

    // ============================================================
    // 📝 STEP 2: Implement Abstract Methods for SavingsAccount
    // ============================================================
    // TODO: STEP 2.1 - return MINIMUM_BALANCE
    @Override
    public double getMinimumBalance() { return 0.0; }

    // TODO: STEP 2.2 - return "SAVINGS"
    @Override
    public String getAccountType() { return ""; }

    // TODO: STEP 2.3 - return INTEREST_RATE
    @Override
    public double getInterestRate() { return 0.0; }

    // TODO: STEP 2.4 - return (this.balance - amount) >= MINIMUM_BALANCE
    @Override
    public boolean canWithdraw(double amount) { return false; }

    // TODO: STEP 2.5 - calculate monthly interest and deposit
    @Override
    public void applyMonthlyInterest() throws InactiveAccountException, InvalidAmountException {
    }
}
