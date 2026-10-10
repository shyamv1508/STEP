package com.gdb.command;

import com.gdb.domain.Account;
import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;

public class WithdrawCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    // ============================================================
    // 📝 STEP 3: Declare Fields
    //
    // INSTRUCTIONS:
    private IAccount account;
    private double amount;
    private int pin;
    private Transaction transaction;
    // ============================================================
    // TODO: declare account, amount, pin, and transaction fields

    // ============================================================
    // 📝 STEP 4: Write Constructor
    //
    // INSTRUCTIONS:
    //   Accept (IAccount account, double amount, int pin); store all.
    // ============================================================
    // TODO: implement constructor
    public WithdrawCommand(IAccount account, double amount, int pin) {
        // TODO: Step 4 - implement constructor
        this.account = account;
        this.amount = amount;
        this.pin = pin;
    }

    // ============================================================
    // 📝 STEP 5: Implement execute()
    //
    // INSTRUCTIONS:
    //   1. Call account.withdrawWithTransaction(amount, pin).
    //   2. Store returned Transaction in this.transaction.
    // ============================================================
    // TODO: implement execute() method
    @Override
    public void execute() throws Exception {
        // TODO: Step 5 - implement execute()
        this.transaction = ((Account) account).withdrawWithTransaction(amount, pin);

    }

    // ============================================================
    // 📝 STEP 6: Implement getTransaction()
    //
    // INSTRUCTIONS:
    //   Return this.transaction.
    // ============================================================
    // TODO: return this.transaction
    @Override
    public Transaction getTransaction() {
        // TODO: Step 6 - return this.transaction
        return this.transaction;
    }
}
