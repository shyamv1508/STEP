package com.gdb.domain;

import com.gdb.exceptions.*;

public class FixedDepositAccount extends BankAccount {
    public static final double MINIMUM_BALANCE = 10000.0;
    public static final double INTEREST_RATE = 6.5;
    public static final double PREMATURE_PENALTY_RATE = 1.0;

    private int tenureMonths;
    private boolean isMatured;

    public FixedDepositAccount(String accountNumber, String name, int age, double balance, String status, String pin, int tenureMonths)
            throws InvalidAmountException, MinimumBalanceViolationException {
        super(accountNumber, name, age, balance, "FIXED_DEPOSIT", status, pin);
        if (balance < MINIMUM_BALANCE) {
            throw new MinimumBalanceViolationException("Minimum balance for Fixed Deposit is Rs " + MINIMUM_BALANCE);
        }
        this.tenureMonths = tenureMonths;
        this.isMatured = false;
    }

    public FixedDepositAccount(String accountNumber, String name, int age, double balance, String status, int tenureMonths)
            throws InvalidAmountException, MinimumBalanceViolationException {
        this(accountNumber, name, age, balance, status, "0000", tenureMonths);
    }

    // ============================================================
    // 📝 STEP 4: Implement Abstract Methods for FixedDepositAccount
    // ============================================================
    // TODO: STEP 4.1 - return MINIMUM_BALANCE
    @Override
    public double getMinimumBalance() { return 0.0; }

    // TODO: STEP 4.2 - return "FIXED_DEPOSIT"
    @Override
    public String getAccountType() { return ""; }

    // TODO: STEP 4.3 - return isMatured ? INTEREST_RATE : (INTEREST_RATE - PREMATURE_PENALTY_RATE)
    @Override
    public double getInterestRate() { return 0.0; }

    // TODO: STEP 4.4 - return amount <= this.balance
    @Override
    public boolean canWithdraw(double amount) { return false; }

    // TODO: STEP 4.5 - calculate monthly interest and deposit
    @Override
    public void applyMonthlyInterest() throws InactiveAccountException, InvalidAmountException {
    }

    public void matureDeposit() { this.isMatured = true; }
    public boolean isMatured() { return isMatured; }
    public int getTenureMonths() { return tenureMonths; }
}
