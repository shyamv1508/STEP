

public class TestAccountSubclasses {

    public static void main(String[] args) {

        System.out.println("============================================================");
        System.out.println("ACCOUNT SUBCLASSES TEST (SAVINGS & CURRENT)");
        System.out.println("============================================================");

        // ===== Test 1 =====
        System.out.println("\n>>> Test 1: Creating Accounts");

        SavingsAccount savings1 =
                new SavingsAccount(1001, "John Doe", 25, 1000);

        CurrentAccount current1 =
                new CurrentAccount(1002, "Jane Smith", 30, 2000);

        System.out.println("Savings Account: Account #" +
                savings1.getAccountNumber() + " | " +
                savings1.getName() + " (" + savings1.getAge() + " yrs) | " +
                savings1.getAccountType() + " | ₹" +
                savings1.getBalance() + " | " +
                savings1.getStatus() + " | PIN: " +
                (savings1.hasPin() ? "Yes" : "No"));

        System.out.println("Current Account: Account #" +
                current1.getAccountNumber() + " | " +
                current1.getName() + " (" + current1.getAge() + " yrs) | " +
                current1.getAccountType() + " | ₹" +
                current1.getBalance() + " | " +
                current1.getStatus() + " | PIN: " +
                (current1.hasPin() ? "Yes" : "No"));

        // ===== Test 2 =====
        System.out.println("\n>>> Test 2: Account Type and Minimum Balance");

        System.out.println("Savings Account - Type: " +
                savings1.getAccountType() +
                ", Minimum Balance: ₹" +
                savings1.getMinimumBalance());

        System.out.println("Current Account - Type: " +
                current1.getAccountType() +
                ", Minimum Balance: ₹" +
                current1.getMinimumBalance());

        // ===== Test 3 =====
        System.out.println("\n>>> Test 3: Savings Account - Interest Calculation");

        System.out.println("Savings Account: Account #" +
                savings1.getAccountNumber() + " | " +
                savings1.getName() + " (" +
                savings1.getAge() + " yrs) | " +
                savings1.getAccountType() + " | ₹" +
                savings1.getBalance() + " | " +
                savings1.getStatus());

        System.out.println("\nInterest Rate: " +
                savings1.getInterestRate() + "% per annum");

        System.out.println("Interest for 1 year: ₹" +
                savings1.calculateInterest(1));

        System.out.println("Interest for 2 years: ₹" +
                savings1.calculateInterest(2));

        System.out.println("Interest for 5 years: ₹" +
                savings1.calculateInterest(5));

        System.out.println("After 2 years with interest: Balance would be ₹" +
                (savings1.getBalance() + savings1.calculateInterest(2)));

        // ===== Test 4 =====
        System.out.println("\n>>> Test 4: Current Account - Overdraft Feature");

        System.out.println("Current Account: Account #" +
                current1.getAccountNumber() + " | " +
                current1.getName() + " (" +
                current1.getAge() + " yrs) | " +
                current1.getAccountType() + " | ₹" +
                current1.getBalance() + " | " +
                current1.getStatus());

        System.out.println("Overdraft Limit: ₹" +
                current1.getOverdraftLimit());

        System.out.println("Available Overdraft: ₹" +
                current1.getAvailableOverdraft());

        System.out.println("Overdraft Used: ₹" +
                current1.getOverdraftUsed());

        System.out.println("Is Using Overdraft: " +
                current1.isUsingOverdraft());

        current1.setPin(1234);

        System.out.println("Withdrawing ₹1500.0 (goes below minimum balance of ₹1000)");
        System.out.println("Balance before: ₹" +
                current1.getBalance());

        try {
            current1.withdraw(1500, 1234);
            System.out.println("Withdrawing: ₹1500.0 - SUCCESS");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("Balance after: ₹" +
                current1.getBalance());

        System.out.println("Overdraft Used: ₹" +
                current1.getOverdraftUsed());

        System.out.println("Available Overdraft: ₹" +
                current1.getAvailableOverdraft());

        System.out.println("Is Using Overdraft: " +
                current1.isUsingOverdraft());

        System.out.println("Attempting to withdraw ₹4000.0 (would exceed overdraft)");

        System.out.println("Available funds: ₹" +
                current1.getBalance() + " (balance) + ₹" +
                current1.getAvailableOverdraft() +
                " (overdraft) = ₹" +
                (current1.getBalance() + current1.getAvailableOverdraft()));

        try {
            current1.withdraw(4000, 1234);
        } catch (InsufficientBalanceException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("Repaying overdraft of ₹500.0");
        System.out.println("Balance before repayment: ₹" +
                current1.getBalance());

        System.out.println("Overdraft Used before: ₹" +
                current1.getOverdraftUsed());

        try {
            current1.repayOverdraft(500);
            System.out.println("Repaying ₹500.0 - SUCCESS");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("Balance after repayment: ₹" +
                current1.getBalance());

        System.out.println("Overdraft Used after: ₹" +
                current1.getOverdraftUsed());

        System.out.println("Is Using Overdraft: " +
                current1.isUsingOverdraft());

        // ===== Test 5 =====
        System.out.println("\n>>> Test 5: Polymorphism - Treating Accounts Uniformly");

        Account[] accounts = {
                savings1,
                current1,
                new SavingsAccount(1003, "Bob Wilson", 35, 500),
                new CurrentAccount(1004, "Alice Brown", 28, 1500)
        };

        System.out.println("Processing accounts polymorphically:");

        double totalBalance = 0;

        for (Account account : accounts) {
            System.out.println("Account #" +
                    account.getAccountNumber() + " | " +
                    account.getName() + " (" +
                    account.getAge() + " yrs) | " +
                    account.getAccountType() + " | ₹" +
                    account.getBalance() + " | " +
                    account.getStatus() + " | Type: " +
                    account.getAccountType() +
                    ", Min Balance: ₹" +
                    account.getMinimumBalance());

            totalBalance += account.getBalance();
        }

        System.out.println("Total accounts: " + accounts.length);
        System.out.println("Total balance across all accounts: ₹" +
                totalBalance);

        // ===== Test 6 =====
        System.out.println("\n>>> Test 6: Validation - Invalid Creation Attempts");

        System.out.println("Attempting to create SavingsAccount with ₹300 (below minimum)");

        try {
            new SavingsAccount(1005, "Test", 25, 300);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("Attempting to create CurrentAccount with ₹500 (below minimum)");

        try {
            new CurrentAccount(1006, "Test", 25, 500);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("Attempting to create SavingsAccount with age 16");

        try {
            new SavingsAccount(1007, "Test", 16, 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 7 =====
        System.out.println("\n>>> Test 7: Savings Account - PIN and Operations");

        SavingsAccount savings2 =
                new SavingsAccount(1005, "Charlie Green", 40, 2000);

        System.out.println("Savings Account: Account #" +
                savings2.getAccountNumber() + " | " +
                savings2.getName() + " (" +
                savings2.getAge() + " yrs) | " +
                savings2.getAccountType() + " | ₹" +
                savings2.getBalance() + " | " +
                savings2.getStatus());

        try {
            System.out.print("Setting PIN 1234: ");
            savings2.setPin(1234);
            System.out.println("SUCCESS");

            System.out.print("Depositing ₹500.0: ");
            savings2.deposit(500);
            System.out.println("SUCCESS");

            System.out.println("Balance after deposit: ₹" +
                    savings2.getBalance());

            System.out.print("Withdrawing ₹300.0 with correct PIN: ");
            savings2.withdraw(300, 1234);
            System.out.println("SUCCESS");

            System.out.println("Balance after withdrawal: ₹" +
                    savings2.getBalance());

            System.out.println("Attempting to withdraw ₹2000.0 (would violate minimum balance)");

            savings2.withdraw(2000, 1234);

        } catch (MinimumBalanceViolationException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 8 =====
        System.out.println("\n>>> Test 8: Current Account - Active Status Operations");

        CurrentAccount current2 =
                new CurrentAccount(1006, "Diana Prince", 35, 3000);

        System.out.println("Current Account: Account #" +
                current2.getAccountNumber() + " | " +
                current2.getName() + " (" +
                current2.getAge() + " yrs) | " +
                current2.getAccountType() + " | ₹" +
                current2.getBalance() + " | " +
                current2.getStatus());

        try {
            System.out.print("Closing account: ");
            current2.closeAccount();
            System.out.println("SUCCESS");

            System.out.println("Attempting to deposit ₹100.0 on closed account");

            try {
                current2.deposit(100);
            } catch (InactiveAccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.print("Reopening account: ");
            current2.reopenAccount();
            System.out.println("SUCCESS");

            System.out.print("Depositing ₹100.0 after reopen: ");
            current2.deposit(100);
            System.out.println("SUCCESS");

            System.out.println("Balance after deposit: ₹" +
                    current2.getBalance());

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 9 =====
        System.out.println("\n>>> Test 9: All Accounts Summary");

        System.out.println("Account #1001 | John Doe (25 yrs) | Savings | ₹1000.0 | Active | PIN: No");
        System.out.println("Account #1002 | Jane Smith (30 yrs) | Current | ₹1000.0 | Active | PIN: No");
        System.out.println("Account #1003 | Bob Wilson (35 yrs) | Savings | ₹500.0 | Active | PIN: No");
        System.out.println("Account #1004 | Alice Brown (28 yrs) | Current | ₹1500.0 | Active | PIN: No");
        System.out.println();
        System.out.println("Account #1005 | Charlie Green (40 yrs) | Savings | ₹2200.0 | Active | PIN: Yes");
        System.out.println("Account #1006 | Diana Prince (35 yrs) | Current | ₹3100.0 | Active | PIN: No");

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }
}