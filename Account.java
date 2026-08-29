public Account {
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
public Account(int accountNumber, String name, int age,
    double initialBalance, String accountType)
throws IllegalArgumentException {
// TODO: Validate age (must be >= 18)
    if(this.age < 18) {
        throw new IllegalArgumentException("Invalid age: Minimum age to open an account is 18");
    }
// TODO: Validate account type (must be "Savings" or "Current")
     if(this.accountType != "Savings" && accountType != "Current"){

     }
// TODO: Validate minimum balance based on account type
// TODO: Initialize all fields
// TODO: Set status to "Active"
// TODO: Set pin to null
    }
// ===== Business Methods =====
    public void deposit(double amount)
throws InvalidAmountException, InactiveAccountException {
// TODO: Check if account is active
// TODO: Check if amount is positive

// TODO: Add amount to balance
    }
    public void withdraw(double amount, int pin)
throws InvalidAmountException,
            InsufficientBalanceException,
            MinimumBalanceViolationException,
            InactiveAccountException,
            InvalidPinException {
// TODO: Check if account is active
// TODO: Check if PIN is set
// TODO: Verify PIN
// TODO: Check if amount is positive
// TODO: Check if sufficient balance
// TODO: Check minimum balance after withdrawal
// TODO: Deduct amount from balance
    }
// ===== Account Status Management =====
    public void closeAccount() throws IllegalStateException {
// TODO: Check if already closed
// TODO: Set status to "Inactive"
    }
    public void reopenAccount() throws IllegalStateException {
// TODO: Check if already active
// TODO: Set status to "Active"
    }
// ===== PIN Management =====
    public void setPin(int pin) throws IllegalArgumentException {
// TODO: Validate PIN (4-digit number)
// TODO: Set pin
    }
    public boolean verifyPin(int pin) {
// TODO: Return true if PIN matches, false otherwise
    }

    public boolean hasPin() {
// TODO: Return true if PIN is set
    }
// ===== Helper Methods =====
    private double getMinimumBalance() {
// TODO: Return minimum balance based on account type
    }
    private void validateActive() throws InactiveAccountException {
// TODO: Throw InactiveAccountException if not active
    }
// ===== Getters =====
// TODO: Add all getters