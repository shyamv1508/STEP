public class Account {

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
 public Account(int accountNumber, String name, int age, double initialBalance, String accountType)
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

    public void withdraw(double amount, int pin)
 throws InvalidAmountException,
            InsufficientBalanceException,
            MinimumBalanceViolationException,
            InactiveAccountException,
            InvalidPinException {
        // TODO: Check if account is active
        if(status == "Inactive"){

        }
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
