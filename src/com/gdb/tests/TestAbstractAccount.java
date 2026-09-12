package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        // NOTE: If you completed Activity 9 successfully, paste your working domain classes into src/com/gdb/domain (replacing the provided versions).

        // TODO: Step 1 - Create an array/portfolio of AbstractAccount objects (SavingsAccount, CurrentAccount, SalaryAccount)
        AbstractAccount[] accounts = {new SavingsAccount("1001","Sugan",20,10000,"ACTIVE","1234",500,4.0), new CurrentAccount("1002","sugan",20,2000,"ACTIVE","1234",2000), new SalaryAccount("1003","sugan",20,3000,"ACTIVE","1234","Venkat")};

        // TODO: Step 2 - Implement and test secure fund transfer from Savings to Current account with PIN authentication


        public void FundTransfer(double amount, String Pin)throws AccountException {
            try {
                accounts[0].withdraw(amount, Pin);
                accounts[1].deposit(amount);
            } catch (AccountException e) {
                System.out.println("Transfer Failed!!" + e.getMessage());
            }
            System.out.println("Transfer Rs" + amount + "from Savings to Current: SUCCESS");
            System.out.println("Savings Balance: Rs " + accounts[0].getBalance() + " | Current Balance: Rs " + accounts[1].getBalance());
        }
        FundTransfer(3000,"1234");
        // TODO: Step 3 - Test failed transfer with wrong PIN and verify no balance was credited/debited

        // TODO: Step 4 - Process monthly cycle applying interest to every SavingsAccount and checking each SalaryAccount's inactive months

        System.out.println("=== Complete the test suite and verify all banking operations ===");
    }
}
