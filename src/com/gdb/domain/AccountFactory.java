package com.gdb.domain;

public class AccountFactory {
    // TODO: Step 3 - Centralise account creation with a switch on the account type:
    //  If type is null, return null.
    //   - switch (type.toUpperCase()):
    //       "SAVINGS"             -> a new SavingsAccount      (minBalance 1000.0, interestRate 4.0)
    //       "CURRENT"             -> a new CurrentAccount      (overdraftLimit 25000.0)
    //       "FIXED_DEPOSIT", "FD" -> a new FixedDepositAccount (tenureMonths 12, interestRate 6.5)
    //       "SALARY"              -> a new SalaryAccount       (employerName "TechCorp")
    //       default               -> throw new IllegalArgumentException("Unknown account type: " + type)
    public static IAccount createAccount(String type, String accNum, String name, int age, double balance, String status, String pin) {
        if(type == null){
            return null;
        }
        switch(type.toUpperCase()){
            case 'SAVINGS':
                break;
            case 'CURRENT':
                break;
            case 'FIXED_DEPOSIT','FD':
                break;
            case 'SALARY':
                break;
            default:
                throw new IllegalArgumentException("Unknown account type: " + type);
                break;
        }
    }
}
