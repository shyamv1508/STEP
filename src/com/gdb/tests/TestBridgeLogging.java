package com.gdb.tests;

import com.gdb.command.*;
import com.gdb.db.SimulatedDatabase;
import com.gdb.domain.*;
import com.gdb.logging.*;
import java.util.List;

public class TestBridgeLogging {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 18 — BRIDGE PATTERN (FILE + DB)");
        System.out.println("=".repeat(60));

        // Setup test accounts
        IAccount acc1 = AccountFactory.createAccount("SAVINGS", 1001, "John", 25, 15000);
        acc1.setPin(1234);
        IAccount acc2 = AccountFactory.createAccount("SAVINGS", 1002, "Jane", 30, 10000);

        // ============================================================
        // 📝 STEP 24: Create Destinations
        //
        // INSTRUCTIONS:
        FileLogDestination fileDest = new FileLogDestination();
        fileDest.clear();
        SimulatedDatabase db = new SimulatedDatabase();
        DatabaseLogDestination dbDest = new DatabaseLogDestination(db);
        MemoryLogDestination memDest = new MemoryLogDestination();
        // ============================================================
        //  instantiate all log destinations

        // ============================================================
        // 📝 STEP 25: Create TransactionLogger with File Destination
        // ============================================================
        TransactionLogger logger = new TransactionLogger(fileDest);

        DepositCommand dep1 = new DepositCommand(acc1, 1000);
        dep1.execute();
        logger.log(dep1);

        WithdrawCommand with1 = new WithdrawCommand(acc1, 500, 1234);
        with1.execute();
        logger.log(with1);

        TransferCommand trans1 = new TransferCommand(acc1, acc2, 1000, 1234);
        trans1.execute();
        logger.log(trans1);

        System.out.println("\n[STEP 25] Logging to FILE destination...");
        System.out.println("  FILE log count: " + logger.readAll().size());

        // ============================================================
        // 📝 STEP 26: Switch to Database Destination
        // ============================================================
        logger.setDestination(dbDest);

        DepositCommand dep2 = new DepositCommand(acc1, 1000);
        dep2.execute();
        logger.log(dep2);

        WithdrawCommand with2 = new WithdrawCommand(acc1, 500, 1234);
        with2.execute();
        logger.log(with2);

        TransferCommand trans2 = new TransferCommand(acc1, acc2, 1000, 1234);
        trans2.execute();
        logger.log(trans2);

        System.out.println("\n[STEP 26] Switched to DATABASE destination...");
        System.out.println("  DATABASE log count: " + logger.readAll().size());

        // ============================================================
        // 📝 STEP 27: Switch to Memory Destination
        // ============================================================
        logger.setDestination(memDest);

        DepositCommand dep3 = new DepositCommand(acc1, 1000);
        dep3.execute();
        logger.log(dep3);

        WithdrawCommand with3 = new WithdrawCommand(acc1, 500, 1234);
        with3.execute();
        logger.log(with3);

        TransferCommand trans3 = new TransferCommand(acc1, acc2, 1000, 1234);
        trans3.execute();
        logger.log(trans3);

        System.out.println("\n[STEP 27] Switched to MEMORY destination...");
        System.out.println("  MEMORY log count: " + logger.readAll().size());

        // ============================================================
        // 📝 STEP 28: Verify Data Isolation Across Backends
        // ============================================================
        System.out.println("\n[STEP 28] Verifying Data Isolation:");

        logger.setDestination(fileDest);
        int fileCount = logger.readAll().size();
        System.out.println("  FILE count: " + fileCount + " [EXPECTED: 3]");

        logger.setDestination(dbDest);
        int dbCount = logger.readAll().size();
        System.out.println("  DATABASE count: " + dbCount + " [EXPECTED: 3]");

        logger.setDestination(memDest);
        int memoryCount = logger.readAll().size();
        System.out.println("  MEMORY count: " + memoryCount + " [EXPECTED: 3]");

        // ============================================================
        // 📝 STEP 29: Print Destination Names and Final Result
        // ============================================================
        System.out.println("\n[STEP 29] Destination Names:");
        System.out.println("  " + fileDest.getDestinationName());
        System.out.println("  " + dbDest.getDestinationName());
        System.out.println("  " + memDest.getDestinationName());

        if (fileCount == 3 && dbCount == 3 && memoryCount == 3) {
            System.out.println("\n[STEP 29] All Bridge Pattern log backends verified successfully!");
        } else {
            System.out.println("\n[STEP 29] Verification FAILED. Check the log destination implementations.");
        }
    }
}
