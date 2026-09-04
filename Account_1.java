public class Account_1 {

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
    public Account_1(int accountNumber, String name, int age, double initialBalance, String accountType)
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
        if(pin < MIN_PIN && pin > MAX_PIN){
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
}
