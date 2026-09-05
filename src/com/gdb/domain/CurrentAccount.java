package com.gdb.domain;

import com.gdb.exceptions.*;

public class CurrentAccount extends BankAccount {
    public static final double MINIMUM_BALANCE = 1000.0;
    public static final double OVERDRAFT_LIMIT = 5000.0;
    public static final double INTEREST_RATE = 0.0;

    public CurrentAccount(String accountNumber, String name, int age, double balance, String status, String pin)
            throws InvalidAmountException, MinimumBalanceViolationException {
        super(accountNumber, name, age, balance, "CURRENT", status, pin);
        if (balance < MINIMUM_BALANCE) {
            throw new MinimumBalanceViolationException("Initial deposit violates minimum balance of Rs " + MINIMUM_BALANCE);
        }
    }

    public CurrentAccount(String accountNumber, String name, int age, double balance, String status)
            throws InvalidAmountException, MinimumBalanceViolationException {
        this(accountNumber, name, age, balance, status, "0000");
    }

    // ============================================================
    // 📝 STEP 3: Implement Abstract Methods for CurrentAccount
    // ============================================================
    // TODO: STEP 3.1 - return MINIMUM_BALANCE
    @Override
    public double getMinimumBalance() { return 0.0; }

    // TODO: STEP 3.2 - return "CURRENT"
    @Override
    public String getAccountType() { return ""; }

    // TODO: STEP 3.3 - return INTEREST_RATE (0.0)
    @Override
    public double getInterestRate() { return 0.0; }

    // TODO: STEP 3.4 - return (this.balance + OVERDRAFT_LIMIT) >= amount
    @Override
    public boolean canWithdraw(double amount) { return false; }

    @Override
    public void applyMonthlyInterest() {}
}
