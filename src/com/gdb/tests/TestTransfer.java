package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.service.TransferService;
import com.gdb.exceptions.*;

public class TestTransfer {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 15 — TRANSFER WITH DAILY LIMITS");
        System.out.println("=".repeat(60));

        TransferService svc = new TransferService();

        // STEP 9: Create two NEW Savings accounts with the same PIN.
        IAccount acc1 = AccountFactory.createAccount(
                "SAVINGS", "1001", "Rajesh Sharma", 30, 100000,
                "ACTIVE", "1234", 0);
        IAccount acc2 = AccountFactory.createAccount(
                "SAVINGS", "1002", "Priya Patel", 28, 20000,
                "ACTIVE", "1234", 0);

        // STEP 10: Successful transfer.
        svc.transfer(acc1, acc2, 5000, 1234);
        System.out.printf("Transfer Rs. 5,000: SUCCESS | acc1 = Rs. %.1f | acc2 = Rs. %.1f%n",
                acc1.getBalance(), acc2.getBalance());

        // STEP 11: The transfer must fail because it would exceed the available balance
        // while preserving the sender's minimum balance.
        try {
            svc.transfer(acc1, acc2, 100000, 1234);
            System.out.println("Unexpected success");
        } catch (InsufficientBalanceException e) {
            System.out.println("Insufficient balance test: " + e.getMessage());
        }

        // STEP 12: Transfer repeatedly until the daily limit is reached.
        AbstractAccount source = (AbstractAccount) acc1;
        System.out.println("Daily transfer limit: Rs. " + source.getDailyTransferLimit());
        for (int i = 1; i <= 3; i++) {
            try {
                svc.transfer(acc1, acc2, 20000, 1234);
                System.out.println("Rs. 20,000 transfer " + i + ": SUCCESS");
            } catch (AccountException e) {
                System.out.println("Daily limit test: " + e.getMessage());
                break;
            }
        }

        // STEP 13: Show how much of today's limit has been used and remains.
        System.out.println("Daily transfer total: Rs. " + source.getDailyTransferTotal());
        System.out.println("Remaining daily limit: Rs. " + source.getRemainingDailyTransferLimit());
        System.out.println("Final balances: acc1 = Rs. " + acc1.getBalance()
                + " | acc2 = Rs. " + acc2.getBalance());
    }
}