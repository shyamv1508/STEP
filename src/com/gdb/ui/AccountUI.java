package com.gdb.ui;

import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;
import com.gdb.command.TransactionCommand;
import com.gdb.service.AccountService;
import com.gdb.exceptions.AccountException;

import java.util.*;

public class AccountUI {
    // ============================================================
    // 📝 STEP 1: Declare Fields done
    // ============================================================
    // TODO: declare private final AccountService service and Scanner scanner
    private final AccountService service;
    Scanner scanner = new Scanner(System.in);
    // ============================================================
    // 📝 STEP 2: Constructor done
    //
    // INSTRUCTIONS:
    //   1. Accept AccountService parameter.
    //   2. Assign to this.service.
    //   3. Initialize scanner = new Scanner(System.in).
    // ============================================================
    // TODO: implement constructor
    public AccountUI(AccountService service) {
        // TODO: Step 2 - implement constructor
        this.service = service;
        scanner = new Scanner(System.in);
    }

    // ============================================================
    // 📝 STEP 3: Implement start() (Main Menu Loop)  done
    //
    // INSTRUCTIONS:
    //   while (true) {
    //     displayMainMenu();
    //     int choice = readInt("Enter your choice: ");
    //     try {
    //       switch (choice) {
    //         case 1: handleOpenAccount(); break;
    //         case 2: handleDeposit(); break;
    //         case 3: handleWithdraw(); break;
    //         case 4: handleTransfer(); break;
    //         case 5: handleCloseAccount(); break;
    //         case 6: handleViewAccount(); break;
    //         case 7: handleViewTransactions(); break;
    //         case 8: System.out.println("Thank you! Goodbye."); return;
    //         default: System.out.println("Invalid choice. Please enter 1-8.");
    //       }
    //     } catch (Exception e) {
    //       System.out.println("ERROR: " + e.getMessage());
    //     }
    //   }
    // ============================================================
    // TODO: implement start() menu loop
    public void start() {
        // TODO: Step 3 - implement start() menu loop
        while (true) {
            displayMainMenu();
            int choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        handleOpenAccount();
                        break;
                    case 2:
                        handleDeposit();
                        break;
                    case 3:
                        handleWithdraw();
                        break;
                    case 4:
                        handleTransfer();
                        break;
                    case 5:
                        handleCloseAccount();
                        break;
                    case 6:
                        handleViewAccount();
                        break;
                    case 7:
                        handleViewTransactions();
                        break;
                    case 8:
                        System.out.println("Thank you! Goodbye.");
                        return;
                    default: System.out.println("Invalid choice. Please enter 1-8.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    // ============================================================
    // 📝 STEP 4: Implement displayMainMenu()    DONE
    // ============================================================
    // TODO: print formatted main menu options
    private void displayMainMenu() {
        // TODO: Step 4 - print formatted main menu options
        System.out.println("========================================\n" +
                "   GLOBAL DIGITAL BANK\n" +
                "========================================\n" +
                "1. Open Account\n" +
                "2. Deposit\n" +
                "3. Withdraw\n" +
                "4. Transfer\n" +
                "5. Close Account\n" +
                "6. View Account Details\n" +
                "7. View Transaction History\n" +
                "8. Exit\n" +
                "========================================\n\n");

    }

    // ============================================================
    // 📝 STEP 5: Implement handleOpenAccount()  DONE
    //
    // INSTRUCTIONS:
    //   1. String type = readString("Account Type (Savings/Current/FixedDeposit/Salary): ")
    //   2. String name = readString("Name: ")
    //   3. int age = readInt("Age: ")
    //   4. double amount = readDouble("Initial Balance: ")
    //   5. IAccount acc = service.openAccount(type, name, age, amount)
    //   6. System.out.println("SUCCESS: " + acc.getAccountInfo())
    //   7. int pin = readInt("Set 4-digit PIN: ")
    //   8. acc.setPin(pin)
    // ============================================================
    // TODO: implement account opening interaction
    private void handleOpenAccount() throws Exception {
        // TODO: Step 5 - implement account opening interaction
        String type = readString("Account Type (Savings/Current/FixedDeposit/Salary): ");
        String name = readString("Name: ");
        int age = readInt("Age: ");
        double amount = readDouble("Initial Balance: ");
        IAccount acc = service.openAccount(type, name, age, amount);
        System.out.println("SUCCESS: " + acc.getAccountInfo());
        int pin = readInt("Set 4-digit PIN: ");
        acc.setPin(pin);
    }

    // ============================================================
    // 📝 STEP 6: Implement handleDeposit()  DONE
    //
    // INSTRUCTIONS:
    //   1. int accNo = readInt("Account Number: ")
    //   2. double amount = readDouble("Amount to deposit: ")
    //   3. Transaction txn = service.deposit(accNo, amount)
    //   4. Print success + new balance
    // ============================================================
    // TODO: implement deposit interaction
    private void handleDeposit() throws Exception {
        // TODO: Step 6 - implement deposit interaction
        int accNo = readInt("Account Number: ");
        double amount = readDouble("Amount to deposit: ");
        Transaction txn = service.deposit(accNo, amount);
        IAccount acc = service.getAccount(accNo);
        System.out.println("SUCESS : Deposited Rs." + amount + " to #" + accNo + ".  New Balance:  " + acc.getBalance() );
    }

    // ============================================================
    // 📝 STEP 7: Implement handleWithdraw() DONE
    //
    // INSTRUCTIONS:
    //   1. int accNo = readInt("Account Number: ")
    //   2. double amount = readDouble("Amount to withdraw: ")
    //   3. int pin = readInt("PIN: ")
    //   4. Transaction txn = service.withdraw(accNo, amount, pin)
    //   5. Print success + new balance
    // ============================================================
    // TODO: implement withdrawal interaction
    private void handleWithdraw() throws Exception {
        // TODO: Step 7 - implement withdrawal interaction
        int accNo = readInt("Account Number: ");
        double amount = readDouble("Amount to withdraw: ");
        int pin = readInt("PIN: ");
        Transaction txn = service.withdraw(accNo, amount, pin);
        IAccount acc = service.getAccount(accNo);
        System.out.println("SUCESS : Withdrawed Rs." + amount + " from #" + accNo + ". New Balance:  " + acc.getBalance());
    }

    // ============================================================
    // 📝 STEP 8: Implement handleTransfer() DONE
    //
    // INSTRUCTIONS:
    //   1. int fromAcc = readInt("From Account: ")
    //   2. int toAcc = readInt("To Account: ")
    //   3. double amount = readDouble("Amount: ")
    //   4. int pin = readInt("PIN: ")
    //   5. Transaction txn = service.transfer(fromAcc, toAcc, amount, pin)
    //   6. Print success message
    // ============================================================
    // TODO: implement transfer interaction
    private void handleTransfer() throws Exception {
        // TODO: Step 8 - implement transfer interaction
        int fromAcc = readInt("From Account: ");
        int toAcc = readInt("To Account: ");
        double amount = readDouble("Amount: ");
        int pin = readInt("PIN: ");
        Transaction txn = service.transfer(fromAcc, toAcc, amount, pin);
        System.out.println("SUCESS  : Transferred Rs." + amount + " From #" + fromAcc + " to #" + toAcc );
    }

    // ============================================================
    // 📝 STEP 9: Implement handleCloseAccount()     DONE
    //
    // INSTRUCTIONS:
    //   1. int accNo = readInt("Account Number: ")
    //   2. int pin = readInt("PIN: ")
    //   3. service.closeAccount(accNo, pin)
    //   4. Print success message
    // ============================================================
    // TODO: implement close account interaction
    private void handleCloseAccount() throws Exception {
        // TODO: Step 9 - implement close account interaction
        int accNo = readInt("Account Number: ");
        int pin = readInt("PIN: ");
        service.closeAccount(accNo, pin);
        IAccount acc = service.getAccount(accNo);
        System.out.println("SUCESS : Closed #" + accNo + " Closing Balance: " + acc.getBalance());
    }

    // ============================================================
    // 📝 STEP 10: Implement handleViewAccount() DONE
    //
    // INSTRUCTIONS:
    //   1. int accNo = readInt("Account Number: ")
    //   2. IAccount acc = service.getAccount(accNo)
    //   3. If null -> "Account not found: " + accNo
    //   4. Else -> print acc.getAccountInfo()
    // ============================================================
    // TODO: implement view account details
    private void handleViewAccount() {
        // TODO: Step 10 - implement view account details
        int accNo = readInt("Account Number: ");
        IAccount acc = service.getAccount(accNo);
        if(acc == null){
            System.out.println("Account not found: " + accNo);
        }
        else{
            System.out.println(acc.getAccountInfo());
        }
    }

    // ============================================================
    // 📝 STEP 11: Implement handleViewTransactions()    DONE
    //
    // INSTRUCTIONS:
    //   1. List<TransactionCommand> history = service.getTransactionHistory()
    //   2. If empty -> "No transactions logged."
    //   3. Else print each with index
    // ============================================================
    // TODO: implement view transaction history
    private void handleViewTransactions() {
        // TODO: Step 11 - implement view transaction history
        List<TransactionCommand> history = service.getTransactionHistory();
        if(history == null){
            System.out.println("No transactions logged.");
        }
        else{
            System.out.println(history);
        }
    }

    // ============================================================
    // 📝 STEP 12: Implement readInt(String prompt)  DONE
    // ============================================================
    // TODO: implement robust integer reader
    private int readInt(String prompt) {
        // TODO: Step 12 - implement robust integer reader
        System.out.printf(prompt);
        return scanner.nextInt();
    }

    // ============================================================
    // 📝 STEP 13: Implement readDouble(String prompt)   DONE
    // ============================================================
    // TODO: implement robust double reader
    private double readDouble(String prompt) {
        // TODO: Step 13 - implement robust double reader
        System.out.printf(prompt);
        return scanner.nextDouble();
    }

    // ============================================================
    // 📝 STEP 14: Implement readString(String prompt)   DONE
    // ============================================================
    // TODO: implement string reader
    private String readString(String prompt) {
        // TODO: Step 14 - implement string reader
        System.out.printf(prompt);
        return scanner.next();
    }
}
