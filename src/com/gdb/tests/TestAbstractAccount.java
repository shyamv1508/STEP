package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {
    public static void main(String[] args) {
        System.out.println("=== ACTIVITY 9: Testing BankAccount Abstract Class ===");
        try {
            BankAccount sa = new SavingsAccount("SA9001", "Meera Sen", 26, 5000.0, "ACTIVE");
            sa.displayAccountInfo();
        } catch (AccountException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("=== Activity 9 Completed ===");
    }
}
