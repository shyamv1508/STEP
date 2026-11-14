package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.repository.*;
import com.gdb.service.AccountService;
import com.gdb.logging.*;
import java.time.LocalDateTime;
import java.util.List;

public class TestRepositoryInMemory {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 21 — REPOSITORY PATTERN (IN-MEMORY)");
        System.out.println("=".repeat(60));

        AccountRepository accountRepo = new InMemoryAccountRepository();
        TransactionRepository txnRepo = new InMemoryTransactionRepository();
        TransactionLogger logger = new TransactionLogger(new MemoryLogDestination());

        AccountService service = new AccountService(accountRepo, txnRepo, logger);

        // ============================================================
        // 📝 STEP 17: Test Account Repository CRUD
        // ============================================================
        IAccount repoAccount = AccountFactory.createAccount("SAVINGS", 2001,
                "Repository Test", 30, 20_000);
        accountRepo.save(repoAccount);
        check(accountRepo.findById(2001) == repoAccount, "account save/findById");
        check(accountRepo.findAll().size() == 1, "account findAll");
        check(accountRepo.exists(2001) && !accountRepo.exists(9999), "account exists");
        repoAccount.deposit(500);
        accountRepo.update(repoAccount);
        check(accountRepo.findById(2001).getBalance() == 20_500, "account update");
        check(accountRepo.nextAccountNumber() == 1001, "next account number");
        accountRepo.delete(2001);
        check(!accountRepo.exists(2001), "account delete");
        System.out.println("Account repository CRUD: PASS");

        // ============================================================
        // 📝 STEP 18: Test Transaction Repository Operations
        // ============================================================
        Transaction txn = new Transaction("TEST-1", LocalDateTime.now(), 3001,
                TransactionType.DEPOSIT, 250, 1_250, "SUCCESS", "Test deposit", 0, 0);
        txnRepo.save(txn);
        check(txnRepo.findAll().size() == 1, "transaction findAll");
        check(txnRepo.findByAccount(3001).size() == 1, "transaction find by account");
        check(txnRepo.findByAccount(9999).isEmpty(), "transaction account filter");
        txnRepo.clear();
        check(txnRepo.findAll().isEmpty(), "transaction clear");
        System.out.println("Transaction repository operations: PASS");

        // ============================================================
        // 📝 STEP 19: Test Service Integration With Repositories
        // ============================================================
        IAccount source = service.openAccount("SAVINGS", "Source Test", 30, 20_000);
        IAccount destination = service.openAccount("SAVINGS", "Destination Test", 30, 20_000);
        source.setPin(1234);
        destination.setPin(5678);

        service.deposit(source.getAccountNumber(), 500);
        service.withdraw(source.getAccountNumber(), 200, 1234);
        service.transfer(source.getAccountNumber(), destination.getAccountNumber(), 100, 1234);

        check(service.getAccount(source.getAccountNumber()).getBalance() == 20_200,
                "service open/deposit/withdraw/transfer source balance");
        check(service.getAccount(destination.getAccountNumber()).getBalance() == 20_100,
                "service transfer destination balance");
        check(service.getTransactionRecords().size() == 3, "service transaction persistence");
        check(service.getTransactionRecords(source.getAccountNumber()).size() == 3,
                "service transaction history by account");
        System.out.println("AccountService integration: PASS");

        System.out.println("Activity 21 Repository tests complete.");
    }

    private static void check(boolean condition, String testName) {
        if (!condition) {
            throw new AssertionError("Failed: " + testName);
        }
    }
}
