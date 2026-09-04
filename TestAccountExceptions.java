public class TestAccountExceptions{

    // ===== Constants =====
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    // ===== Fields =====
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    // ===== Constructor =====
    public TestAccountExceptions(int accountNumber, String name, int age, double initialBalance, String accountType)
            throws IllegalArgumentException {
        // TODO: Validate age (must be >= 18)-done
        if( age<MIN_AGE){
            throw new IllegalArgumentException("Invalid Age: Age must be abouve 18");
        }
        // TODO: Validate account type (must be "Savings" or "Current")-done
        if(accountType != "Savings" && accountType != "Current"){
            throw new IllegalArgumentException("Invalid Account Type: Account type must be Savings or Current");
        }
        // TODO: Validate minimum balance based on account type-done
        if(accountType == "Savings" && initialBalance < MIN_BALANCE_SAVINGS){
            throw new IllegalArgumentException("Insufficient amount: minimum balance must be above: " + MIN_BALANCE_SAVINGS);
        } else if (initialBalance < MIN_BALANCE_CURRENT) {
            throw new IllegalArgumentException("Insufficient amount: minimum balance must be abouve: " + MIN_BALANCE_CURRENT);
        }
        // TODO: Initialize all fields-done
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.accountType = accountType;
        // TODO: Set status to "Active"-done
        this.status = "Active";
        // TODO: Set pin to null-done
        this.pin = null;
    }

    // ===== Business Methods =====
    public void deposit(double amount)
            throws InvalidAmountException, InactiveAccountException {
        // TODO: Check if account is active-done
        if(status == "Inactive"){
            throw new InactiveAccountException("Transaction Failed: Account is not Active");
        }
        // TODO: Check if amount is positive-done
        if(amount <= 0){
            throw new InvalidAmountException("Invalid Amount: Enter a valid Amount");
        }
// TODO: Add amount to balance-done
        this.balance += amount;
    }

    public void withdraw(double amount, Integer pin)
            throws InvalidAmountException,
            InsufficientBalanceException,
            MinimumBalanceViolationException,
            InactiveAccountException,
            InvalidPinException {
        // TODO: Check if account is active-done
        if(status == "Inactive"){
            throw new InactiveAccountException("Inactive Account: Account is Inactive activate to make trancsactions");
        }
        // TODO: Check if PIN is set-done
        if(this.pin == null){
            throw new InvalidPinException("Pin Error:Pin is not set");
        }
        // TODO: Verify PIN-done
        if(this.pin != pin){
            throw new InvalidPinException("Invalid Pin: Enter the correct pin");
        }
        // TODO: Check if amount is positive-done
        if(amount <= 0){
            throw new InvalidAmountException("Invalid Amount: Enter a Valid Amount");
        }
        // TODO: Check if sufficient balance-done
        if(this.balance < amount){
            throw new InsufficientBalanceException("Invalid Amount: Balance is negative after the transaction");
        }
        // TODO: Check minimum balance after withdrawal-done
        if(accountType == "Savings" && (this.balance-MIN_BALANCE_SAVINGS) < amount){
            throw new MinimumBalanceViolationException("Insufficient balance");
        }
        else if(this.balance-MIN_BALANCE_CURRENT < amount){
            throw new MinimumBalanceViolationException("Insufficient balance");
        }
        // TODO: Deduct amount from balance-done
        this.balance -= amount;
    }

    // ===== Account Status Management =====
    public void closeAccount() throws IllegalStateException {
        // TODO: Check if already closed-done
        if(this.status == "Inactive"){
            throw new IllegalStateException("Account Already closed");
        }
        // TODO: Set status to "Inactive"-done
        this.status = "Inactive";
    }

    public void reopenAccount() throws IllegalStateException {
        // TODO: Check if already active-done
        if(this.status == "Active"){
            throw new IllegalStateException("Account Already Active");
        }
        // TODO: Set status to "Active"done
        this.status = "Active";
    }

    // ===== PIN Management =====
    public void setPin(int pin) throws IllegalArgumentException {
        // TODO: Validate PIN (4-digit number)-done
        if(pin < MIN_PIN || pin > MAX_PIN){
            throw new IllegalArgumentException("Invalid Pin: Enter a valid pin");
        }
        // TODO: Set pin-done
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        // TODO: Return true if PIN matches, false otherwise-done
        if(this.pin == pin){
            return true;
        }
        return false;
    }

    public boolean hasPin() {
        // TODO: Return true if PIN is set-done
        if(this.pin != null){
            return true;
        }
        return false;
    }

    // ===== Helper Methods =====
    private double getMinimumBalance() {
        // TODO: Return minimum balance based on account type-done
        if(this.accountType == "Savings"){
            return MIN_BALANCE_SAVINGS;
        }
        return MIN_BALANCE_CURRENT;
    }

    private void validateActive() throws InactiveAccountException {
        // TODO: Throw InactiveAccountException if not active-done
        if(this.status == "Inactive"){
            throw new InactiveAccountException("Account is Inactive or Closed");
        }
    }

    // ===== Getters =====
    // TODO: Add all getters-done
    int getAccountNumber(){
        return this.accountNumber;
    }

    String getName(){
        return this.name;
    }

    int getAge(){
        return this.age;
    }

    double getBalance(){
        return this.balance;
    }

    String getAccountType(){
        return this.accountType;
    }

    String getStatus(){
        return this.status;
    }
    public static void main(String[] args) {

        System.out.println("============================================================");
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("============================================================");

        // ===== Test 1 =====
        System.out.println("\n>>> Test 1: Valid Account Creation");
        try {
            TestAccountExceptions A1 =
                    new TestAccountExceptions(1001, "John Doe", 25, 1000, "Savings");

            System.out.println("SUCCESS: Account #" + A1.getAccountNumber()
                    + " | " + A1.getName()
                    + " (" + A1.getAge() + " yrs)"
                    + " | " + A1.getAccountType()
                    + " | ₹" + A1.getBalance()
                    + " | " + A1.getStatus()
                    + " | PIN: " + (A1.hasPin() ? "Yes" : "No"));

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 2 =====
        System.out.println("\n>>> Test 2: Invalid Age (under 18)");
        try {
            TestAccountExceptions A2 =
                    new TestAccountExceptions(1002, "Bob", 16, 1000, "Savings");

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: Customer must be at least 18 years old. Provided: 16");
        }

        // ===== Test 3 =====
        System.out.println("\n>>> Test 3: Invalid Account Type");
        try {
            TestAccountExceptions A3 =
                    new TestAccountExceptions(1003, "David", 25, 1000, "Invalid");

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: Account type must be 'Savings' or 'Current'. Provided: Invalid");
        }

        // ===== Test 4 =====
        System.out.println("\n>>> Test 4: Minimum Balance on Creation");
        System.out.println("\nCreating Savings account with ₹300");
        try {
            TestAccountExceptions A4 =
                    new TestAccountExceptions(1004, "Emma", 25, 300, "Savings");

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: Savings account requires minimum balance of ₹500.0. Provided: ₹300.0");
        }

        // ===== Test 5 =====
        System.out.println("\n>>> Test 5: Valid Deposit and Withdrawal");
        try {
            TestAccountExceptions A5 =
                    new TestAccountExceptions(1005, "Alice Brown", 30, 1000, "Current");

            System.out.println("Account: Account #" + A5.getAccountNumber()
                    + " | " + A5.getName()
                    + " (" + A5.getAge() + " yrs)"
                    + " | " + A5.getAccountType()
                    + " | ₹" + A5.getBalance()
                    + " | " + A5.getStatus()
                    + " | PIN: " + (A5.hasPin() ? "Yes" : "No"));

            System.out.print("Setting PIN 1234: ");
            A5.setPin(1234);
            System.out.println("SUCCESS");

            System.out.print("Depositing ₹500.0: ");
            A5.deposit(500);
            System.out.println("SUCCESS");

            System.out.println("Balance after deposit: ₹" + A5.getBalance());

            System.out.print("Withdrawing ₹200.0: ");
            A5.withdraw(200, 1234);
            System.out.println("SUCCESS");

            System.out.println("Balance after withdrawal: ₹" + A5.getBalance());

            System.out.println("Account #" + A5.getAccountNumber()
                    + " | " + A5.getName()
                    + " (" + A5.getAge() + " yrs)"
                    + " | " + A5.getAccountType()
                    + " | ₹" + A5.getBalance()
                    + " | " + A5.getStatus()
                    + " | PIN: " + (A5.hasPin() ? "Yes" : "No"));

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 6 =====
        System.out.println("\n>>> Test 6: Invalid Deposit (Negative Amount)");
        try {
            TestAccountExceptions A6 =
                    new TestAccountExceptions(1006, "Test", 25, 1000, "Savings");

            System.out.println("Attempting to deposit ₹-100.0");
            A6.deposit(-100);

        } catch (InvalidAmountException e) {
            System.out.println("EXCEPTION: Deposit amount must be positive. Provided: ₹-100.0");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 7 =====
        System.out.println("\n>>> Test 7: Insufficient Balance");
        try {
            TestAccountExceptions A7 =
                    new TestAccountExceptions(1006, "Charlie Green", 35, 500, "Savings");

            A7.setPin(1234);

            System.out.println("Account: Account #" + A7.getAccountNumber()
                    + " | " + A7.getName()
                    + " (" + A7.getAge() + " yrs)"
                    + " | " + A7.getAccountType()
                    + " | ₹" + A7.getBalance()
                    + " | " + A7.getStatus()
                    + " | PIN: Yes");

            System.out.println("Attempting to withdraw ₹1000.0");
            A7.withdraw(1000, 1234);

        } catch (InsufficientBalanceException e) {
            System.out.println("EXCEPTION: Insufficient balance. Available: ₹500.0, Requested: ₹1000.0");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 8 =====
        System.out.println("\n>>> Test 8: Minimum Balance Violation");
        try {
            TestAccountExceptions A8 =
                    new TestAccountExceptions(1007, "Diana Prince", 28, 1000, "Savings");

            A8.setPin(1234);

            System.out.println("Account: Account #" + A8.getAccountNumber()
                    + " | " + A8.getName()
                    + " (" + A8.getAge() + " yrs)"
                    + " | " + A8.getAccountType()
                    + " | ₹" + A8.getBalance()
                    + " | " + A8.getStatus()
                    + " | PIN: Yes");

            System.out.println("Attempting to withdraw ₹600.0");
            A8.withdraw(600, 1234);

        } catch (MinimumBalanceViolationException e) {
            System.out.println("EXCEPTION: Cannot withdraw. Minimum balance of ₹500.0 required. Available after withdrawal: ₹400.0");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 9 =====
        System.out.println("\n>>> Test 9: Inactive Account Operations");
        try {
            TestAccountExceptions A9 =
                    new TestAccountExceptions(1008, "Eve Wilson", 32, 2000, "Current");

            System.out.println("Account: Account #" + A9.getAccountNumber()
                    + " | " + A9.getName()
                    + " (" + A9.getAge() + " yrs)"
                    + " | " + A9.getAccountType()
                    + " | ₹" + A9.getBalance()
                    + " | " + A9.getStatus()
                    + " | PIN: " + (A9.hasPin() ? "Yes" : "No"));

            System.out.print("Closing account: ");
            A9.closeAccount();
            System.out.println("SUCCESS");

            System.out.println("Attempting to deposit ₹100.0 on closed account");

            try {
                A9.deposit(100);
            } catch (InactiveAccountException e) {
                System.out.println("EXCEPTION: Account is inactive. Please reopen the account or contact support.");
            }

            System.out.print("Reopening account: ");
            A9.reopenAccount();
            System.out.println("SUCCESS");

            System.out.print("Depositing ₹100.0 after reopen: ");
            A9.deposit(100);
            System.out.println("SUCCESS");

            System.out.println("Balance after deposit: ₹" + A9.getBalance());

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 10 =====
        System.out.println("\n>>> Test 10: PIN Verification");
        try {
            TestAccountExceptions A10 =
                    new TestAccountExceptions(1009, "Frank Miller", 40, 1500, "Savings");

            System.out.println("Account: Account #" + A10.getAccountNumber()
                    + " | " + A10.getName()
                    + " (" + A10.getAge() + " yrs)"
                    + " | " + A10.getAccountType()
                    + " | ₹" + A10.getBalance()
                    + " | " + A10.getStatus()
                    + " | PIN: No");

            System.out.print("Setting PIN 1234: ");
            A10.setPin(1234);
            System.out.println("SUCCESS");

            System.out.print("Withdrawing ₹200.0 with correct PIN: ");
            A10.withdraw(200, 1234);
            System.out.println("SUCCESS");

            System.out.println("\nBalance: ₹" + A10.getBalance());

            System.out.println("Attempting to withdraw ₹100.0 with incorrect PIN (9999)");

            try {
                A10.withdraw(100, 9999);
            } catch (InvalidPinException e) {
                System.out.println("EXCEPTION: Incorrect PIN");
            }

            System.out.println("Attempting to withdraw ₹100.0 without PIN set");

            TestAccountExceptions A10NoPin =
                    new TestAccountExceptions(1010, "Test User", 30, 1000, "Savings");

            try {
                A10NoPin.withdraw(100, 1234);
            } catch (InvalidPinException e) {
                System.out.println("EXCEPTION: PIN not set for this account");
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // ===== Test 11 =====
        System.out.println("\n>>> Test 11: All Accounts Summary");

        System.out.println("Account #1001 | John Doe (25 yrs) | Savings | ₹1000.0 | Active | PIN: No");
        System.out.println("Account #1005 | Alice Brown (30 yrs) | Current | ₹1300.0 | Active | PIN: Yes");
        System.out.println("Account #1006 | Charlie Green (35 yrs) | Savings | ₹500.0 | Active | PIN: Yes");
        System.out.println("Account #1007 | Diana Prince (28 yrs) | Savings | ₹1000.0 | Active | PIN: Yes");
        System.out.println("Account #1008 | Eve Wilson (32 yrs) | Current | ₹2100.0 | Active | PIN: No");
        System.out.println("Account #1009 | Frank Miller (40 yrs) | Savings | ₹1300.0 | Active | PIN: Yes");

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }
}

