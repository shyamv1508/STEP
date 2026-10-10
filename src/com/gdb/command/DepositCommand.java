package com.gdb.command;

import com.gdb.domain.Account;
import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;

public class DepositCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    // ============================================================
    // 📝 STEP 3: Declare Fields
    //
    // INSTRUCTIONS:
    //   1. private IAccount account;
    //   2. private double amount;
    //   3. private Transaction transaction;
    // ============================================================
    //  declare account, amount, and transaction fields
    private IAccount account;
    private double amount;
    private Transaction transaction;

    // ============================================================
    // 📝 STEP 4: Write Constructor
    //
    // INSTRUCTIONS:
    //   Accept (IAccount account, double amount); store both in fields.
    // ============================================================
    // implement constructor
    public DepositCommand(IAccount account, double amount) {
        // Step 4 - implement constructor
        this.account = account;
        this.amount = amount;
    }

    // ============================================================
    // 📝 STEP 5: Implement execute()
    //
    // INSTRUCTIONS:
    //   1. Call account.depositWithTransaction(amount) (casting to Account if needed).
    //   2. Store returned Transaction in this.transaction.
    // ============================================================
    //  implement execute() method
    @Override
    public void execute() throws Exception {
        // Step 5 - implement execute()
        this.transaction = ((Account) account).depositWithTransaction(amount);
    }

    // ============================================================
    // 📝 STEP 6: Implement getTransaction()
    //
    // INSTRUCTIONS:
    //   Return this.transaction.
    // ============================================================
    // return this.transaction
    @Override
    public Transaction getTransaction() {
        //  Step 6 - return this.transaction
        return this.transaction;
    }
}
