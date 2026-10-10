package com.gdb.command;

import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;
import com.gdb.service.TransferService;

public class TransferCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    // ============================================================
    // 📝 STEP 3: Declare Fields
    //
    // INSTRUCTIONS:
    private IAccount fromAccount;
    private IAccount toAccount;
    private double amount;
    private int pin;
    private Transaction transaction;
    // ============================================================
    //  declare fromAccount, toAccount, amount, pin, and transaction fields

    // ============================================================
    // 📝 STEP 4: Write Constructor
    //
    // INSTRUCTIONS:
    //   Accept (IAccount from, IAccount to, double amount, int pin); store all.
    // ============================================================
    // implement constructor
    public TransferCommand(IAccount from, IAccount to, double amount, int pin) {
        //  Step 4 - implement constructor
        this.fromAccount = from;
        this.toAccount = to;
        this.amount = amount;
        this.pin = pin;
    }

    // ============================================================
    // 📝 STEP 5: Implement execute()
    //
    // INSTRUCTIONS:
    //   1. Call new TransferService().transferWithTransaction(fromAccount, toAccount, amount, pin).
    //   2. Store returned Transaction in this.transaction.
    // ============================================================
    //  implement execute() method
    @Override
    public void execute() throws Exception {
        // Step 5 - implement execute()
        this.transaction = new TransferService().transferWithTransaction(fromAccount, toAccount, amount, pin);
    }

    // ============================================================
    // 📝 STEP 6: Implement getTransaction()
    //
    // INSTRUCTIONS:
    //   Return this.transaction.
    // ============================================================
    //  return this.transaction
    @Override
    public Transaction getTransaction() {
        //  Step 6 - return this.transaction
        return this.transaction;
    }
}
