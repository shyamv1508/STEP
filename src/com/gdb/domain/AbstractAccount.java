package com.gdb.domain;
import com.gdb.exceptions.*;
public class AbstractAccount {
    protected String accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected String pin;


    public void deposit(double amount) throws InactiveAccountException, InvalidAmountException {
        if (!"ACTIVE".equalsIgnoreCase(this.status)) {
            throw new InactiveAccountException("Account is not active.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }
}
